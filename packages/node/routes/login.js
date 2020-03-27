const fetch = require('node-fetch');
const changeFormData = require('../utils/changeFormData');
const resReturn = require('../utils/res-return');

module.exports = (router) => {
    /**
     * 获取 session
     * */
    router.post('/getSessionData', async ( ctx )=>{
        const { body } = ctx.request;
        const postData = changeFormData(body);

        const result = await fetch('https://open-auth.uc.cn/auth.code2Session', {
            method: 'POST',
            body: postData,
        }).then(e => e.json());

        const { data } = result || {};

        if (data) {
            resReturn.success(ctx, {
                data,
            });
        } else {
            resReturn.fail(ctx);
        }
    });
};
