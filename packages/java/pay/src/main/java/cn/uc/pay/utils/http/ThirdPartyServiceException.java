package cn.uc.pay.utils.http;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ThirdPartyServiceException extends RuntimeException implements IThirdPartyException {

    private static final long serialVersionUID = 2532950673174819424L;

    /**
     * 这是一个错误编码，以较为简短的形式表示当前的问题
     */
    private String code;
    private String msg;

    /**
     * 第三方名称
     */
    private String thirdPartyName = "";

    /**
     * 调用地址，可作具体的日志记录
     */
    private String callUri;

    /**
     * 原始响应内容
     */
    private String response;

    /**
     * 其它详细数据，本来可以直接用Exception的detailMessage，但CoralBaseException没有留口子，就放在这里进行存储了
     */
    private String detailMessage;

    public ThirdPartyServiceException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param thirdPartyName 第三方名称
     * @param callUri        调用uri
     * @param response       响应内容
     * @param promptMsg      对用户的提示信息
     */
    public ThirdPartyServiceException(String thirdPartyName,
                                      String callUri,
                                      String response,
                                      String promptMsg,
                                      String detailMessage) {
        super(promptMsg);
        this.msg = promptMsg;
        this.thirdPartyName = thirdPartyName;
        this.callUri = callUri;
        this.response = response;
        this.detailMessage = detailMessage;
    }

    /**
     * @param thirdPartyName 第三方名称
     * @param callUri        调用uri
     * @param response       响应内容
     * @param cause          外部发生的其它异常
     * @param promptMsg      对用户的提示信息
     */
    public ThirdPartyServiceException(String thirdPartyName,
                                      String callUri,
                                      String response,
                                      Throwable cause,
                                      String promptMsg,
                                      String detailMessage) {
        super(cause);
        this.msg = promptMsg;
        this.thirdPartyName = thirdPartyName;
        this.callUri = callUri;
        this.response = response;
        this.detailMessage = detailMessage;
    }

    public ThirdPartyServiceException(String thirdPartyName,
                                      String callUri,
                                      String response,
                                      String code,
                                      String promptMsg,
                                      String detailMessage) {
        super();
        this.code = code;
        this.msg = promptMsg;
        this.thirdPartyName = thirdPartyName;
        this.callUri = callUri;
        this.response = response;
        this.detailMessage = detailMessage;
    }

    public ThirdPartyServiceException(String thirdPartyName,
                                      String callUri,
                                      String response,
                                      Throwable cause,
                                      String code,
                                      String promptMsg,
                                      String detailMessage) {
        super(cause);
        this.code = code;
        this.msg = promptMsg;
        this.thirdPartyName = thirdPartyName;
        this.callUri = callUri;
        this.response = response;
        this.detailMessage = detailMessage;
    }

    @Override
    public String getThirdPartyName() {
        return thirdPartyName;
    }

    @Override
    public String getMsgDetail() {
        return detailMessage;
    }

    @Override
    public Map<String, String> getMsgDetailForMap() {
        Map<String, String> ret = new HashMap<>();
        ret.put("third_party_name", thirdPartyName);
        ret.put("call_uri", callUri);
        ret.put("response", response);

        return ret;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isEmpty(detailMessage)) {
            return super.getMessage();
        } else {
            return detailMessage;
        }
    }
}
