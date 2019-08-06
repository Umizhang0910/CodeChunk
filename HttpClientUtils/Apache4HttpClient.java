package com.example.demo.web.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @ClassName Apache4HttpClient
 * @Description TODO
 * @Author umizhang
 * @Date 2019/8/5 12:12
 * @Version 1.0
 */
public class Apache4HttpClient {

    public static String doGet(String httpUrl){
        String result = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            // 1、通过默认配置创建一个HttpClient实例
            httpClient = HttpClients.createDefault();
            // 2、创建HttpGet远程连接实例
            HttpGet httpGet = new HttpGet(httpUrl);
            // 3、设置请求头信息，鉴权（如果具有密钥的属性已存在，则使用新值覆盖其值。）
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 4、设置配置请求参数
            RequestConfig config = RequestConfig.custom().setConnectTimeout(35000) // 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000) // 请求超时时间
                    .setSocketTimeout(60000) // 数据读取超时时间
                    .build();
            // 5、为httpGet实例设置配置
            httpGet.setConfig(config);
            // 6、执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 7、通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 8、通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 9、关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPost(String httpUrl, Map<String, Object> param){
        String result = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        // 6、执行get请求得到返回对象
        // 7、通过返回对象获取返回数据
        // 8、通过EntityUtils中的toString方法将结果转换为字符串

        // 1、通过默认配置创建一个HttpClient实例
        httpClient = HttpClients.createDefault();
        // 2、创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(httpUrl);
        // 3、配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 4、为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 5、设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 6、封装post请求参数
        if (null != param && param.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 6.1、通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = param.entrySet();
            // 6.2、循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }
            // 7、为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // 8、httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 9、从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            // 10、通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 11、关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
