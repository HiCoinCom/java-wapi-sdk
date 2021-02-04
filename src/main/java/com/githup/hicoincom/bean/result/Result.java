package com.githup.hicoincom.bean.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ZPZ
 * 通用返回结果处理类
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS_CODE = "0";
    /**
     * 0：成功；其它为失败
     */
    private String code;
    private String msg;
    private String sign;
    @JSONField(deserialize = false)
    private T data;
    private String rawData;

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data != null ? this.data : JSONObject.parseObject(rawData, getGenericType());
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setData(String rawData) {
        this.data = JSONObject.parseObject(rawData, getGenericType());
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.getCode());
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    protected Class<T> getGenericType() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        throw new RuntimeException();
    }
}
