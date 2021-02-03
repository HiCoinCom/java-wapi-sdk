package com.githup.hicoincom.crypto.rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class XRsa {
    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    public static final String RSA_ALGORITHM_SIGN = "SHA256WithRSA";
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public XRsa(String publicKey, String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);

            // 通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));

            this.publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
            // 通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            throw new RuntimeException("不支持的密钥: ", e);
        }
    }

    public static RSAPublicKey getRSAPublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            // 通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            throw new RuntimeException("getRSAPublicKey,不支持的密钥: ", e);
        }
    }

    public static RSAPrivateKey getRSAPrivateKey(String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            // 通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            throw new RuntimeException("getRSAPrivateKey,不支持的密钥: ", e);
        }
    }


    public static Map<String, String> createKeys(int keySize) {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;

        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm -> [" +
                RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(keySize);

        // 生成秘钥对
        KeyPair keyPair = kpg.generateKeyPair();

        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());

        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 公钥加密
     */
    public String publicEncrypt(String data) {
        return publicKeyEncrypt(data, publicKey);
    }
    public static String publicEncrypt(String data, RSAPublicKey rsaPublicKey) {
        return publicKeyEncrypt(data, rsaPublicKey);
    }

    private static String publicKeyEncrypt(String data, RSAPublicKey rsaPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher,
                    Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                    rsaPublicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("公钥加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     */
    public String privateDecrypt(String data) {
        return privateKeyDecrypt(data, privateKey, publicKey.getModulus());
    }
    public static String privateDecrypt(String data, RSAPrivateKey rsaPrivateKey) {
        return privateKeyDecrypt(data, rsaPrivateKey, rsaPrivateKey.getModulus());
    }

    private static String privateKeyDecrypt(String data, RSAPrivateKey rsaPrivateKey, BigInteger modulus) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
                    modulus.bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("私钥解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     */
    public String privateEncrypt(String data) {
        return privateKeyEncrypt(data, privateKey, publicKey.getModulus());
    }
    public static String privateEncrypt(String data, RSAPrivateKey rsaPrivateKey) {
        return privateKeyEncrypt(data, rsaPrivateKey, rsaPrivateKey.getModulus());
    }

    private static String privateKeyEncrypt(String data, RSAPrivateKey rsaPrivateKey, BigInteger modulus) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);

            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher,
                    Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                    modulus.bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("私钥加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     */
    public String publicDecrypt(String data) {
        return publicKeyDecrypt(data, publicKey);
    }
    public static String publicDecrypt(String data, RSAPublicKey rsaPublicKey) {
        return publicKeyDecrypt(data, rsaPublicKey);
    }

    private static String publicKeyDecrypt(String data, RSAPublicKey rsaPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);

            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE,
                    Base64.decodeBase64(data),
                    rsaPublicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("公钥解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥签名
     */
    public String sign(String data) {
        return getSign(data, privateKey);
    }

    public static String sign(String data, RSAPrivateKey privateKey) {
        return getSign(data, privateKey);
    }

    private static String getSign(String data, RSAPrivateKey privateKey) {
        try {
            String encodeStr = DigestUtils.md5Hex(data);
            Signature signature = Signature.getInstance(RSA_ALGORITHM_SIGN);
            signature.initSign(privateKey);
            signature.update(encodeStr.getBytes(CHARSET));

            return Base64.encodeBase64URLSafeString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("私钥签名字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥验签，验证sign
     */
    public boolean verify(String data, String sign) {
        return verifySign(data, sign, publicKey);
    }

    public static boolean verify(String data, String sign, RSAPublicKey rsaPublicKey) {
        return verifySign(data, sign, rsaPublicKey);
    }

    private static boolean verifySign(String data, String sign, RSAPublicKey rsaPublicKey) {
        try {
            String encodeStr = DigestUtils.md5Hex(data);
            Signature signature = Signature.getInstance(RSA_ALGORITHM_SIGN);
            signature.initVerify(rsaPublicKey);
            signature.update(encodeStr.getBytes(CHARSET));

            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            throw new RuntimeException("公钥验签字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 分段加解密
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode,
        byte[] datas, int keySize) {
        int maxBlock = 0;

        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = (keySize / 8) - 11;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;

        try {
            while (datas.length > offSet) {
                if ((datas.length - offSet) > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }

                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }

        byte[] resultDatas = out.toByteArray();
        if(out!=null){
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //IOUtils.closeQuietly(out);

        return resultDatas;
    }

    public static void main(String[] args) {
        try {
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs4AivV7eO95S3iL1OSl-NchS3LWtVrCxWBYer9s6TFc88CLNYmuVdb7FhA9nc2eBDWeSMosWHPVNpA0KKnMscajRJt6vKiBKmhZv81T4h9aXAtScw3EH1sYE_ia0N4LlOmPmgoSIJ4gQJQljHd2_45j32xZX-Hw-y9YrjsXU4fi6uNWqFZexfhwaYmLCkGm4-yb2Mf4bqooB2BwNxfsrt_jQokPqZfqOz7ktfY1zbThQ8VJPIbO3uYgjH3_pl1c_48dGAHBs4weC3taX3OpMBu7NYdiIkqOo6x9Q8Vs7Q-_dK0g6DqAy8nwOvgcf-KfH56e030LBDADGXxssYwwpPQIDAQAB";
            String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzgCK9Xt473lLeIvU5KX41yFLcta1WsLFYFh6v2zpMVzzwIs1ia5V1vsWED2dzZ4ENZ5IyixYc9U2kDQoqcyxxqNEm3q8qIEqaFm_zVPiH1pcC1JzDcQfWxgT-JrQ3guU6Y-aChIgniBAlCWMd3b_jmPfbFlf4fD7L1iuOxdTh-Lq41aoVl7F-HBpiYsKQabj7JvYx_huqigHYHA3F-yu3-NCiQ-pl-o7PuS19jXNtOFDxUk8hs7e5iCMff-mXVz_jx0YAcGzjB4Le1pfc6kwG7s1h2IiSo6jrH1DxWztD790rSDoOoDLyfA6-Bx_4p8fnp7TfQsEMAMZfGyxjDCk9AgMBAAECggEBAK93Un5LfnKHofoDsjNunDF24YlfD1Lu5m11Mgo2A4ccwDT90EelYzT2h53QcRAe3ch8ti0ySSuFn5_-HzHf5FI29D1K8W_8oPB_fnAfX9NpsbTSoWtr0n3glIDc1M5u5iVuAqcTZwU9vIp34qwPWMTjg2ZnMRd2XOxlL68hNDivhxhz3tv-8fK5k6noO3KzKU17sa9673tbcq73OEPF3Okv7Ypa6qohi5MqLMMhi6AErP_Q_K81KE58lDMVQkxBFSaSzgfMjbjvn1fYpLfpBmMRl3-eFJUvG7IzPTR6j4iofNG74WfxLlKx91CYwmgIr-0b36S3eOc1yz8J1FsNiAECgYEA6jvKqQgISKXkSjE0Fk5GDDI08wvd0Wg_e2FZFg_ZQ8RyZ9MfV9za3ATUweJUdDLgIi0poECR0m7e_SCznjLrb0ZjpzkpRscTWalDlIW_lVkXTDJO-yBoEmWCe_GgpyqRIuFoMiUwCN-rPtbOsGFnPqys_2M65eZ5SEFW2epUf2kCgYEAxC5LOwsjr3y-Tye8djBOhzAeQmamsBV-AGmNGsS3pD8sjWdG4tG01qRQ2y9_RIdCIvvrUYgwoocf7k7voTA8XmT3Ou6Oal74J-aU0SYCUBPJt1WvUmuRlBZN35TcUhquPgUb7SvbfOtNUc-gtCCK3KTPb-txXCzCXjFf_i8u9LUCgYEAsqKo-2Jp1uXVhhOiUsSdPV3o7dcF81da2sCyTVYG71zZl372r25650Mz8y2mFPxb3RSuY037KA5wN4ICGkthLHr1Myov5Y-bnUyugo3CP6czUmQnwfPECwupiNcNG5AmIgDgEyYzTQEvu3vdI70VHUJZqWfHGmA77LQQBZ9lk8kCgYAnj65cGcL4gI9gJwM6UkODv5Bak5jJqYvfSWnLHCBsXtD9MvZ4hxGQt1IW4V0o1J3hsCukJXKpU9Z8mC56st95qaKxn6nYiY5BfZ5FDwUoYNUsw3q3hDm3Q0gw7jP_2qGIoD8hdNauOkU9WkFuEaHvHM04JKKXk-8eT5asC5fMgQKBgQCCcn-7MWFumJfmCZOxBg0_pSPsbAlmSFxdOO1VQ9Q4Iny47AQRvYxbe-zdvpBzPbkoTlLraJ_hjg9dE2ZbxdiWVAkcTcHmzlCs4BKBQi8hu-z3cns5-TI-93wjrBjqAWL04nFA3wz043cXiOqhaykm3FZV1Dk4i-SZOZns6Zuukw";

            RSAPublicKey rsaPublicKey = XRsa.getRSAPublicKey(publicKey);
            RSAPrivateKey rsaPrivateKey = XRsa.getRSAPrivateKey(privateKey);


            String json = "{\"name\" : \"春江潮水连海平，海上明月共潮生\"}";

            // 私钥加密得到sign
            String sign = XRsa.sign(json, rsaPrivateKey);
            System.out.println("sign:" + sign);
            boolean b = XRsa.verifySign(json, sign, rsaPublicKey);
            System.out.println("验证签名:" + b);


            String en = XRsa.publicEncrypt(json, rsaPublicKey);

            String de = XRsa.privateDecrypt(en, rsaPrivateKey);

            System.out.println("公钥(public)加密,私钥(private)解密---------");
            System.out.println("公钥加密json数据:" + en);
            System.out.println("私钥解密:" + de);

            en = XRsa.privateEncrypt(json, rsaPrivateKey);
            de = XRsa.publicDecrypt(en, rsaPublicKey) ;

            System.out.println("私钥(private)加密，公钥(public)解密---------");
            System.out.println("私钥加密json数据:" + en);
            System.out.println("公钥解密:" + de);

            System.out.println("--------------------------------------------------------------------------------");


        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
        }
    }
}
