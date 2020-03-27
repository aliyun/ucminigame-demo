import config from 'config';
import signSort from 'utils/sign-sort';
import md5 from 'utils/md5';
import request from 'utils/fetch';

const uc = window.uc;

const {
  app_id: appID,
  client_id: clientID,
  client_key: clientKey,
} = config;

const ucLogin = () => {
  return new Promise((resolve, reject) => {
    uc.login({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

/**
 * 获取 session data
 * @param   {Object}  params  加签公参
 * @return  {Object}          session data
 * */
const fetchSessionData = (params) => {
  const { request_id: requestID } = params;

  const paramStr = signSort(params).map(([ name, value ]) => `${name}=${value}`).join('&');
  const signText = `${clientID}${clientKey}${requestID}${paramStr}`;
  const sign = md5(signText);

  const postData = {
    ...params,
    sign,
  };

  return request('/getSessionData', {
    method: 'POST',
    body: JSON.stringify(postData),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  });
};

const checkSession = () => {
  return new Promise((resolve) => {
    uc.checkSession({
      success: () => resolve({ code: 0 }),
      fail: () => resolve({ code: -1 }),
    });
  })
};

const getSetting = () => {
  return new Promise((resolve, reject) => {
    uc.getSetting({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

const authorize = () => {
  return new Promise((resolve, reject) => {
    uc.authorize({
      scope: 'userInfo',
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

export const checkLogin = () => {
  return new Promise((resolve, reject) => {
    uc.isLogin({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

const getUserInfo = () => {
  return new Promise((resolve, reject) => {
    uc.getUserInfo({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

const checkSessionAvailable = async () => {
  const checkSessionRes = await checkSession();
  return checkSessionRes.code === 0
};

/**
 * 获取 session data
 * @param   {String}  code  登录 code
 * @return  {Object}        session data
 * */
const getSessionData = async (code) => {
  const isSessionAvailable = await checkSessionAvailable();
  if (isSessionAvailable) return { code: 0, msg: 'session available' };

  const params = {
    request_id: Date.now(),
    code,
    app_id: appID,
    client_id: clientID,
    timestamp: Date.now(),
  };

  // 获取 session data
  return fetchSessionData(params);
};

/**
 * 登录
 * */
export const checkAndLogin = async () => {
  const { isLogin } = await checkLogin();
  const isSessionAvailable = await checkSessionAvailable();

  // 已登录 且 session data 未过期, 否则需要重新获取 session data
  if (isLogin && isSessionAvailable) return Promise.resolve();

  const { code } = await ucLogin();
  return getSessionData(code);
};

/**
 * 获取登录后的用户信息
 * */
export default async () => {
  if (!uc) return Promise.reject();

  try {
    // 登录判断
    await checkAndLogin();
  } catch (err) {
    console.log('login error: ', err);
    return Promise.reject({ code: 300001, msg: 'need login' })
  }

  try {
    // 获取用户授权配置
    const { userInfo } = await getSetting();

    // 如果没有授权则触发授权
    if (!userInfo) {
      await authorize();
    }

    // 获取用户信息
    return getUserInfo();
  } catch (err) {
    console.log('get UserInfo error: ', err);
    return Promise.reject(err);
  }
};
