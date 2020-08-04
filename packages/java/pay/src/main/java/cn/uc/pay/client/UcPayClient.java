package cn.uc.pay.client;

import cn.uc.pay.constant.UcPayConstants;
import cn.uc.pay.request.CommonOpenRequest;
import cn.uc.pay.request.PreCreateTradeRequest;
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

    private String url;

    public UcPayClient(String url) {
        this.url = url;
    }

    public UcPayResponse<PreCreateTradeResponse> preCreateTrade(UcGameApp gameApp, PreCreateTradeRequest request) {
        CommonOpenRequest<String> ucPayRequest = new CommonOpenRequest<>();
        ucPayRequest.setAppId(gameApp.getAppId());
        ucPayRequest.setClientId(gameApp.getClientId());
        ucPayRequest.setNonceStr(UUID.id());
        ucPayRequest.setSignType(UcPayConstants.SIGNTYPE_MD5);
        ucPayRequest.setVersion(UcPayConstants.VERSION);
        ucPayRequest.setTimestamp(System.currentTimeMillis());
        ucPayRequest.setMethodName("/trade/precreate");
        ucPayRequest.setBizContent(Json.to(request));
        Map<String, String> requestNameValuePairs = Json.from(Json.to(ucPayRequest),
            new TypeReference<Map<String, String>>() {});
        requestNameValuePairs.forEach((key, value) -> requestNameValuePairs.put(key, URLEncodeUtils.encode(value)));
        String sign = SignUtils.openSign(gameApp.getClientKey(), requestNameValuePairs);
        requestNameValuePairs.put(UcPayConstants.FIELD_SIGN, sign);

        List<String> kvPairs = SignUtils.getKVList(requestNameValuePairs);
        String requestBody = StringUtils.join(kvPairs, "&");
        ThirdHttpCalling result = ThirdHttpClient.doHttpPostWithForm(url , 3000, 3000,
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
