const uc = window.uc;

/**
 * 弹窗
 * @param {Object}  options 弹窗参数
 *    ├──title                {String}    弹窗
 *    ├──content              {String}    正文内容
 *    ├──showCancel           {Boolean}   是否显示取消按钮
 *    ├──cancelText           {String}    取消按钮文字内容
 *    ├──cancelColor          {String}    取消按钮文字颜色
 *    ├──confirmText          {String}    确认按钮文字内容
 *    ├──confirmColor         {String}    确认按钮文字颜色
 *    ├──confirmBgColorLight  {String}    确认按钮背景颜色浅色(确认按钮背景是渐变色所以需要设置两个颜色)
 *    ├──confirmBgColorDark   {String}    确认按钮背景颜色深色(确认按钮背景是渐变色所以需要设置两个颜色)
 *    ├──canBgCancel          {Boolean}   是否允许点击背景触发取消操作
 *    ├──success              {Function}  取消/确认操作回调函数, 接收按钮类型参数: { confirm: true } / { cancel: true }
 *    ├──fail                 {Function}  调用失败错误回调函数, { msg: '' }
 *    └──complete             {Function}  调用成功/失败统一回调函数
 * */
export const showModal = (options) => {
  if (!uc) return;

  try {
    uc.showModal(options);
  } catch (err) {
    console.log('showModal error: ', err);
  }
};

/**
 * toast 提示
 * @param {Object}  options toast 参数
 *    ├──content  {String}    toast 内容
 *    ├──duration {Number}    消失时间, 默认: 2000(毫秒)
 *    ├──mask     {Boolean}   是否显示蒙层(防止穿透)
 *    ├──success  {Function}  toast 成功展示后回调函数
 *    ├──fail     {Function}  调用失败错误回调函数, { msg: '' }
 *    └──complete {Function}  调用成功/失败统一回调函数
 * */
export const showToast = (options) => {
  if (!uc) return;

  try {
    uc.showToast(options);
  } catch (err) {
    console.log('showToast error: ', err);
  }
};

/**
 * 退出
 * */
export const exit = () => {
  if (!uc) return;

  uc.exit();
};
