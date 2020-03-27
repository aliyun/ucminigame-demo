const uc = window.uc;

/**
 * 获取系统设备信息
 * */
export const getSystemInfo = () => {
  if (!uc) return Promise.reject();

  return new Promise((resolve, reject) => {
    uc.getSystemInfo({
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};

/**
 * 获取系统设备信息(同步)
 * */
export const getSystemInfoSync = () => {
  if (!uc) return false;

  try {
    const data = uc.getSystemInfoSync();
    return JSON.parse(data);
  } catch (err) {
    /** */
  }

  return false;
};
