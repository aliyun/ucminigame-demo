package cn.uc.pay.request;

import cn.uc.pay.utils.Json;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 支付结果回调通知参数
 */
public class PayNotifyRequest {

    /**
     * 支付网关交易订单id，全局唯一
     */
    private String tradeId;

    /**
     * 业务订单id
     */
    private String bizOrderId;

    /**
     * 订单总金额
     */
    private Integer totalAmount;
    /**
     * 支付方式
     */
    private String payType;
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
     * Ios支付商品类型
     */
    private String productType;
    /**
     * Ios商品id
     */
    private String productId;
    /**
     * Ios商品单价
     */
    private Integer price;
    /**
     * 购买商品数量，一般为1
     */
    private Integer quantity;

    /**
     * IAP支付购买环境
     */
    private String environment;

    /**
     * 退款时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cancellationTime;

    /**
     * 退款原因
     */
    private Integer cancellationReason;

    /**
     * iap订阅过期时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expiresDate;

    /**
     * IAP订阅过期原因
     */
    private Integer expirationIntent;

    /**
     * 订单关闭时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expiredAt;

    // IAP订阅自动续费类产品返回该字段, 上一次订阅的业务订单id
    private String lastBizOrderId;

    // IAP订阅自动续费类产品返回该字段，上一次交易的用户id
    private String lastUserId;

    /**
     * 预下单透传字段
     */
    private String extra;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getBizOrderId() {
        return bizOrderId;
    }

    public void setBizOrderId(String bizOrderId) {
        this.bizOrderId = bizOrderId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getTradePayTime() {
        return tradePayTime;
    }

    public void setTradePayTime(Date tradePayTime) {
        this.tradePayTime = tradePayTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Integer getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(Integer cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Date getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(Date cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public Integer getExpirationIntent() {
        return expirationIntent;
    }

    public void setExpirationIntent(Integer expirationIntent) {
        this.expirationIntent = expirationIntent;
    }

    public String getLastBizOrderId() {
        return lastBizOrderId;
    }

    public void setLastBizOrderId(String lastBizOrderId) {
        this.lastBizOrderId = lastBizOrderId;
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
