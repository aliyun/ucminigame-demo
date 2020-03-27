package cn.uc.pay.utils.http;

/**
 * 对第三方调用异常的一个抽象
 */
public interface IThirdPartyException extends IDetailedException {

    /**
     * 第三方名称
     *
     * @return
     */
    String getThirdPartyName();
}
