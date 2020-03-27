package cn.uc.pay.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public final class SignUtils {

    /**
     * 服务端接口加签校验
     * 按照 key 升序排列，拼接 key1=value1&key2=value2 ( value 为 null 跳过, value 需要 url encode ), 在后面拼接上 &app_key=xxx，md5 生成 sign 值
     *
     * @param appKey
     * @param params
     * @return
     */
    public static String openSign(String appKey, Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            params = new HashMap<String, String>(1);
        }
        Map<String, String> sortedParams = new TreeMap<>(params);
        List<String> kvPairList = getKVList(sortedParams);
        String sourceText = StringUtils.join(kvPairList, "&");
        sourceText = sourceText + "&app_key=" + appKey;
        return DigestUtils.md5(sourceText);
    }

    /**
     * 验签
     *
     * @param appKey
     * @param sign
     * @param reqBody
     * @return
     */
    public static boolean checkOpenSign(String appKey, String sign, String reqBody) {
        String[] fields = reqBody.split("&");
        Map<String, String> fieldMap = new HashMap<>();
        for (String field : fields) {
            String[] pair = field.split("=");
            if (pair.length != 2) {
                continue;
            }
            // sign 字段不参与校验，空字段不校验
            if (!"sign".equals(pair[0]) && StringUtils.isNotBlank(pair[1])) {
                fieldMap.put(pair[0], pair[1]);
            }
        }
        String generateSign = openSign(appKey, fieldMap);
        return Objects.equals(generateSign, sign);
    }

    public static List<String> getKVList(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return Collections.emptyList();
        }
        List<String> kvPairs = new ArrayList<>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) {
                continue;
            }
            String kv = key + "=" + value;
            kvPairs.add(kv);
        }
        return kvPairs;
    }

    private SignUtils() {}


	/**
	 * 认证服务接口加签校验
     * 按照 key 升序排列，拼接 key1=value1&key2=value2 ( value 为 null 跳过, value 需要 url encode )
     * $client_id + $client_key + $request_id + $sourceText，md5 生成 sign 值
     *
	 * @param clientKey
	 * @param params
	 * @return
	 */
	public static String authSign(String clientId, String clientKey, String requestId,Map<String, String> params) {
		if(MapUtils.isEmpty(params)) {
			params = new HashMap<String, String>(1);
		}
		Map<String, String> sortedParams = new TreeMap<>(params);
		List<String> kvPairList = getKVList(sortedParams);
		StringBuilder sourceText = new StringBuilder();
		sourceText.append(clientId).append(clientKey).append(requestId).append(StringUtils.join(kvPairList, "&"));
		return DigestUtils.md5(sourceText.toString());
	}
}
