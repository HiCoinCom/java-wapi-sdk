package com.githup.hicoincom.bean.args;

import java.io.Serializable;

/**
 * 获取提币列表参数
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
