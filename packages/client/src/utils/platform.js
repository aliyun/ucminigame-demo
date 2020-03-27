const { userAgent } = navigator;
export const isAndroid = userAgent.indexOf('Android') > -1 || userAgent.indexOf('Adr') > -1;
export const isIOS = !!userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);

export default {
  isAndroid,
  isIOS,
}
