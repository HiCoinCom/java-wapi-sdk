package com.githup.hicoincom.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.githup.hicoincom.bean.args.BaseWalletArgs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 提币返回值
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 14:20
 **/
public class WithdrawConsumeInfo extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "trans_id")
    private Integer transId;
    private String symbol;
    @JSONField(name = "address_to")
    private String addressTo;
    private BigDecimal amount;
    private String memo;

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
