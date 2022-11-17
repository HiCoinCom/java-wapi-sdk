package com.githup.hicoincom.bean.args;

import java.io.Serializable;

/**
 * obtain the parameters of the withdrawal list
 *
 * @author ZPZ
 * @version 1.0
 **/
public class WithdrawConsumeArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    public WithdrawConsumeArgs(String symbol) {
        this.symbol = symbol;
    }
}
