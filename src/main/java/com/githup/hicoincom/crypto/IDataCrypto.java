package com.githup.hicoincom.crypto;

/**
 * Further encapsulation of RSA tools
 *
 * @author ZPZ
 */
public interface IDataCrypto {
    /**
     * decode data
     *
     * @param cipher data to decode
     * @return java.lang.String
     */
    String decode(String cipher);

    /**
     * encode data
     *
     * @param raw data to encode
     * @return java.lang.String
     */
    String encode(String raw);

    /**
     * sign the data
     *
     * @param data  signed data
     * @return java.lang.String
     */
    String sign(String data);

    /**
     * verify sign
     *
     * @param data  raw data
     * @param sign sign data
     * @return boolean
     */
    boolean checkSign(String data, String sign);
}
