const uc = window.uc;

/**
 * 获取游客信息
 * */
export default () => {
  return new Promise((resolve, reject) => {
    uc.getGuestInfo({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};
