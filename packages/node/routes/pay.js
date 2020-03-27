const fetch = require('node-fetch');
const crypto = require('crypto');
const config = require('../config');
const signSort = require('../utils/signSort');
const resReturn = require('../utils/res-return');

const { PAY_CALLBACK_URL } = config;

/**
 * 加签
 * @param   {Object}    params  加签参数
 * @param   {String}    appKey  内购参数
 * @return  {String}            MD5 密钥
 * */
const getSign = (params, appKey) => {
    let paramsStr = signSort(params).map(([ name, value ]) => `${name}=${value}`).join('&');
    paramsStr += `&app_key=${appKey}`;

    const md5Builder = crypto.createHash('md5');
    return md5Builder.update(paramsStr).digest('hex');
};

/**
 * 参数转字符串
 * @param   {Object}    data    参数对象
 * @return  {String}            参数字符串
 * */
const getPostData = (data) => Object.keys(data).map(e => `${e}=${data[e]}`).join('&');

module.exports = (router) => {
    // 预创建订单
    router.post('/tradePrecreate', async ( ctx ) => {
        const { body } = ctx.request;
        const { app_key: appKey, ...publicParams } = body;

        const params = {
            biz_order_id: Date.now(),
            total_amount: 1,
            user_id: 1000,
            ip: '0.0.0.1',
            pay_type: '001',
            title: '测试支付',
            platform: 'ANDROID',
            notify_url: PAY_CALLBACK_URL,    // 请填写公网域名
        };

        const signData = { ...publicParams, biz_content: encodeURIComponent(JSON.stringify(params)) };
        const sign = getSign(signData, appKey);

        const postData = getPostData({
            ...signData,
            sign,
        });

        const result = await fetch('https://payment.uc.cn/open/trade/precreate', {
            method: 'POST',
            body: postData,
            headers: {
                'Content-Type': 'text/plain',
            },
        }).then(e => e.json());

        const { code, data } = result;

        if (code === 2000000) {
            resReturn.success(ctx, {
                data: {
                    biz_order_id: params.biz_order_id,
                    ...data,
                },
            });
        } else {
            resReturn.fail(ctx);
        }
    });

    // 查看订单详情
    router.post('/tradeQuery', async ( ctx )=>{
        const { body } = ctx.request;
        const {
            app_key: appKey,
            trade_id: tradeId,
            biz_order_id: bizOrderId,
            ...publicParams
        } = body;

        const params = {
            trade_id: tradeId,
            biz_order_id: bizOrderId,
        };

        const signData = { ...publicParams, biz_content: encodeURIComponent(JSON.stringify(params)) };
        const sign = getSign(signData, appKey);

        const postData = getPostData({
            ...signData,
            sign,
        });

        const result = await fetch('https://payment.uc.cn/open/trade/query', {
            method: 'POST',
            body: postData,
            headers: {
                'Content-Type': 'text/plain',
            },
        }).then(e => e.json());

        const { code, data } = result;

        if (code === 2000000) {
            resReturn.success(ctx, {
                data,
            });
        } else {
            resReturn.fail(ctx);
        }
    });

    // 获取订单支付回调信息
    router.post('/getTradeCallback', async ( ctx )=>{
        const body = 'biz_content=%7B%22third_buyer_id%22%3A%222088532002572455%22%2C%22trade_id%22%3A%22144710841948959685%22%2C%22total_amount%22%3A1%2C%22trade_pay_time%22%3A%222020-03-09+15%3A38%3A34%22%2C%22trade_status%22%3A%22SUCCESS%22%2C%22pay_type%22%3A%22101%22%2C%22expired_at%22%3A%222020-03-09+17%3A38%3A27%22%2C%22title%22%3A%22%E6%B5%8B%E8%AF%95%E6%94%AF%E4%BB%98%22%2C%22biz_order_id%22%3A%221583739506415%22%7D&nonce_str=42eafdaad37a4e45a63d2f3806d6f949&sign=964bdcfef75f1344afb8f12b3b771812&biz_id=6c7405098b804743b9a932cc55c5700e&sign_type=MD5&version=1.0&client_id=105fff2b87ac48e4acd8083875928fac&timestamp=1583739520234';
        const data = body.split('&').reduce((result, item) => {
            const [ key, value ] = item.split('=');
            result[key] = key === 'biz_content' ? JSON.parse(decodeURIComponent(value)) : value;
            return result;
        }, {});

        resReturn.success(ctx, {
            data,
        });
    });
};
