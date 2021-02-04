package com.githup.hicoincom;

/**
 * @author ZPZ
 */
public class WalletConfig {
    /**
    * 商户 APP id ,必选参数
    */
    private String appId;
    /**
     * 用户私钥 ,必选参数
     */
    private String userPrivateKey;
    /**
     * 钱包服务公钥 ,必选参数
     */
    private String waasPublickKey;
    /**
     * 钱包服务地址 ,必选参数
     */
    private String domain = "";
    private String version = "v1";
    private String charset = "utf-8";
    private Boolean enableLog = true;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserPrivateKey() {
        return userPrivateKey;
    }

    public void setUserPrivateKey(String userPrivateKey) {
        this.userPrivateKey = userPrivateKey;
    }

    public String getWaasPublickKey() {
        return waasPublickKey;
    }

    public void setWaasPublickKey(String waasPublickKey) {
        this.waasPublickKey = waasPublickKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Boolean getEnableLog() {
        return enableLog;
    }

    public void setEnableLog(Boolean enableLog) {
        this.enableLog = enableLog;
    }
}
