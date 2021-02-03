package com.githup.hicoincom;


import com.githup.hicoincom.api.impl.AddressRegisterService;
import com.githup.hicoincom.api.impl.DepositNotifyService;
import com.githup.hicoincom.api.impl.WithdrawService;
import com.githup.hicoincom.crypto.DataCrypto;
import com.githup.hicoincom.crypto.IDataCrypto;

/**
 * 工厂类
 */
public class WalletClientFactory {
    public static WalletClient CreateClient(WalletConfig cfg){
        DataCrypto crypto = new DataCrypto(cfg.getUserPrivateKey(), cfg.getWaasPublickKey());
        return CreateClient(cfg, crypto);
    }

    public static WalletClient CreateClient(WalletConfig cfg, IDataCrypto crypto){
        WalletClient client = new WalletClient();
        client.setAddressRegister(new AddressRegisterService(cfg, crypto));
        client.setDepositNotifyService(new DepositNotifyService(cfg, crypto));
        client.setWithdrawService(new WithdrawService(cfg, crypto));
        return client;
    }
}
