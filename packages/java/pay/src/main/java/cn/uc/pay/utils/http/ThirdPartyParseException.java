package cn.uc.pay.utils.http;

/**
 * 第三方响应解析异常，专指第三方调用时，响应内容与期望不一致，比如关键字段
 * 缺失，响应内容非JSON等
 */
public class ThirdPartyParseException extends ThirdPartyServiceException {

    private static final long serialVersionUID = 7947784901542975220L;

    public ThirdPartyParseException(String thirdPartyName, String callUri, String response, String promptMsg, String detailMessage) {
        super(thirdPartyName, callUri, response, promptMsg, detailMessage);
    }

    public ThirdPartyParseException(String thirdPartyName, String callUri, String response, Throwable cause, String promptMsg, String detailMessage) {
        super(thirdPartyName, callUri, response, cause, promptMsg, detailMessage);
    }

    public ThirdPartyParseException(String thirdPartyName, String callUri, String response, String code, String msg, String detailMessage) {
        super(thirdPartyName, callUri, response, code, msg, detailMessage);
    }

    public ThirdPartyParseException(String thirdPartyName, String callUri, String response, Throwable cause, String code, String msg, String detailMessage) {
        super(thirdPartyName, callUri, response, cause, code, msg, detailMessage);
    }
}
