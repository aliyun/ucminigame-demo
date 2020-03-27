package cn.uc.pay.response;

import cn.uc.pay.constant.CommonErrorCode;
import cn.uc.pay.utils.Json;

import java.util.Objects;

public class UcPayResponse<T> {

    private Integer code;

    private String msg;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Boolean isSuccess(UcPayResponse response) {
        return response != null && Objects.equals(response.getCode(), CommonErrorCode.OK.getErrorCode());
    }

    @Override
    public String toString() {
        return Json.to(this);
    }
}
