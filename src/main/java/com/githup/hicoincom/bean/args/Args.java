package com.githup.hicoincom.bean.args;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZPZ
 */
public class Args implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name ="app_id")
    private String appId;
    @JSONField(name ="data")
    private String data;
    private String sign;
    private Long time;

    public Args(){}

    public Args(String appId, String data,String sign){
        this.appId = appId;
        this.data = data;
        this.sign =sign;
        this.time =System.currentTimeMillis();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Map<String, String> toMap(){
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("app_id", this.appId);
        map.put("data",     this.data);
        map.put("sign",     this.sign);
        map.put("time",     this.time.toString());
        return map;
    }

    @Override
    public String toString(){
        String s = String.format("app_id=%s&data=%s&sign=%s&time=%s", this.appId, this.data,this.sign,this.time);
        return s;
    }
}
