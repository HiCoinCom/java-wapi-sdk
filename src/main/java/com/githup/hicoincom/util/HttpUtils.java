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
     * Send Http Get request
     *
     * @param url request address
     * @return return string
     */
    public static String sendGet(String url, String token) {
        String result = null;
        CloseableHttpResponse response = null;
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
        return result;
    }


    /**
     * Send Http Post request, parameter is json string
     *
     * @param url     request address
     * @param jsonStr request body
     * @return String response body
     */
    public static String sendPost(String url, String jsonStr) {
        String result = null;
        // string encoding
        StringEntity entity = new StringEntity(jsonStr, Consts.UTF_8);
        //set content-type
        entity.setContentType("application/json");
        HttpPost httpPost = new HttpPost(url);
        // To prevent being added as an attack
        httpPost.setHeader("User-Agent", USER_AGENT);
        //Receive parameter settings
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
            // close CloseableHttpResponse
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return result;
    }

}
