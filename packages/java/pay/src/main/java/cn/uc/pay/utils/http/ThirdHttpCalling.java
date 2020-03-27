package cn.uc.pay.utils.http;

public class ThirdHttpCalling {

    private String url;

    private int statusCode;

    private String entityStr;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getEntityStr() {
        return entityStr;
    }

    public void setEntityStr(String entityStr) {
        this.entityStr = entityStr;
    }
}
