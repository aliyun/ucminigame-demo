// 游戏参数(必填!)
// 1. 游戏相关参数必填: app_id, client_id, client_key。开放平台，游戏审批通过即可获取。
// 2. 内购相关参数选填: pay_biz_id。开放平台，游戏开通内购审批后即可获取
export default {
  host: '',

  app_id: '',           // 公司标识 app_id
  client_id: '',        // 游戏应用标识 client_id
  client_key: '',       // 游戏应用秘钥 client_key

  // 内购参数, 非内购游戏可以忽略不填
  pay_biz_id: '',       // 游戏应用内购标识 pay_biz_id (该参数为内购 1.0 版本需要， 内购 2.0 已废弃该字段)
}
