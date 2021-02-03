package com.githup.hicoincom.bean.args;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 充值接口所需的参数
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 14:20
 **/
public class DepositArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "request_id")
    private String requestId;
    @JSONField(name = "address_from")
    private String addressFrom;
    @JSONField(name = "address_to")
    private String addressTo;
    private String txid;
    private BigDecimal amount;
    private int confirm;
    private BigDecimal balance;
    private int status;
    private String memo;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
