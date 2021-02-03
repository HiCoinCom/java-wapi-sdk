package com.githup.hicoincom.crypto;

/**
* @Description:   对RSA工具的进一步地封装
* @Author: ZPZ
*/
public interface IDataCrypto {
    String decode(String cipher);
    String encode(String raw);
    String sign(String data);
    boolean checkSign(String data,String sign);
}
