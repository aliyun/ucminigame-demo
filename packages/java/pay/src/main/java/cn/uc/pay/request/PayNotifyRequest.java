package cn.uc.pay.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 支付结果回调通知参数
 */
public class PayNotifyRequest {
    /**
     * 支付网关交易订单id，全局唯一
     */
    private String orderId;

    /**
     * 业务订单id
     */
    private String bizOrderId;

    /**
     * 订单总金额
     */
    private Integer totalAmount;
    /**
     * 交易订单状态
     */
    private String tradeStatus;
    /**
     * 订单商品名称
     */
    private String title;
    /**
     * 用户支付时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date tradePayTime;

    /**
     * 预下单透传字段
     */
    private String extra;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBizOrderId() {
        return bizOrderId;
    }

    public void setBizOrderId(String bizOrderId) {
        this.bizOrderId = bizOrderId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTradePayTime() {
        return tradePayTime;
    }

    public void setTradePayTime(Date tradePayTime) {
        this.tradePayTime = tradePayTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
