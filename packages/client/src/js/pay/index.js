import idx from 'idx';
import config from 'config';
import platform from 'utils/platform';
import request from 'utils/fetch';
import { checkSDKVersionAvailable } from 'utils/version';
import { checkLogin } from 'js/open/user';

const {
  pay_client_id: payClientID,
  pay_app_key: payAppKey,
  pay_biz_id: payBizID,
} = config;

const uc = window.uc;

/**
 * 挑起支付面板
 * @param {String}  nToken   预创建订单 token
 * @param {String}  nTradeId 交易订单 id
 * */
const requestPayment = (nToken, nTradeId) => {
  const isAvailable = checkSDKVersionAvailable('1.0.1');
  if (!isAvailable) return Promise.reject();

  return new Promise((resolve, reject) => {
    uc.requestPayment({
      biz_id: payBizID,
      token: nToken,
      trade_id: nTradeId,
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
const getParams = () => ({
  client_id: payClientID,
  biz_id: payBizID,
  sign_type: 'MD5',
  timestamp: Date.now(),
  version: '1.0',
  nonce_str: Date.now(),
  app_key: payAppKey,
});

/**
 * 预创建订单
 * @return  {Object}          订单信息
 * */
const precreate = () => {
  return request('/tradePrecreate', {
    method: 'POST',
    body: JSON.stringify(getParams()),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  });
};

/**
 * 获取订单状态
 * */
const fetchTradeData = (tradeId, bizOrderId) => {
  return request('/tradeQuery', {
    method: 'POST',
    body: JSON.stringify({
      ...getParams(),
      trade_id: tradeId,
      biz_order_id: bizOrderId,
    }),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  });
};

/**
 * 支付
 * */
export const pay = async () => {
  if (platform.isIOS) return Promise.reject({ code: -1, msg: 'iOS 暂不支持充值哦' });

  const { isLogin } = await checkLogin();
  if (!isLogin) return Promise.reject({ code: 3001, msg: '未登录' });

  const result = {};

  try {
    // 预创建订单
    const preCreateData = await precreate();
    const { token: newToken, trade_id: newTradeId } = preCreateData;
    Object.assign(result, preCreateData);

    const res = await requestPayment(newToken, newTradeId);
    Object.assign(result, res);
  } catch (err) {
    console.log('pay error: ', err);
  }

  return Object.keys(result).length > 0 ? result : Promise.reject();
};

/**
 * 继续支付
 * @param   {String}  token   订单 token
 * @param   {String}  tradeId 订单 id
 * @return  {Promise}         支付结果
 * */
export const repay = async (token, tradeId) => {
  if (platform.isIOS) return Promise.reject({ code: -1, msg: 'iOS 暂不支持充值哦' });

  const errorRes = { code: -1, msg: '缺少订单参数' };

  if (!token || !tradeId) {
    console.log('repay error: ', errorRes);
    return Promise.reject(errorRes);
  }

  const { isLogin } = await checkLogin();
  if (!isLogin) return Promise.reject({ code: 3001, msg: '未登录' });

  try {
    return await requestPayment(token, tradeId);
  } catch (err) {
    console.log('repay error: ', err);

    const extRes = idx(err, _ => _.ext);
    if (extRes) return Promise.reject(extRes);
  }

  return Promise.reject();
};

/**
 * 查询订单信息
 * @param   {String}  tradeId     订单 id
 * @param   {String}  bizOrderId  自定义的订单 id
 * @return  {Promise}             支付结果
 * */
export const getTradeData = async (tradeId, bizOrderId) => {
  if (!tradeId || !bizOrderId) {
    return Promise.reject({ code: -1, msg: '缺少订单参数' });
  }

  return fetchTradeData(tradeId, bizOrderId);
};
