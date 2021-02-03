package com.githup.hicoincom.bean.args;

import java.io.Serializable;

/**
 * @Description: 注册地址所需的参数
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 14:20
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
