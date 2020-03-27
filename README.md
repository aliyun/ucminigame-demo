# UC小游戏开放平台接入最佳实践

## demo
> iOS 请使用应用商店 UC浏览器 扫码。 Android 请使用 UC游戏开发者包 扫码，详见: [文档](https://minigame.uc.cn/intro/develop/apktools)

![image.png](image/QRCode.png)

## 目录简介
```
packages
    ├── client  // 前端示例代码 「 基于 react 」
    ├── java    // Java 端服务端代码示例 「 登陆、支付 」
    └── node    // Node 服务端接口服务示例 「 登陆、支付 」
```

## 备注
- 游戏开发者具体使用参考 `client/src/js`、`cient/src/utils` 相关参考对应代码即可，界面渲染绘制以为具体游戏平台 API 为准。「 Cocos、LayaBox、Egret 」

- `client`、`java`、`node` 对应模块下有 `README.md`，可自行查阅。


### client 前端示例
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

### java 后端示例
```
.
├── src/main/java/cn/uc
│  ├── auth                               // 登陆授权
|  |    ├── client
|  |    ├── const
|  |    ├── request
|  |    ├── response
|  |    └── Code2SessionMainTest
│  ├── pay                                // 内购支付
|  |    ├── client
|  |    ├── constant
|  |    ├── request
|  |    ├── response
|  |    ├── utils
|  |    ├── PreCreateMainTest             // 预下单、订单查询
|  |    └── TradeCallbackMainDemo         // 发货通知
├── pay-client-demo.iml
├── pom.xml
└── README.md
```

### node 后端示例
```
.
├── config          // 参数配置
├── routes          // 接口路由
├── utils           // 公共方法
├── index.js
├── package.json
└── README.md
```
