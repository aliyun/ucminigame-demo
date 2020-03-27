package cn.uc.auth.constant;

public enum ErrorCode {

    BAD_PARAMETER(400, 400001, "Bad Parameter: [{0}]"),

    INVALID_CODE(400, 400003, "Invalid Code"),

    INVALID_SESSION(400, 400004, "Invalid Session"),

    INVALID_REQUEST_ID(400, 400005, "Invalid Request Id"),

    INVALID_SIGN(400, 400006, "Invalid Sign");

    private int httpStatusCode;
    private int errorCode;
    private String messagePattern;

    ErrorCode(int httpStatusCode, int errorCode, String messagePattern) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.messagePattern = messagePattern;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessagePattern() {
        return messagePattern;
    }
}
