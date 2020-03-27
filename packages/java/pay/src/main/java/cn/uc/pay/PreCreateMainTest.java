package cn.uc.pay;

import cn.uc.pay.client.UcPayClient;
import cn.uc.pay.constant.PayType;
import cn.uc.pay.constant.PurchasePlatform;
import cn.uc.pay.constant.UcPayConstants;
import cn.uc.pay.request.PreCreateTradeRequest;
import cn.uc.pay.response.PreCreateTradeResponse;
import cn.uc.pay.response.UcPayResponse;
import cn.uc.pay.utils.UUID;

public class PreCreateMainTest {

    /**
     * PreCreate、预下单
     *
     * @param args
     */
    public static void main(String[] args) {
        // 填入开放平台分配的内购参数，游戏开通内购且审核通过后才会分配参数，注意不要与登录授权参数混用
        UcPayClient client = new UcPayClient("https://payment.uc.cn", "appKey", "bizId", "clientId");
        PreCreateTradeRequest request = new PreCreateTradeRequest();

        // 业务订单 id，最大长度 64 位
        request.setBizOrderId(UUID.id());

        // 用户 ip，必填
        request.setIp("127.0.0.1");

        // 固定为 101
        request.setPayType(PayType.UNCERTAIN_PAY.getCode());

        // 支付金额，单位分
        request.setTotalAmount(1);

        // 业务回调地址, https / http 开头
        request.setNotifyUrl("业务方自己的回调地址");
        request.setPlatform(PurchasePlatform.ANDROID.name());

        // 登录授权 openId
        request.setUserId("open_id");

        // 用户 guestId
        request.setUtdid("guest_id");
        request.setTitle("商品名称");

        UcPayResponse<PreCreateTradeResponse> response = client.preCreateTrade(request);
        System.out.println(response);
    }

}
