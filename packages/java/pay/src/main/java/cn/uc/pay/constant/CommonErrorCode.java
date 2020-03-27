package cn.uc.pay.constant;

public class CommonErrorCode {

    // 成功
    public static final CommonErrorCode OK = new CommonErrorCode(2000000, "OK");

    /**
     * 参数错误
     */
    public static final CommonErrorCode BAD_PARAMETER = new CommonErrorCode(4000001, "bad parameter: {0}");

    /**
     * 请求体错误
     */
    public static final CommonErrorCode INVALID_REQUEST_BODY = new CommonErrorCode(4000002, "bad reqeust body");

    /**
     * 内部错误
     */
    public static final CommonErrorCode INTERNAL_SERVER_ERROR = new CommonErrorCode(5000000,
        "internal server error, {0}");

    private int errorCode;
    private String messagePattern;

    protected CommonErrorCode() {}

    protected CommonErrorCode(int errorCode, String messagePattern) {
        this.errorCode = errorCode;
        this.messagePattern = messagePattern;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessagePattern() {
        return messagePattern;
    }
}
