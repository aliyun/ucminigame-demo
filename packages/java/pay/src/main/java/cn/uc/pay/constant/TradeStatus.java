package cn.uc.pay.constant;

import java.util.Arrays;

/**
 * 支付订单状态
 */
public enum TradeStatus {

    /**
     * 初始化，预下单
     */
    PENDING(0),

    /**
     * 成功
     */
    SUCCESS(2),
    /**
     * 失败
     */
    FAILURE(3),

    /**
     * 预下单成功，待支付
     */
    PAYING(7);

    private final int code;

    private TradeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TradeStatus getByCode(int code) {
        return Arrays.stream(values())
            .filter(status -> status.getCode() == code)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No such TradeStatus code: " + code));
    }
}
