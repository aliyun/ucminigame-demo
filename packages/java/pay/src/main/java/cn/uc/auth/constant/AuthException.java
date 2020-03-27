package cn.uc.auth.constant;

import java.text.MessageFormat;

public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private static boolean isFillStack = true;

    private Integer httpStatusCode;
    private Integer code;
    private String message;
    private Throwable cause;

    protected AuthException(int httpStatusCode, int code, String message, Throwable e) {
        this(httpStatusCode, code, message);
        this.cause = e;
    }

    protected AuthException(int httpStatusCode, int code, String message) {
        super(message, null, isFillStack, isFillStack);
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.message = message;
    }

    private AuthException(ErrorCode errorCode, Object... messageArgs) {
        this(errorCode.getHttpStatusCode(),
            errorCode.getErrorCode(),
            MessageFormat.format(errorCode.getMessagePattern(), messageArgs));
    }

    public static AuthException throwError(int code, String message) {
        throw new AuthException(200, code, message);
    }

    public static AuthException throwError(ErrorCode errorCode, Object... messageArgs) {
        throw new AuthException(errorCode, messageArgs);
    }

    public static AuthException getError(ErrorCode errorCode, Object... messageArgs) {
        return new AuthException(errorCode, messageArgs);
    }

    public static void badParameter(String message) {
        throw new AuthException(ErrorCode.BAD_PARAMETER, message);
    }

    public static void invalidCode() {
        throw new AuthException(ErrorCode.INVALID_CODE);
    }

    public static void invalidSession() {
        throw new AuthException(ErrorCode.INVALID_SESSION);
    }

    public static void invalidRequestId() {
        throw new AuthException(ErrorCode.INVALID_REQUEST_ID);
    }

    public static void invalidSign() {
        throw new AuthException(ErrorCode.INVALID_SIGN);
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
