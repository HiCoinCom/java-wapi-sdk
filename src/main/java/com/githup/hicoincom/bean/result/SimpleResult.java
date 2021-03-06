package com.githup.hicoincom.bean.result;

import com.githup.hicoincom.bean.NullData;

import java.io.Serializable;

/**
 * 调用返回结果，返回的data属性无数据
 *
 * @author ZPZ
 * @version 1.0
 **/
public class SimpleResult extends Result<NullData> implements Serializable {
    private static final long serialVersionUID = 1L;
}
