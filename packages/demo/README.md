## demo 使用教程

### 目录结构
> 基础示例 demo 结构如下

```
.
├── public
│   └──  game.zip            // 游戏 demo zip 包示例
├── assets                   // 资源示例
├── index.html               // 基础 index.html 文件
├── package.json
├── uc.config.json           // 基础 UC 游戏描述配置文件
└── watch.js                 // 本地在线访问服务
```

### 游戏本地调试 game_url 模式调试示例

#### Step1 : 启动本地服务
```bash
$ npm run dev
```

> 启动本地服务后获取在线访问示例地址: `http://{ip}:8889/index.html

##### Step2 : 生成协议扫码

> 访问 UC 小游戏在线调试小工具: https://minigame.uc.cn/tools/protocal

（1）填写 app_id 「 公司管理获取 」、client_id 「 游戏管理获取 」、game_url 「 http://{ip}:8889/index.html 」 等关键参数；
（2）提交参数，生成二维码；
（3）下载并使用 UC 游戏开发者包扫码。

----

### 游戏 Zip 包模式打包自测示例

#### Step1 : 准备游戏 zip 包

`packages/demo/public` 中 `game.zip` 游戏包准备，如何得到 `game.zip` 参考如下 :

- 将 `index.html`、`uc.config.json`、`assets/` 游戏资源和产物进行压缩，切记不要新建文件夹压缩
- 压缩产物命名无限制，最终以 `.zip` 后缀结尾

#### Step2 : 打包生成产物二维码自测

> 访问 UC 小游戏打包自测工具: https://minigame.uc.cn/tools/sonic

（1）填写 client_id 「 游戏管理获取 」
（2）点击或拖拽文件上传游戏包
（3）点击开始打包，生成二维码，下载并使用 UC 浏览器扫码即可。 「 如需调试请使用 UC 游戏开发者包 」
（4）假如出现打包异常，请先阅读常见问题，假如仍无法解决，请加入 “UC 小游戏官方技术交流群” 反馈。

