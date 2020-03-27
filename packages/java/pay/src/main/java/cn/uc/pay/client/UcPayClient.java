package cn.uc.pay.client;

import cn.uc.pay.constant.UcPayConstants;
import cn.uc.pay.request.PreCreateTradeRequest;
import cn.uc.pay.request.UcPayRequest;
import cn.uc.pay.response.PreCreateTradeResponse;
import cn.uc.pay.response.UcPayResponse;
import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.SignUtils;
import cn.uc.pay.utils.URLEncodeUtils;
import cn.uc.pay.utils.UUID;
import cn.uc.pay.utils.http.ThirdHttpCalling;
import cn.uc.pay.utils.http.ThirdHttpClient;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class UcPayClient {

    private String host;

    private String appKey;

    private String bizId;

    private String clientId;

    public UcPayClient() {
    }

    public UcPayClient(String host, String appKey, String bizId, String clientId) {
        this.host = host;
        this.appKey = appKey;
        this.bizId = bizId;
        this.clientId = clientId;
    }

    public UcPayResponse<PreCreateTradeResponse> preCreateTrade(PreCreateTradeRequest request) {
        UcPayRequest<String> ucPayRequest = new UcPayRequest<>();
        ucPayRequest.setBizId(bizId);
        ucPayRequest.setClientId(clientId);
        ucPayRequest.setNonceStr(UUID.id());
        ucPayRequest.setSignType(UcPayConstants.SIGNTYPE_MD5);
        ucPayRequest.setVersion(UcPayConstants.VERSION);
        ucPayRequest.setTimestamp(System.currentTimeMillis());
        ucPayRequest.setBizContent(URLEncodeUtils.encode(Json.to(request)));
        Map<String, String> requestNameValuePairs = Json.from(Json.to(ucPayRequest),
            new TypeReference<Map<String, String>>() {});
        String sign = SignUtils.openSign(appKey, requestNameValuePairs);
        requestNameValuePairs.put(UcPayConstants.FIELD_SIGN, sign);

        List<String> kvPairs = SignUtils.getKVList(requestNameValuePairs);
        String requestBody = StringUtils.join(kvPairs, "&");
        ThirdHttpCalling result = ThirdHttpClient.doHttpPostWithForm(host + UcPayConstants.PRECREATE_URI, 3000, 3000,
            requestBody);
        UcPayResponse<PreCreateTradeResponse> response = null;
        if(result!= null && result.getStatusCode() == HttpStatus.SC_OK) {
            String resBody = result.getEntityStr();
            response = Json.from(resBody, new TypeReference<UcPayResponse<PreCreateTradeResponse>>(){});
        }
        if(response != null && UcPayResponse.isSuccess(response)) {
            System.out.println("precreate success, response======\n" + response.getData());
        }
        return response;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
