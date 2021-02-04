package com.githup.hicoincom.bean.args;

import java.io.Serializable;

/**
 * @author ZPZ
 * @version 1.0
 * 提币参数
 * @date 2021-01-28 14:20
 **/
public class WithdrawConsumeArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    public WithdrawConsumeArgs(String symbol) {
        this.symbol = symbol;
    }
}
