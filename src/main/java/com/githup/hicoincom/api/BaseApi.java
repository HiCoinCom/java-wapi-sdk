package com.githup.hicoincom.api;

import com.alibaba.fastjson.JSONObject;
import com.githup.hicoincom.WalletConfig;
import com.githup.hicoincom.api.urls.ApiUri;
import com.githup.hicoincom.bean.args.Args;
import com.githup.hicoincom.bean.args.BaseWalletArgs;
import com.githup.hicoincom.crypto.IDataCrypto;
import com.githup.hicoincom.exception.CryptoException;
import com.githup.hicoincom.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZPZ
 */
public class BaseApi {
    protected static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    protected IDataCrypto dataCrypto;
    protected WalletConfig cfg;
    private final String GET = "get";
    private final String dataField = "data";

    public BaseApi() {
    }

    public BaseApi(WalletConfig cfg, IDataCrypto crypto) {
        this.cfg = cfg;
        this.dataCrypto = crypto;
    }

    public IDataCrypto getDataCrypto() {
        return dataCrypto;
    }

    public void setDataCrypto(IDataCrypto dataCrypto) {
        this.dataCrypto = dataCrypto;
    }

    public WalletConfig getCfg() {
        return cfg;
    }

    public void setCfg(WalletConfig cfg) {
        this.cfg = cfg;
    }

    /**
     * 请求baas 接口
     *
     * @param uri   请求地址，
     * @param args  入参
     * @param clazz 返回值类型
     * @return T
     * @author ZPZ
     */
    protected <T> T invoke(ApiUri uri, BaseWalletArgs args, Class<T> clazz) {
        T result = null;
        //加密参数
        String raw = args.toJson();
        this.info("request url:{}  raw args:{}", uri.getValue(), raw);
        String data = this.dataCrypto.encode(raw);
        this.info("{}  encode args:{}", data);

        if (StringUtils.isBlank(data)) {
            logger.error("{} encode args return null", uri.getValue());
            throw new CryptoException("data crypto return null");
        }

        //对数据进行签名，并传递给被调用方
        String signData = dataCrypto.sign(raw);

        //请求接口
        String url = String.format("%s/%s", this.cfg.getDomain(), uri.getValue());
        Args params = new Args(this.cfg.getAppId(), data, signData);
        String resp;
        if (this.GET.equalsIgnoreCase(uri.getMethod())) {
            url += "?" + params.toString();
            resp = HttpUtils.sendGet(url, "");
        } else {
            resp = HttpUtils.sendPost(url, JSONObject.toJSONString(params));
        }

        this.info("{} raw result:{}", uri.getValue(), resp);
        //接口返回数据为空
        if (StringUtils.isBlank(resp)) {
            logger.error("{} api return null", uri.getValue());
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(resp);
        if (jsonObject == null || !jsonObject.containsKey(dataField) || StringUtils.isBlank(jsonObject.getString(dataField))) {
            //接口返回数据未包含data字段
            logger.info("{} result does not find data field or data field is empty", uri.getValue());
            return JSONObject.parseObject(resp, clazz);
        }

        //解密响应数据
        String respRaw = this.dataCrypto.decode(jsonObject.getString("data"));
        String respSign = jsonObject.getString("sign");
        this.info("{} decode result :{}", uri.getValue(), respRaw);

        //对签名进行验签
        if (!StringUtils.isBlank(respRaw) && dataCrypto.checkSign(respRaw, respSign)) {
            jsonObject.put("rawData", respRaw);
            result = jsonObject.toJavaObject(clazz);
        }

        if (result == null) {
            logger.error("{} result parse json to object error", uri.getValue());
            return null;
        }
        return result;
    }

    protected void info(String format, Object... object) {
        if (this.getCfg().getEnableLog()) {
            logger.info(format, object);
        }
    }
}
