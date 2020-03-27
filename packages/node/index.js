const Koa = require('koa');
const Router = require('koa-router');
const cors = require('koa2-cors');
const koaBody = require('koa-body');

const app = new Koa();
const router = new Router();

app.use(cors({
    origin: function (ctx) {
        return "*";
    },
    exposeHeaders: ['WWW-Authenticate', 'Server-Authorization'],
    maxAge: 5,
    credentials: true,
    allowMethods: ['GET', 'POST', 'DELETE'],
    allowHeaders: ['Content-Type', 'Authorization', 'Accept'],
}));

app.use(koaBody());
app.use(router.routes());

// 接口路由
require('./routes/login')(router);
require('./routes/pay')(router);

app.use(async ctx => {
    ctx.body = 'Hello World';
});

app.listen(3001);
console.log('listen to http://location:3001');
