package cn.uc.pay.constant;

import java.util.Arrays;

public enum PurchasePlatform {

    /**
     * 安卓系统
     */
    ANDROID(1);

    /**
     * 支付平台码
     */
    private final int code;

    PurchasePlatform(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PurchasePlatform getByCode(int code) {
        return Arrays.stream(values())
            .filter(status -> status.getCode() == code)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No such PurchasePlatform code: " + code));
    }
}
