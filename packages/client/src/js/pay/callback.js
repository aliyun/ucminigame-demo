import request from 'utils/fetch';

/**
 * 获取订单回调数据
 * */
export default () => {
  return request('/getTradeCallback', {
    method: 'POST',
  });
};
