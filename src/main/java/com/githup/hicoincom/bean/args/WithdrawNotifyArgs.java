package com.githup.hicoincom.bean.args;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 充值接口所需的参数
 *
 * @author ZPZ
 * @version 1.0
 **/
public class WithdrawNotifyArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "address_from")
    private String addressFrom;
    @JSONField(name = "address_to")
    private String addressTo;
    private String txid;
    @JSONField(name = "trans_id")
    private Integer transId;
    private BigDecimal amount;
    private BigDecimal fee;
    private int confirm;
    private BigDecimal balance;
    private String memo;


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

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
