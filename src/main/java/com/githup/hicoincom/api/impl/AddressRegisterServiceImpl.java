package com.githup.hicoincom.api.impl;

import com.githup.hicoincom.WalletConfig;
import com.githup.hicoincom.api.BaseApi;
import com.githup.hicoincom.api.IAddressRegisterService;
import com.githup.hicoincom.api.urls.ApiUri;
import com.githup.hicoincom.bean.args.AddressInfoArgs;
import com.githup.hicoincom.bean.result.AddressCountInfoResult;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.crypto.IDataCrypto;
import com.githup.hicoincom.exception.ArgsNullException;

/**
 * implements for IAddressRegisterService
 *
 * @author ZPZ
 * @version 1.0
 **/
public class AddressRegisterServiceImpl extends BaseApi implements IAddressRegisterService {
    public AddressRegisterServiceImpl(WalletConfig cfg, IDataCrypto crypto) {
        super(cfg, crypto);
    }

    @Override
    public SimpleResult register(AddressInfoArgs args) {
        if (args == null) {
            throw new ArgsNullException("args can not be null");
        }
        return this.invoke(ApiUri.REGISTER_ADDRESS, args, SimpleResult.class);
    }

    @Override
    public AddressCountInfoResult checkAvailable(String symbol) {
        AddressInfoArgs args = new AddressInfoArgs();
        args.setSymbol(symbol);
        return this.invoke(ApiUri.CHECK_AVAILABLE_ADDRESS, args, AddressCountInfoResult.class);
    }
}
