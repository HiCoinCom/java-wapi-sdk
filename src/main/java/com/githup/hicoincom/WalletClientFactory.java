package com.githup.hicoincom;


import com.githup.hicoincom.api.impl.AddressRegisterServiceImpl;
import com.githup.hicoincom.api.impl.DepositNotifyServiceImpl;
import com.githup.hicoincom.api.impl.WithdrawServiceImpl;
import com.githup.hicoincom.crypto.DataCrypto;
import com.githup.hicoincom.crypto.IDataCrypto;

/**
 * 工厂类, 初始化
 *
 * @author ZPZ
 */
public class WalletClientFactory {
    public static WalletClient createClient(WalletConfig cfg) {
        DataCrypto crypto = new DataCrypto(cfg.getUserPrivateKey(), cfg.getWaasPublickKey());
        return createClient(cfg, crypto);
    }

    public static WalletClient createClient(WalletConfig cfg, IDataCrypto crypto) {
        WalletClient client = new WalletClient();
        client.setAddressRegister(new AddressRegisterServiceImpl(cfg, crypto));
        client.setDepositNotifyService(new DepositNotifyServiceImpl(cfg, crypto));
        client.setWithdrawService(new WithdrawServiceImpl(cfg, crypto));
        return client;
    }
}
