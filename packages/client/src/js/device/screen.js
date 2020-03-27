const uc = window.uc;

/**
 * 设置系统横竖屏
 * @param {Number}  orientaiton 屏幕模式; { 1: '竖屏', 2: '横屏' }
 * */
export default (orientaiton = 1) => {
  return new Promise((resolve, reject) => {
    uc.requestScreenOrientation({
      orientaiton, // 1:竖屏模式 2：横屏模式,传感器模式的横屏，对应 android SCREEN_ORIENTATION_SENSOR_LANDSCAPE
      success: (data) => resolve(data),
      fail: (data) => reject(data),
    });
  })
};
