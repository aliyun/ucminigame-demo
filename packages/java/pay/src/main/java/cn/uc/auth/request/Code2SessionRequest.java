package cn.uc.auth.request;

import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.UUID;

public class Code2SessionRequest {

    private String appId;

    private String clientId;

    private String requestId;

    private String code;

    private Long timestamp;

    private String sign;

    public Code2SessionRequest() {

    }

    public Code2SessionRequest(String appId, String clientId, String code) {
        super();
        this.appId = appId;
        this.clientId = clientId;
        this.timestamp = System.currentTimeMillis();
        this.requestId = UUID.id();
        this.code = code;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
