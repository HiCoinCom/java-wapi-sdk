package com.githup.hicoincom.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 充值通知結果返回
 *
 * @author ZPZ
 * @version 1.0
 **/
public class DepositNotifyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "request_id")
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
