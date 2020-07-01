package cn.uc.pay.constant;

import java.util.Arrays;

public enum PayType {

    UNCERTAIN_PAY("001");

    private final String code;

    PayType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PayType getByCode(String code) {
        return Arrays.stream(values())
            .filter(type -> type.getCode().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No such PayType type: " + code));
    }
}
