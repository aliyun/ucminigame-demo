package cn.uc.pay;

import cn.uc.pay.constant.TradeStatus;
import cn.uc.pay.request.PayNotifyRequest;
import cn.uc.pay.request.UcPayRequest;
import cn.uc.pay.response.TradeCallbackResponse;
import cn.uc.pay.utils.SignUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @description: 支付网关支付结果回调通知
 **/
public class TradeCallbackMainDemo {

    /**
     * 开放平台分配，请填入正确的 appKey, bizId
     */
    private static final String APPKEY = "appKey";
    private static final String BIZID = "bizId";
    private static final String PAYCLIENTID = "payClientId";

    public static void main(String[] args) {
        // http request body 需要从请求体获取，不是从 url 里获取参数
        System.out.println(payNotify("xxxx"));
    }

    //@RequestMapping("/api/uc/pay/callback")
    //@ResponseBody
    public static TradeCallbackResponse payNotify(String reqBody) {
        if (StringUtils.isBlank(reqBody)) {
            return TradeCallbackResponse.fail("回调内容为空");
        }
        UcPayRequest<PayNotifyRequest> notifyRequest = UcPayRequest.build(reqBody, PayNotifyRequest.class);
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
        // 2.校验订单是否已发货，根据tradeId幂等处理
        // 3.业务自己发货逻辑
        return response;

    }

    private static TradeCallbackResponse validNotifyRequest(UcPayRequest ucPayRequest, String reqBody) {
        if (ucPayRequest == null) {
            return TradeCallbackResponse.fail("请求body或bizcontent为空");
        }
        // 使用原始的 reqbody 进行签名验证，不能先 URL decode 后再验证
        if (!SignUtils.checkOpenSign(APPKEY, ucPayRequest.getSign(), reqBody)) {
            return TradeCallbackResponse.fail("签名sign错误");
        }
        // 验证 biz_id 是否正确
        if (StringUtils.isBlank(ucPayRequest.getBizId()) || !Objects.equals(BIZID, ucPayRequest.getBizId())) {
            return TradeCallbackResponse.fail("bizId错误");
        }
        if (!Objects.equals(PAYCLIENTID, ucPayRequest.getClientId())) {
            return TradeCallbackResponse.fail("clientId错误");
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
