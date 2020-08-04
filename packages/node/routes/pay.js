const fetch = require('node-fetch');
const crypto = require('crypto');
const config = require('../config');
const signSort = require('../utils/signSort');
const resReturn = require('../utils/res-return');

const { PAY_CALLBACK_URL, TRADE_API_URL } = config;

/**
 * 加签
 * @param   {Object}    params  加签参数
 * @param   {String}    clientKey  内购参数
 * @return  {String}            MD5 密钥
 * */
const getSign = (params, clientKey) => {
    let paramsStr = signSort(params).map(([ name, value ]) => `${name}=${value}`).join('&');
    paramsStr += `&client_key=${clientKey}`;

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
        const { client_key: clientKey, open_id: openId, guest_id: guestId, sys_info, platform, ...publicParams } = body;

        const params = {
            biz_order_id: Date.now(),
            total_amount: 1,
            open_id: openId,
            guest_id: guestId,
            ip: '0.0.0.1',
            title: '测试支付',
            platform,
            sys_info,
            notify_url: PAY_CALLBACK_URL,    // 请填写公网域名
        };

        const signData = {
            ...publicParams,
            biz_content: encodeURIComponent(JSON.stringify(params)),
            method_name: '/trade/precreate'
        };
        const sign = getSign(signData, clientKey);

        const postData = getPostData({
            ...signData,
            sign,
        });

        const result = await fetch(TRADE_API_URL, {
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
            client_key: clientKey,
            order_id: orderId,
            biz_order_id: bizOrderId,
            open_id: openId,
            guest_id: guestId,
            platform,
            ...publicParams
        } = body;

        const params = {
            order_id: orderId,
            biz_order_id: bizOrderId,
            open_id: openId,
            guest_id: guestId,
            platform,
        };

        const signData = {
            ...publicParams,
            biz_content: encodeURIComponent(JSON.stringify(params)),
            method_name: '/trade/query',
        };
        const sign = getSign(signData, clientKey);

        const postData = getPostData({
            ...signData,
            sign,
        });

        const result = await fetch(TRADE_API_URL, {
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
        const body = 'biz_content=%7B%22total_amount%22%3A100%2C%22trade_status%22%3A%22SUCCESS%22%2C%22trade_pay_time%22%3A%222020-07-07+15%3A30%3A30%22%2C%22title%22%3A%22%E9%81%93%E5%85%B7%22%2C%22order_id%22%3A%22456%22%2C%22biz_order_id%22%3A%22123%22%7D&nonce_str=04d118b53bb54aeb8969035634e92f86&sign=8cd4f7bbb4c38cdf94be5db01dc24a0a&app_id=c64950d1e48040199cb4ddcc4b2f7593&sign_type=MD5&version=1.0&client_id=acfb87109c17431cac2e316254cde254&timestamp=1594107088979';
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
