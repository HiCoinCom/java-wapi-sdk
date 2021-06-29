package com.githup.hicoincom.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * @author ZPZ
 */
public class HttpUtils {
    protected static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    private static final CloseableHttpClient HTTPCLIENT = HttpClients.createDefault();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
    private static int timeout = 30000;
    /**
     * 发送HttpGet请求
     *
     * @param url 请求地址
     * @return 返回字符串
     */
    public static String sendGet(String url, String token) {
        String result = null;
        CloseableHttpResponse response = null;
        log.debug("========= Call [{}] Start ==========", url);
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", USER_AGENT);
            httpGet.setHeader("Authorization", "token " + token);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                    .setConnectTimeout(timeout).setSocketTimeout(timeout).build();
            httpGet.setConfig(config);
            response = HTTPCLIENT.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.error("get request error : {}", e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

        }
        log.debug("========= Call [{}] End ==========", url);
        return result;
    }


    /**
     * 发送HttpPost请求，参数为json字符串
     *
     * @param url     请求地址
     * @param jsonStr request body
     * @return String response body
     */
    public static String sendPost(String url, String jsonStr) {
        log.debug("========= Call [{}] Start ==========", url);
        String result = null;
        // 字符串编码
        StringEntity entity = new StringEntity(jsonStr, Consts.UTF_8);
        // 设置content-type
        entity.setContentType("application/json");
        HttpPost httpPost = new HttpPost(url);
        // 防止被当成攻击添加的
        httpPost.setHeader("User-Agent", USER_AGENT);
        // 接收参数设置
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(entity);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout).setSocketTimeout(timeout).build();
        httpPost.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            response = HTTPCLIENT.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.error("HttpClient has exception! message: {}",e.getMessage());
        } finally {
            // 关闭CloseableHttpResponse
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        log.debug("========= Call [{}] End ==========", url);
        return result;
    }

}
