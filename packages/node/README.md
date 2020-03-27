# 后端（Node）示例

## 目录结构
```
.
├── config          // 参数配置
├── routes          // 接口路由
├── utils           // 公共方法
├── index.js
├── package.json
└── README.md
```

## 如何使用

#### 启动服务
```bash
$ npm i
$ npm run start
```

#### 填写内购参数

如果需求验证内购功能需要填写 `支付回调接口地址`，否则可忽略

```bash
$ cd config

PAY_CALLBACK_URL = 'your pay callback interface'  // 修改对应公网地址即可、http:// 或 https://
```
