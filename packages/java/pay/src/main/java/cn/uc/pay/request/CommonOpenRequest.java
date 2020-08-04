package cn.uc.pay.request;

import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.URLEncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by shaoqiang.ysq on 2019/12/21.
 *
 * @author shaoqiang.ysq
 */
public class CommonOpenRequest<T> {
    private String appId;

    private String clientId;

    private String sign;

    private String signType;

    private Long timestamp;

    private String version;

    private String nonceStr;

    private String methodName;

    private T bizContent;

    public CommonOpenRequest() {
        super();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public T getBizContent() {
        return bizContent;
    }

    public void setBizContent(T bizContent) {
        this.bizContent = bizContent;
    }

    public static <T> CommonOpenRequest<T> build(String reqBody, Class<T> clazz) {
        if (StringUtils.isBlank(reqBody)) {
            return null;
        }

        // TODO: 防止恶意传入&
        Map<String, String> fieldMap = getSSMapFromKvPairs(reqBody);

        CommonOpenRequest<T> req = new CommonOpenRequest<T>();
        Optional.ofNullable(fieldMap.get("app_id")).ifPresent(e -> req.setAppId(e));
        Optional.ofNullable(fieldMap.get("client_id")).ifPresent(e -> req.setClientId(e));
        Optional.ofNullable(fieldMap.get("sign")).ifPresent(e -> req.setSign(e));
        Optional.ofNullable(fieldMap.get("sign_type")).ifPresent(e -> req.setSignType(e));
        Optional.ofNullable(fieldMap.get("timestamp")).ifPresent(e -> {
            if (NumberUtils.isNumber(e)) {
                req.setTimestamp(Long.valueOf(e));
            }
        });
        Optional.ofNullable(fieldMap.get("version")).ifPresent(e -> req.setVersion(e));
        Optional.ofNullable(fieldMap.get("nonce_str")).ifPresent(e -> {
            req.setNonceStr(e);
        });
        Optional.ofNullable(fieldMap.get("method_name")).ifPresent(e -> req.setMethodName(e));
        Optional.ofNullable(fieldMap.get("biz_content")).ifPresent(e -> {
            req.setBizContent(Json.from(e, clazz));
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
            if (StringUtils.isNotBlank(pair[1])) {
                pair[1] = URLEncodeUtils.decode(pair[1], "UTF-8");
            }
            fieldMap.put(pair[0], pair[1]);
        }
        return fieldMap;
    }
}
