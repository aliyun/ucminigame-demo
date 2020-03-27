package cn.uc.pay.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ThirdHttpClient {

    /**
     *
     * @param urlStr
     * @param connectTimeout
     * @param socketTimeout
     * @param parser
     * @return
     */
    public static <T> T doHttpPostWithFormAndParse(String urlStr,
                                                   int connectTimeout,
                                                   int socketTimeout,
                                                   String requestBody,
                                                   Function<ThirdHttpCalling, T> parser) {
        ThirdHttpCalling calling = doHttpPostWithForm(urlStr,
            connectTimeout,
            socketTimeout,
            requestBody,
            new DefaultHttpResponsePredicate()
        );
        return parser.apply(calling);
    }

    /**
     *
     * @param urlStr         URL
     * @param connectTimeout 超时时间，以毫秒计算
     * @param socketTimeout  超时时间，以毫秒计算
     *
     * @return
     */

    /**
     *
     * @param urlStr
     * @param connectTimeout
     * @param socketTimeout
     * @param requestBody
     * @return
     */
    public static ThirdHttpCalling doHttpPostWithForm(String urlStr,
                                                  int connectTimeout,
                                                  int socketTimeout,
                                                  String requestBody) {
        return doHttpPostWithForm(urlStr,
                connectTimeout,
                socketTimeout,
                requestBody,
                new DefaultHttpResponsePredicate()
        );
    }

    public static ThirdHttpCalling doHttpPostWithForm(String urlStr,
                                                  int connectTimeout,
                                                  int socketTimeout,
                                                  String requestBody,
                                                  Predicate<ThirdHttpCalling> errHttpResponsePredicate) {
        try {
            return Request.Post(urlStr)
                    .connectTimeout(connectTimeout)
                    .socketTimeout(socketTimeout)
                    .bodyString(requestBody, ContentType.APPLICATION_FORM_URLENCODED.withCharset(Charset.forName("utf-8")))
                    .execute()
                    .handleResponse(resp -> handleHttpResponse(urlStr, resp, errHttpResponsePredicate));
        } catch (ThirdPartyServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ThirdPartyCallException(
                    getThirdPartyName(),
                    urlStr,
                    "",
                    e.getClass().getName(),
                    e.getMessage()
            );
        }
    }

    public static ThirdHttpCalling doHttpGet(String urlStr,
                               int connectTimeout,
                               int socketTimeout) {
        return doHttpGet(
                urlStr,
                connectTimeout,
                socketTimeout,
                new DefaultHttpResponsePredicate()
        );
    }


    public static ThirdHttpCalling doHttpGet(String urlStr,
                               int connectTimeout,
                               int socketTimeout,
                               Predicate<ThirdHttpCalling> errHttpResponsePredicate) {
        try {
            return Request.Get(urlStr)
                    .connectTimeout(connectTimeout)
                    .socketTimeout(socketTimeout)
                    .execute()
                    .handleResponse(resp -> handleHttpResponse(urlStr, resp, errHttpResponsePredicate));
        } catch (ThirdPartyServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ThirdPartyCallException(
                    getThirdPartyName(),
                    urlStr,
                    "",
                    e.getClass().getName(),
                    e.getMessage()
            );
        }
    }

    /**
     * 对Http Response进行解析，子类可以根据具体进行重写
     *
     * @param queryUrlStr URL
     * @param response    Http响应
     * @return
     */
    public static ThirdHttpCalling handleHttpResponse(String queryUrlStr,
                                                  HttpResponse response,
                                                  Predicate<ThirdHttpCalling> predicate) {
        if (response == null) {
            return null;
        }

        ThirdHttpCalling calling = new ThirdHttpCalling();
        calling.setUrl(queryUrlStr);

        // 先检查响应码
        int statusCode = Optional.ofNullable(response)
                .map(resp -> resp.getStatusLine())
                .map(statusLine -> statusLine.getStatusCode())
                .orElse(0);

        calling.setStatusCode(statusCode);

        HttpEntity entity = response.getEntity();

        if (entity == null) {
            throw new ThirdPartyParseException(
                    getThirdPartyName(),
                    queryUrlStr,
                    "null_entity",
                    "status_code:" + statusCode,
                    ""
            );
        }

        String entityStr = "";

        try {
            entityStr = EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new ThirdPartyParseException(
                    getThirdPartyName(),
                    queryUrlStr,
                    "get_entity_fail",
                    "status_code:" + statusCode,
                    ""
            );
        }

        calling.setEntityStr(entityStr);


        if (predicate != null) {
            if (!predicate.test(calling)) {
                throw new ThirdPartyCallException(
                        getThirdPartyName(),
                        queryUrlStr,
                        entityStr,
                        "http_calling_test_fail",
                        "status_code:" + statusCode
                );
            }
        }

        return calling;
    }

    private static String getThirdPartyName() {
        return "UC-PAY-GATEWAY";
    }

    /**
     * 默认的Http Response异常判断逻辑，默认为2XX为正常响应，其余均为异常情况
     */
    public static class DefaultHttpResponsePredicate implements Predicate<ThirdHttpCalling> {

        @Override
        public boolean test(ThirdHttpCalling httpResponse) {
            int statusCode = httpResponse.getStatusCode();

            // 非正常响应，抛出第三方调用异常
            if (statusCode <= 0 || statusCode >= 300) {
                return false;
            }

            return true;
        }
    }
}
