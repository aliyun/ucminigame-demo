package cn.uc.auth;

import cn.uc.auth.client.UcAuthClient;
import cn.uc.auth.request.Code2SessionRequest;
import cn.uc.auth.response.Code2SessionResponse;

public class Code2SessionMainTest {

    /**
     * Code2Session 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        // 填入开放平台分配的登录授权参数
        UcAuthClient client = new UcAuthClient("https://open-auth.uc.cn", "appId", "clientId", "clientKey");
        Code2SessionRequest request = new Code2SessionRequest();

        // 通过游戏前端通过 uc.login() 获取到的 code、并请求得到 session
        request.setCode("test_code");
        Code2SessionResponse response = client.code2Session(request);

        System.out.println("success:" + Code2SessionResponse.isSuccess(response));
        System.out.println(response);
    }

}
