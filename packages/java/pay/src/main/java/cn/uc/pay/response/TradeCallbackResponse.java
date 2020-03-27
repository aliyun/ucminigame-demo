package cn.uc.pay.response;

import cn.uc.pay.utils.Json;

public class TradeCallbackResponse {

    /**
     * 0表示成功，非0表示失败
     */
    private Integer callbackCode;

    /**
     * 回调错误信息
     */
    private String callbackMsg;

    public static interface ResponseCode {
        int SUCCESS = 0;
        int FAIL = 1;
    }

    public static TradeCallbackResponse success() {
        return new TradeCallbackResponse(ResponseCode.SUCCESS, "ok");
    }

    public static TradeCallbackResponse fail(String errorMsg) {
        return new TradeCallbackResponse(ResponseCode.FAIL, errorMsg);
    }

    public TradeCallbackResponse() {
    }

    public TradeCallbackResponse(Integer callbackCode, String callbackMsg) {
        this.callbackCode = callbackCode;
        this.callbackMsg = callbackMsg;
    }

    public Integer getCallbackCode() {
		return callbackCode;
	}

	public void setCallbackCode(Integer callbackCode) {
		this.callbackCode = callbackCode;
	}

	public String getCallbackMsg() {
		return callbackMsg;
	}

	public void setCallbackMsg(String callbackMsg) {
		this.callbackMsg = callbackMsg;
	}

    @Override
    public String toString() {
        return Json.to(this);
    }

}
