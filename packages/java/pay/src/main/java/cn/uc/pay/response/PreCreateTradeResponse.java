package cn.uc.pay.response;

import cn.uc.pay.utils.Json;

public class PreCreateTradeResponse {

    /**
     * 客户端查询订单的token
     */
    private String token;
    /**
     * 支付网关的交易订单id
     */
    private String orderId;

    public PreCreateTradeResponse() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
