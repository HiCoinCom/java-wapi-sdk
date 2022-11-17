package com.githup.hicoincom.api.urls;

/**
 * @author ZPZ
 */
public enum ApiUri {

    /**
     * Push the address to the platform, this address is used to assign to the user
     */
    REGISTER_ADDRESS("address/register", "POST"),

    /**
     * How many addresses are available
     */
    CHECK_AVAILABLE_ADDRESS("address/available", "POST"),

    /**
     * Deposit notification interface
     */
    DEPOSIT_NOTIFY("deposit/notify", "POST"),

    /**
     * Withdrawal records pull interface
     */
    WITHDRAW_CONSUME("withdraw/consume", "POST"),

    /**
     * Withdrawal notification interface
     */
    WITHDRAW_NOTIFY("withdraw/notify", "POST"),

    /**
     * Internal notification interface
     */
    INTERNAL_NOTIFY("internal/notify", "POST"),

    /**
     * Withdrawal rejection interface
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
