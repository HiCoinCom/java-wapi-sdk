package com.githup.hicoincom.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author ZPZ
 * @version 1.0
 * 充值通知結果返回
 * @date 2021-01-29 17:57
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
