package com.githup.hicoincom.bean.args;

import java.io.Serializable;

/**
 * @author ZPZ
 * @version 1.0
 * 注册地址所需的参数
 * @date 2021-01-28 14:20
 **/
public class AddressInfoArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    private String addresses;
    private boolean memo;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public boolean isMemo() {
        return memo;
    }

    public void setMemo(boolean memo) {
        this.memo = memo;
    }
}
