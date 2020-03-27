import { checkVersionAvailable } from 'utils/version';

const uc = window.uc;

/**
 * Cocos 引擎开启 WebGL 兼容
 * */
export default () => {
  const isAvailable = checkVersionAvailable('12.6.5');
  if (!isAvailable) return { msg: '版本过低' };

  return uc.enableWebGL();
};
