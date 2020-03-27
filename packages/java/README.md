# 后端 (Java) 端示例

## 目录结构

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

## 如何使用

- 不同于 node、java 示例无直接部署，可自行参考代码获取需要内容即可

#### 登陆编写必填项参数
```bash
$ cd src/main/java/cn/uc/auth

vi Code2SessionMainTest

```

打开对应文件、修改填写 `appId`、`clientId`、`clientKey` 。

#### 支付填写必填项参数

```bash
$ cd src/main/java/cn/uc/pay

vi PreCreateMainTest
vi TradeCallbackMainDemo

```

打开对应文件、修改所需配置项 `appId`、`clientId`、`clientKey`、`payBizId`、`payClientId`、`payAppKey`。
