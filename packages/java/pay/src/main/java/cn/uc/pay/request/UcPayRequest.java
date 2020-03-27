package cn.uc.pay.request;

import cn.uc.pay.constant.UcPayConstants;
import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.URLEncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UcPayRequest<T> {

    private String clientId;

    private String bizId;

    private String sign;

    private String signType;

    private Long timestamp;

    private String version;

    private String nonceStr;

    private T bizContent;

    public UcPayRequest() {
        super();
    }

    public UcPayRequest(String clientId, String bizId, String nonceStr, T bizContent) {
        super();
        this.clientId = clientId;
        this.bizId = bizId;
        this.signType = UcPayConstants.SIGNTYPE_MD5;
        this.timestamp = System.currentTimeMillis();
        this.version = UcPayConstants.VERSION;
        this.nonceStr = nonceStr;
        this.bizContent = bizContent;
    }

    public static <T> UcPayRequest<T> build(String reqBody, Class<T> clazz) {
        if (StringUtils.isBlank(reqBody)) {
            return null;
        }

        Map<String, String> fieldMap = getSSMapFromKvPairs(reqBody);

        UcPayRequest<T> req = new UcPayRequest<T>();
        Optional.ofNullable(fieldMap.get("client_id")).ifPresent(e -> req.setClientId(e));
        Optional.ofNullable(fieldMap.get("biz_id")).ifPresent(e -> req.setBizId(e));
        Optional.ofNullable(fieldMap.get("sign")).ifPresent(e -> req.setSign(e));
        Optional.ofNullable(fieldMap.get("sign_type")).ifPresent(e -> req.setSignType(e));
        Optional.ofNullable(fieldMap.get("timestamp")).ifPresent(e -> {
            if (NumberUtils.isNumber(e)) {
                req.setTimestamp(Long.valueOf(e));
            }
        });
        Optional.ofNullable(fieldMap.get("version")).ifPresent(e -> req.setVersion(e));
        Optional.ofNullable(fieldMap.get("nonce_str")).ifPresent(e -> {
            req.setNonceStr(URLEncodeUtils.decode(e, "UTF-8"));
        });
        Optional.ofNullable(fieldMap.get("biz_content")).ifPresent(e -> {
            // URL DECODE
            req.setBizContent(Json.from(URLEncodeUtils.decode(e, "UTF-8"), clazz));
        });
        return req;
    }

    public static Map<String, String> getSSMapFromKvPairs(String reqBody) {
        String[] fields = reqBody.split("&");
        Map<String, String> fieldMap = new HashMap<>();
        for (String field : fields) {
            String[] pair = field.split("=");
            if (pair.length != 2) {
                continue;
            }

            fieldMap.put(pair[0], pair[1]);
        }
        return fieldMap;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public T getBizContent() {
        return bizContent;
    }

    public void setBizContent(T bizContent) {
        this.bizContent = bizContent;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
