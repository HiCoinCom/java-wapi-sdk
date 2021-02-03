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
 * @Description: 地址注册接口的实现
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 14:31
 **/
public class AddressRegisterService extends BaseApi implements IAddressRegisterService {
    public AddressRegisterService(WalletConfig cfg, IDataCrypto crypto) {
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
    public AddressCountInfoResult checkAailable(String symbol) {
        AddressInfoArgs args = new AddressInfoArgs();
        args.setSymbol(symbol);
        return this.invoke(ApiUri.CHECK_AVAILABLE_ADDRESS, args, AddressCountInfoResult.class);
    }
}
