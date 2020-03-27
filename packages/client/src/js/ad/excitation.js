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
 * 监听广告退出
 * @param {Function}  cb  回调函数
 * */
export const onClose = (cb) => {
  if (!ad) return;
  ad.onClose(cb);
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
 * 播放广告
 * */
export const show = () => {
  if (!ad) return;
  ad.show();
};

/**
 * 创建广告
 * */
export const create = () => {
  ad = uc.createRewardVideoAd();
  ad.show()
    .then()
    .catch(err => console.log(err));

  return ad;
};

/**
 * 取消事件绑定
 * */
export const destroy = () => {
  if (!ad) return;

  ad.offClose();
  ad.offLoad();
  ad.offError();
};
