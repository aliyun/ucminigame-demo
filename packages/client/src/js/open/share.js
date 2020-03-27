import { checkSDKVersionAvailable } from 'utils/version';

const uc = window.uc;

/**
 * 分享
 * */
export const shareAppMessage = () => {
  return new Promise((resolve, reject) => {
    uc.shareAppMessage({
      title: '转发标题',
      imageUrl: 'http://image.uc.cn/s/uae/g/72/img/uc_minigame_logo.png', // 图片 URL
      query: '', // 查询字符串，必须是 key1=val1&key2=val2 的格式。
      // 从这条转发消息进入后，可通过 uc.getLaunchOptionsSync() 获取启动参数中的 query。
      // target: 'qq', // wechat:微信好友，qq: qq好友，不设置的话会调起分享面板
      success: res => resolve(res),
      fail: err => reject(err),
    });
  })
};

/**
 * 获取分享回流信息
 * */
export const getLaunchOptionsSync = () => {
  const isAvailable = checkSDKVersionAvailable('1.0.3');
  return isAvailable
    ? uc.getLaunchOptionsSync()
    : Promise.reject({ code: -1, msg: '版本过低' });
};
