package com.githup.hicoincom.crypto;


import com.githup.hicoincom.crypto.rsa.XRsa;

/**
 * @author ZPZ
 */
public class DataCrypto implements IDataCrypto {
    /**
     * 加密数据的私钥
     **/
    String privateKey;
    /**
     * 解密数据的公钥
     **/
    String publicKey;

    public DataCrypto() {
    }

    public DataCrypto(String priv, String pub) {
        this.privateKey = priv;
        this.publicKey = pub;
    }

    @Override
    public String decode(String cipher) {
        return XRsa.privateDecrypt(cipher, XRsa.getRSAPrivateKey(this.privateKey));
    }

    @Override
    public String encode(String raw) {
        return XRsa.publicEncrypt(raw, XRsa.getRSAPublicKey(this.publicKey));
    }

    @Override
    public String sign(String data) {
        return XRsa.sign(data, XRsa.getRSAPrivateKey(this.privateKey));
    }

    @Override
    public boolean checkSign(String data, String sign) {
        return XRsa.verify(data, sign, XRsa.getRSAPublicKey(this.publicKey));
    }
}
