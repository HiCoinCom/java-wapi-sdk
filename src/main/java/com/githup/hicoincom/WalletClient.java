package com.githup.hicoincom;


import com.githup.hicoincom.api.IAddressRegisterService;
import com.githup.hicoincom.api.IDepositNotifyService;
import com.githup.hicoincom.api.IWithdrawService;

/**
 * Encapsulates all callable services
 *
 * @author ZPZ
 */
public class WalletClient {

    public WalletClient() {
    }

    private IAddressRegisterService addressRegister;
    private IDepositNotifyService depositNotifyService;
    private IWithdrawService withdrawService;

    public IAddressRegisterService getAddressRegister() {
        return addressRegister;
    }

    public void setAddressRegister(IAddressRegisterService addressRegister) {
        this.addressRegister = addressRegister;
    }

    public IDepositNotifyService getDepositNotifyService() {
        return depositNotifyService;
    }

    public void setDepositNotifyService(IDepositNotifyService depositNotifyService) {
        this.depositNotifyService = depositNotifyService;
    }

    public IWithdrawService getWithdrawService() {
        return withdrawService;
    }

    public void setWithdrawService(IWithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }
}
