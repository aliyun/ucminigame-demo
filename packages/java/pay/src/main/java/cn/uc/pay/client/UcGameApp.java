package cn.uc.pay.client;

public class UcGameApp {

    /**
     *  小游戏开放平台分配
     */
    private String clientKey;

    private String appId;

    private String clientId;

    public UcGameApp(String clientKey, String appId, String clientId) {
        this.clientKey = clientKey;
        this.appId = appId;
        this.clientId = clientId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
