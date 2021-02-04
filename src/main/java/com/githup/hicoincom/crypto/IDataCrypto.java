package com.githup.hicoincom.crypto;

/**
 * @author ZPZ
 * 对RSA工具的进一步地封装
 */
public interface IDataCrypto {
    /**
     * decode data
     *
     * @param cipher
     * @return java.lang.String
     * @author ZPZ
     */
    String decode(String cipher);

    /**
     * encode data
     *
     * @param raw
     * @return java.lang.String
     * @author ZPZ
     */
    String encode(String raw);

    /**
     * sign the data
     *
     * @param data
     * @return java.lang.String
     * @author ZPZ
     */
    String sign(String data);

    /**
     * verify sign
     *
     * @param data
     * @param sign
     * @return boolean
     * @author ZPZ
     */
    boolean checkSign(String data, String sign);
}
