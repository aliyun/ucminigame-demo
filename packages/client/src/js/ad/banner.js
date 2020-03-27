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
  ad = uc.createBannerAd({
    style: {
      gravity: 1,
      top: 500,
    }
  });

  show();

  return ad;
};
