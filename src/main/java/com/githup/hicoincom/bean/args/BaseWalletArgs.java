package com.githup.hicoincom.bean.args;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZPZ
 */
public class BaseWalletArgs {
    protected String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toJson(){
        return JSONObject.toJSONString(this);
    }
}
