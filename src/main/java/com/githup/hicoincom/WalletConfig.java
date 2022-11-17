package com.githup.hicoincom;

/**
 * @author ZPZ
 */
public class WalletConfig {
    /**
    * Merchant APP id , required parameter
    */
    private String appId;
    /**
     * User private key, required parameter
     */
    private String userPrivateKey;
    /**
     * Wallet service public key, required parameter
     */
    private String waasPublickKey;
    /**
     * Wallet service address, required parameter
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
