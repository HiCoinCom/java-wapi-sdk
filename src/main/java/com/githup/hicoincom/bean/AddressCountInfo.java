package com.githup.hicoincom.bean;

import java.io.Serializable;

/**
 * 返回地址数量信息
 *
 * @author ZPZ
 * @version 1.0
 **/
public class AddressCountInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String symbol;
    private int count;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
