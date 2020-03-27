package cn.uc.pay.utils.http;

/**
 * 第三方连接异常，专指第三方调用时，出现网络层调用异常，或者HTTP响应码异常等状况时，抛出此异常
 */
public class ThirdPartyCallException extends ThirdPartyServiceException {

    private static final long serialVersionUID = 6800933998523649996L;
    public static String CODE = "CORAL:THIRD_PARTY_CALL_ERROR";

    public ThirdPartyCallException(String thirdPartyName, String callUri, String response, String promptMsg, String detailMessage) {
        super(thirdPartyName, callUri, response, promptMsg, detailMessage);
    }

    public ThirdPartyCallException(String thirdPartyName, String callUri, String response, Throwable cause, String promptMsg, String detailMessage) {
        super(thirdPartyName, callUri, response, cause, promptMsg, detailMessage);
    }

    public ThirdPartyCallException(String thirdPartyName, String callUri, String response, String code, String msg, String detailMessage) {
        super(thirdPartyName, callUri, response, code, msg, detailMessage);
    }

    public ThirdPartyCallException(String thirdPartyName, String callUri, String response, Throwable cause, String code, String msg, String detailMessage) {
        super(thirdPartyName, callUri, response, cause, code, msg, detailMessage);
    }
}
