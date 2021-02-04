package com.githup.hicoincom.api.urls;

/**
 * @author ZPZ
 */
public enum ApiUri {

    /**
     * 推送地址给平台，此地址用于分配给用户
     */
    REGISTER_ADDRESS("address/register", "POST"),

    /**
     * 推送的地址中还有多少可用地址
     */
    CHECK_AVAILABLE_ADDRESS("address/available", "POST"),

    /**
     * 充值通知调用接口
     */
    DEPOSIT_NOTIFY("deposit/notify", "POST"),

    /**
     * 提币拉取接口
     */
    WITHDRAW_CONSUME("withdraw/consume", "POST"),

    /**
     * 提现通知调用接口
     */
    WITHDRAW_NOTIFY("withdraw/notify", "POST"),

    /**
     * 内部通知调用接口
     */
    INTERNAL_NOTIFY("internal/notify", "POST"),

    /**
     * 提币打回接口
     */
    WITHDRAW_CANCEL("withdraw/cancel", "POST"),

    ;

    private String value;
    private String method;

    ApiUri(String value, String method) {
        this.value = value;
        this.method = method;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
