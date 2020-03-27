package cn.uc.pay.constant;

public class BizErrorCode extends CommonErrorCode {

    public static final BizErrorCode NO_MERCHANT = new BizErrorCode(2000106, "biz_id错误，商户不存在");

    public static final BizErrorCode UNSUPPORTED_PAY_TYPE = new BizErrorCode(4030102, "不支持的支付类型, pay_type = {0}");

    public static final BizErrorCode THIRD_SYSTEM_ERROR = new BizErrorCode(4060113, "第三方系统错误");

    public static final BizErrorCode THIRD_PAY_FAIL = new BizErrorCode(4060122, "第三方返回失败, errorMsg = {0}");

    private BizErrorCode(int errorCode, String messagePattern) {
        super(errorCode, messagePattern);
    }

}
