package com.githup.hicoincom.api.impl;

import com.githup.hicoincom.WalletConfig;
import com.githup.hicoincom.api.BaseApi;
import com.githup.hicoincom.api.IWithdrawService;
import com.githup.hicoincom.api.urls.ApiUri;
import com.githup.hicoincom.bean.args.WithdrawCancelArgs;
import com.githup.hicoincom.bean.args.WithdrawConsumeArgs;
import com.githup.hicoincom.bean.args.WithdrawNotifyArgs;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.bean.result.WithdrawConsumeResult;
import com.githup.hicoincom.crypto.IDataCrypto;
import com.githup.hicoincom.exception.ArgsNullException;
import org.apache.commons.lang3.StringUtils;

/**
 * implements for IWithdrawService
 *
 * @author ZPZ
 * @version 1.0
 **/
public class WithdrawServiceImpl extends BaseApi implements IWithdrawService {
    public WithdrawServiceImpl(WalletConfig cfg, IDataCrypto crypto) {
        super(cfg, crypto);
    }

    @Override
    public WithdrawConsumeResult withdrawConsume(String symbol) {
        if (StringUtils.isBlank(symbol)) {
            throw new ArgsNullException("args can not be null");
        }
        return this.invoke(ApiUri.WITHDRAW_CONSUME, new WithdrawConsumeArgs(symbol), WithdrawConsumeResult.class);
    }

    @Override
    public SimpleResult withdrawNotify(WithdrawNotifyArgs args) {
        if (args == null) {
            throw new ArgsNullException("args can not be null");
        }
        return this.invoke(ApiUri.WITHDRAW_NOTIFY, args, SimpleResult.class);
    }

    @Override
    public SimpleResult withdrawCancel(WithdrawCancelArgs args) {
        if (args == null) {
            throw new ArgsNullException("args can not be null");
        }
        return this.invoke(ApiUri.WITHDRAW_CANCEL, args, SimpleResult.class);
    }
}
