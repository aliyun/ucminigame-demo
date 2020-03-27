package cn.uc.auth.client;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.TypeReference;

import cn.uc.auth.constant.Code2SessionConstants;
import cn.uc.auth.request.Code2SessionRequest;
import cn.uc.auth.response.Code2SessionResponse;
import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.SignUtils;
import cn.uc.pay.utils.http.ThirdHttpCalling;
import cn.uc.pay.utils.http.ThirdHttpClient;
import org.apache.commons.lang3.StringUtils;

public class UcAuthClient {

    private String host;

    private String appId;

    private String clientId;

    private String clientKey;

    public UcAuthClient() {
    }

    public UcAuthClient(String host, String appId, String clientId, String clientKey) {
        this.host = host;
        this.appId = appId;
        this.clientId = clientId;
        this.clientKey = clientKey;
    }

    public Code2SessionResponse code2Session(Code2SessionRequest request) {
        Code2SessionRequest code2SessionRequest = new Code2SessionRequest(appId, clientId, request.getCode());
        Map<String, String> requestNameValuePairs = Json.from(Json.to(code2SessionRequest),
            new TypeReference<Map<String, String>>() {});
        String sign = SignUtils.authSign(clientId, clientKey, code2SessionRequest.getRequestId(),
            requestNameValuePairs);
        requestNameValuePairs.put(Code2SessionConstants.FIELD_SIGN, sign);

        List<String> kvPairs = SignUtils.getKVList(requestNameValuePairs);
        String requestBody = StringUtils.join(kvPairs, "&");
        ThirdHttpCalling result = ThirdHttpClient.doHttpPostWithForm(host + Code2SessionConstants.GET_SESSION_URI, 3000,
            3000,
            requestBody, new Code2SessionResponse.AuthHttpResponsePredicate());
        Code2SessionResponse response = null;
        if (result != null) {
            String resBody = result.getEntityStr();
            response = Json.from(resBody, new TypeReference<Code2SessionResponse>() {});

        }
        return response;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
