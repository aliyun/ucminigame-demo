package cn.uc.pay.request;

import cn.uc.pay.utils.Json;

public class PreCreateTradeRequest {

    /**
     * 业务订单id，业务内唯一，非空
     */
    private String bizOrderId;
    /**
     * 订单总金额，单位分，非空
     */
    private Integer totalAmount;
    /**
     * 用户id，非空, open_id
     */
    private String openId;
    /**
     * 用户设备id, guest_id
     */
    private String guestId;
    /**
     * 客户端ip 非空,格式xx.xx.xx.xx
     */
    private String ip;
    /**
     * 商品名称,非空
     */
    private String title;
    /**
     * 支付平台,非空
     */
    private String platform;
    /**
     * 支付结果通知地址, 非空
     */
    private String notifyUrl;

    /**
     * 透传字段，支付回调会返回
     */
    private String extra;

    /**
     * 设备信息
     */
    private String sysInfo;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(String sysInfo) {
        this.sysInfo = sysInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
