import idx from 'idx';
import config from 'config';
import platform from 'utils/platform';
import request from 'utils/fetch';
import { checkSDKVersionAvailable } from 'utils/version';
import { checkLogin } from 'js/open/user';
import getSystemInfo from 'js/open/sys';

const {
  app_id: payAppId,
  pay_client_id: payClientID,
  client_key: payClientKey,
  pay_biz_id: payBizID,
} = config;

const uc = window.uc;

/**
 * 挑起支付面板
 * @param {String}  nToken   预创建订单 token
 * @param {String}  orderId 交易订单 id
 * */
const requestPayment = (nToken, orderId) => {
  const isAvailable = checkSDKVersionAvailable('1.0.1');
  if (!isAvailable) return Promise.reject();

  return new Promise((resolve, reject) => {
    uc.requestPayment({
      biz_id: payBizID,
      token: nToken,
      order_id: orderId,
      // trade_id 为 1.0 版本收银台参数，2.0 接入使用 order_id 代替, 可不传
      trade_id: orderId,
      success: (data) => {
        resolve(data);
      },
      fail: (data) => {
        reject(data);
      },
    });
  })
};

/**
 * 加签参数
 * @return {Object} 加签参数
 * */
const getParams = (attachParams) => ({
  app_id: payAppId,
  client_id: payClientID,
  sign_type: 'MD5',
  timestamp: Date.now(),
  version: '1.0',
  nonce_str: Date.now(),
  client_key: payClientKey,
  ...attachParams,
});

/**
 * 预创建订单
 * @return  {Object}          订单信息
 * */
const precreate = (attachParams) => {
  const sysInfo = getSystemInfo();
  return request('/tradePrecreate', {
    method: 'POST',
    body: JSON.stringify({
      ...getParams(attachParams),
      platform: sysInfo.platform,
      sys_info: encodeURIComponent(JSON.stringify(getSystemInfo())),
    }),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  });
};

/**
 * 获取订单状态
 * */
const fetchTradeData = (params) => {
  const sysInfo = getSystemInfo();
  return request('/tradeQuery', {
    method: 'POST',
    body: JSON.stringify({
      ...getParams(),
      order_id: params.orderId,
      biz_order_id: params.bizOrderId,
      open_id: params.openId,
      guest_id: params.guestId,
      platform: sysInfo.platform,
    }),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  });
};

/**
 * 支付
 * */
export const pay = async (params) => {
  const { isLogin } = await checkLogin();
  if (!isLogin) return Promise.reject({ code: 3001, msg: '未登录' });

  const result = {};

  try {
    // 预创建订单
    const preCreateData = await precreate(params);
    const { token: newToken, order_id: newOrderId } = preCreateData;
    Object.assign(result, preCreateData);

    const res = await requestPayment(newToken, newOrderId);
    Object.assign(result, res);
  } catch (err) {
    console.log('pay error: ', err);
  }

  return Object.keys(result).length > 0 ? result : Promise.reject();
};

/**
 * 继续支付
 * @param   {String}  token   订单 token
 * @param   {String}  orderId 订单 id
 * @return  {Promise}         支付结果
 * */
export const repay = async (token, orderId) => {
  // if (platform.isIOS) return Promise.reject({ code: -1, msg: 'iOS 暂不支持充值哦' });

  const errorRes = { code: -1, msg: '缺少订单参数' };

  if (!token || !orderId) {
    console.log('repay error: ', errorRes);
    return Promise.reject(errorRes);
  }

  const { isLogin } = await checkLogin();
  if (!isLogin) return Promise.reject({ code: 3001, msg: '未登录' });

  try {
    return await requestPayment(token, orderId);
  } catch (err) {
    console.log('repay error: ', err);

    const extRes = idx(err, _ => _.ext);
    if (extRes) return Promise.reject(extRes);
  }

  return Promise.reject();
};

/**
 * 查询订单信息
 * @param {object} params
 */
export const getTradeData = async (params) => {
  if (!params.orderId || !params.bizOrderId) {
    return Promise.reject({ code: -1, msg: '缺少订单参数' });
  }

  return fetchTradeData(params);
};
