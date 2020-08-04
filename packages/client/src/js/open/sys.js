const uc = window.uc;

let systemInfo;

/**
 * 获取系统信息
 */
export default () => {
  if (!systemInfo) {
    systemInfo = uc.getSystemInfoSync();
    if (typeof systemInfo === 'string') {
      try {
        systemInfo = JSON.parse(systemInfo);
      } catch (e) { }
    }
  }

  return systemInfo;
}