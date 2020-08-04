package cn.uc.pay;

import cn.uc.pay.client.UcGameApp;
import cn.uc.pay.constant.TradeStatus;
import cn.uc.pay.request.CommonOpenRequest;
import cn.uc.pay.request.PayNotifyRequest;
import cn.uc.pay.response.TradeCallbackResponse;
import cn.uc.pay.utils.SignUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @description: 支付网关支付结果回调通知
 **/
public class TradeCallbackMainDemo {

    public static void main(String[] args) {
        // http request body 需要从请求体获取，不是从 url 里获取参数
        System.out.println(payNotify("biz_content=%7B%22total_amount%22%3A8800%2C%22trade_status%22%3A%22SUCCESS%22%2C%22extra%22%3A%22fishh5ucIOS001%22%2C%22trade_pay_time%22%3A%222020-08-03+15%3A47%3A13%22%2C%22title%22%3A%22%E5%A4%A7%E7%A5%9E%E6%8D%95%E9%B1%BC%3A%E6%AF%8F%E6%97%A5%E9%99%90%E8%B4%AD%E7%A4%BC%E5%8C%85128%E5%85%83%E6%89%93%E6%8A%98%22%2C%22order_id%22%3A%221522691756960302892%22%2C%22biz_order_id%22%3A%22h5_20200803008q5%22%7D&nonce_str=f2a8043763e3474d8ab1abc2d03a3208&sign=a36c70a9a4b54cdd3361cfd3fca4610c&app_id=f361cf3d6f1d4f8bb4803122c56f6aee&sign_type=MD5&version=1.0&client_id=5dc445ee5b8143cdbbb715dd18247ffd&timestamp=1596440833917"));
    }

    //@RequestMapping("/api/uc/pay/callback")
    //@ResponseBody
    public static TradeCallbackResponse payNotify(String reqBody) {
        if (StringUtils.isBlank(reqBody)) {
            return TradeCallbackResponse.fail("回调内容为空");
        }
        CommonOpenRequest<PayNotifyRequest> notifyRequest = CommonOpenRequest.build(reqBody, PayNotifyRequest.class);
        // 校验签名和基本参数
        TradeCallbackResponse response = validNotifyRequest(notifyRequest, reqBody);
        if (response.getCallbackCode() == TradeCallbackResponse.ResponseCode.FAIL) {
            return response;
        }
        // 未支付订单，直接返回
        if (!TradeStatus.SUCCESS.name().equalsIgnoreCase(notifyRequest.getBizContent().getTradeStatus())) {
            return TradeCallbackResponse.success();
        }
        // 1.校验支付金额，订单id，订单状态
        // 2.校验订单是否已发货，根据orderId幂等处理
        // 3.业务自己发货逻辑
        return response;

    }

    private static TradeCallbackResponse validNotifyRequest(CommonOpenRequest ucPayRequest, String reqBody) {
        if (ucPayRequest == null) {
            return TradeCallbackResponse.fail("请求body或bizcontent为空");
        }
        UcGameApp gameApp = new UcGameApp("clientKey", "appId", "clientId");

        // 使用原始的 reqbody 进行签名验证，不能先 URL decode 后再验证
        if (!SignUtils.checkOpenSign(gameApp.getClientKey(), ucPayRequest.getSign(), reqBody)) {
            return TradeCallbackResponse.fail("签名sign错误");
        }
        // 验证 biz_id 是否正确
        if (StringUtils.isBlank(ucPayRequest.getClientId()) || !Objects.equals(gameApp.getClientId(), ucPayRequest.getClientId())) {
            return TradeCallbackResponse.fail("clientId错误");
        }
        if (!Objects.equals(gameApp.getAppId(), ucPayRequest.getAppId())) {
            return TradeCallbackResponse.fail("appId错误");
        }
        if (ucPayRequest == null || ucPayRequest.getBizContent() == null) {
            return TradeCallbackResponse.fail("请求body或bizcontent为空");
        }
        if (StringUtils.isBlank(ucPayRequest.getClientId())) {
            return TradeCallbackResponse.fail("支付回调请求公共参数为空：clientId为空");
        }
        if (ucPayRequest.getBizContent() == null) {
            return TradeCallbackResponse.fail("支付回调请求公共参数为空：业务参数");
        }
        return TradeCallbackResponse.success();
    }
}
