package com.githup.hicoincom.bean.args;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Description: 提币打回参数
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 14:20
 **/
public class WithdrawCancelArgs extends BaseWalletArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    private String msg;
    @JSONField(name = "trans_id")
    private Integer transId;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

}
