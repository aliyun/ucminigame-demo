package cn.uc.auth.response;

import java.util.function.Predicate;

import cn.uc.pay.utils.Json;
import cn.uc.pay.utils.http.ThirdHttpCalling;

public class Code2SessionResponse {

    private Code2SessionResponse.Error error;

    private Code2SessionResponse.Data data;

    public static Boolean isSuccess(Code2SessionResponse response) {
        return response != null && response.getError() == null;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return Json.to(this);
    }

    public static class AuthHttpResponsePredicate implements Predicate<ThirdHttpCalling> {

        @Override
        public boolean test(ThirdHttpCalling httpResponse) {
            int statusCode = httpResponse.getStatusCode();

            // 非正常响应，抛出第三方调用异常
            if (statusCode <= 0 || statusCode >= 600) {
                return false;
            }

            return true;
        }
    }

    private class Data {
        private String openId;

        private String sessionKey;

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }
    }

    private class Error {
        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}