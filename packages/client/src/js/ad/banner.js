import getSystemInfo from 'js/open/sys';

const uc = window.uc;

let ad;

/**
 * 监听广告加载
 * @param {Function}  cb  回调函数
 * */
export const onLoad = (cb) => {
  if (!ad) return;
  ad.onLoad(cb);
};

/**
 * 监听广告异常
 * @param {Function}  cb  回调函数
 * */
export const onError = (cb) => {
  if (!ad) return;
  ad.onError(cb);
};

/**
 * 显示广告
 * */
export const show = () => {
  if (!ad) return;
  ad.show();
};

/**
 * 隐藏广告
 * */
export const hide = () => {
  if (!ad) return;
  ad.hide();
};

/**
 * 销毁广告
 * */
export const destroy = () => {
  if (!ad) return;
  ad.destroy();
};

/**
 * 创建广告
 * */
export const create = () => {
  console.log('bannerAd 广告加载 start ');
  const sysInfo = getSystemInfo();

  const deviceWidth = sysInfo.screenWidth > sysInfo.screenHeight ? sysInfo.screenHeight : sysInfo.screenWidth;
  const width = deviceWidth / 2;
  const height = width * 194 / 345;
  ad = uc.createBannerAd({
    style: {
      width: width,
      height: height,
      gravity: 7,
    }
  });

  show();

  return ad;
};
