const PORT = 8889;

const http = require('http');
const fs = require('fs');
const url = require('url');
const path = require('path');

function getIPAdress() {
  var interfaces = require('os').networkInterfaces();　　
  for (var devName in interfaces) {　　　　
      var iface = interfaces[devName];　　　　　　
      for (var i = 0; i < iface.length; i++) {
          var alias = iface[i];
          if (alias.family === 'IPv4' && alias.address !== '127.0.0.1' && !alias.internal) {
              return alias.address;
          }
      }　　
  }
}

function watch (watchDir) {
  const host = `http://${getIPAdress()}`;
  const server = http.createServer((req, res) => {
    const pathName = url.parse(req.url).pathname;
    const realPath = path.join(watchDir, decodeURIComponent(pathName));
    fs.readFile(realPath, (err, data) => {
      if (err) {
        // 未找到文件
        res.writeHead(404, { 'content-type': 'text/plain' });
        res.write('404, 未找到页，请检查路径是否正确');
        res.end();
      } else {
        // 成功读取到文件
        if (path.extname(realPath) === '.html') {
          res.writeHead(200, { 'content-type': 'text/html' });
        } else {
          res.writeHead(200);
        }
        res.write(data);
        res.end();
      }
    });
  });
  server.listen(PORT);
  console.log(`服务已成功启动，服务链接（game_url）: ${host}:${PORT}/index.html`)
  console.log(`请访问 UC 小游戏在线调试小工具生成调试地址: https://minigame.uc.cn/tools/protocal`);
  return `${host}:${PORT}`
}

watch('./');
