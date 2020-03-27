package cn.uc.pay.utils.http;

import java.util.Map;

/**
 * 带有明细信息的异常
 */
public interface IDetailedException {

    /**
     * 第三方异常的详细信息，可选
     *
     * @return
     */
    String getMsgDetail();

    /**
     * 第三方异常详细信息的Map结构，可选
     *
     * @return
     */
    Map<String, String> getMsgDetailForMap();
}
