# 前端示例

## 目录结构
```
.
├── build                   // 最终生成产物
├── public                  // 外部可访问文件
├── src                     // 原始代码
│   ├── assets              // 资源
│   │   ├── image           // 图片
│   │   ├── scss            // 样式
│   ├── component           // 公共组件
│   ├── config              // 配置
│   ├── js
│   │   ├── ad              // 广告
│   │   ├── device          // 设备
│   │   ├── open            // 开放能力
│   │   └── pay             // 支付
│   ├── page                // 模版页面
│   ├── utils               // 公共方法
│   ├── index.css
│   ├── index.js
│   ├── router.js
│   ├── serviceWorker.js
│   └── setupTests.js
├── .gitignore
├── jsconfig.json
├── CLOUD_REAMDE.md
├── package.json
├── README.md
├── uc.config.json
└── yarn.lock
```

## 如何使用

#### 填写游戏相关参数

打开 `src/config` 修改对应环境的配置变量

```
const host = '';            // 接口地址, 填写可访问的 IP 地址(如：http://192.168.1.1:3001, 必填!)

// 游戏参数(必填!)
const app_id = '';          // 公司标识 app_id
const client_id = '';       // 游戏应用标识 client_id
const client_key = '';      // 游戏应用秘钥 client_key

// 内购参数, 非内购游戏可以忽略不填
const pay_client_id = '';   // 公司内购支付标识 pay_client_id
const pay_app_key = '';     // 公司内购支付秘钥 pay_app_key
const pay_biz_id = '';      // 游戏应用内购标识 pay_biz_id
```

内购验证还需要填写`支付回调接口地址`，具体请参照 [Node README.md](../node/README.md)

#### 远程调试

填写对应参数生成对应 `uclink` 即可扫码快速验证，`game_url` 填写同一网段下手机可访问的地址即可，具体可参考[开发调试指南](https://minigame.uc.cn/intro/develop/develop)
```
uclink://minigame?action=launch&module=uc_minigame&appid=申请appID&clientid=申请的clientid&game_name=游戏名&game_url=小游戏页面服务在线地址&game_icon=http://xxxxx
```

#### 本地开发
```bash
$ npm i
$ npm run dev
```

#### 打开预览
> `http://localhost:3000`

#### 打包验证
```bash
$ npm run build     // 打包生产环境配置
$ npm run pre       // 打包预发环境配置

$ cd build
```
压缩 `build` 目录下的 `game.zip`，到[小游戏开放平台打包服务](https://minigame.uc.cn/tools/sonic)进行打包扫码即可验证
`client_id` 需对应填写到打包参数，否则加签会失败

> `public/game.zip` 是已经打包好的游戏包，可直接到小游戏开放平台打包服务进行打包验证

#### 注意
> 如果出现部分功能无法使用（如：IOS 下无法调起激励视频），请使用线上打包模式
