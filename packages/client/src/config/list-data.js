import open from 'assets/image/icon_open.png';
import device from 'assets/image/icon_device.png';
import ad from 'assets/image/icon_ad.png';
import pay from 'assets/image/icon_pay.png';

export default [{
  title: '开放能力',
  icon: open,
  sub: [{
    title: '获取用户信息',
    link: '/open/user',
  }, {
    title: '获取游客信息',
    link: '/open/guest',
  }, {
    title: '分享回流',
    link: '/open/share',
  }, {
    title: '分包/跨域',
    link: '/open/packages',
  }],
}, {
  title: '设备',
  icon: device,
  sub: [{
    title: '获取系统设备信息',
    link: '/device/setting',
  }, {
    title: '强制横屏或竖屏',
    link: '/device/screen',
  }, {
    title: 'Cocos 引擎开启 WebGL 兼容',
    link: '/device/cocos',
  }],
}, {
  title: '广告组件',
  icon: ad,
  sub: [{
    title: 'banner 广告',
    link: '/ad/banner',
  }, {
    title: '激励视频广告',
    link: '/ad/excitation',
  }],
}, {
  title: '支付',
  icon: pay,
  sub: [{
    title: '支付/重新支付',
    link: '/pay',
  }, {
    title: '获取支付回调结果',
    link: '/pay/callback',
  }],
}]
