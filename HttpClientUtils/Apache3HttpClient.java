package com.example.demo.web.test;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Apache3HttpClient
 * @Description TODO
 * @Author umizhang
 * @Date 2019/8/5 12:11
 * @Version 1.0
 */
public class Apache3HttpClient {

    /**
     * @Author: umizhang
     * @Title: doGet
     * @Description TODO get请求方法
     * @Date: 2019/8/6 14:43
     * @Param: [httpUrl]
     * @return: java.lang.String
     * @Exception:
     */
    public static String doGet(String httpUrl) {
        String result = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        // 1、创建HttpClient实例
        HttpClient httpClient = new HttpClient();
        // 2、设置http连接主机服务器的超时时间：15000毫秒
        // 2.1、先获取连接管理器对象，再获取参数对象,再进行参数的赋值
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 3、创建一个get方法实例对象
        GetMethod getMethod = new GetMethod(httpUrl);
        // 3.1、设置get请求超时时间60000毫秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        // 3.2、设置请求重试机制，默认重试次数：3次，参数设置为true，重试机制可用，false相反
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
        try {
            // 4、执行get方法
            int statusCode = httpClient.executeMethod(getMethod);
            // 5、判断返回状态码做对应的处理
            if (statusCode != HttpStatus.SC_OK) {
                // 5.1、如果状态码返回的不是ok,说明失败了,打印错误信息
                System.out.println("Method faild: " + getMethod.getStatusLine());
            } else {
                // 5.2.1、通过getMethod实例，获取远程一个输入流
                inputStream = getMethod.getResponseBodyAsStream();
                // 5.2.2、封装输入流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
                // 5.2.3、存放数据
                StringBuffer stringBuffer = new StringBuffer();
                // 6、读取封装的输入流返回
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(temp).append("\r\n");
                }
                result = stringBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7、关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 8、释放连接
            getMethod.releaseConnection();
        }
        return result;
    }

    /**
     * @Author: umizhang
     * @Title: doPost
     * @Description TODO post请求方法
     * @Date: 2019/8/6 15:19
     * @Param: [httpUrl, param]
     * @return: java.lang.String
     * @Exception:
     */
    public static String doPost(String httpUrl, Map<String, Object> param) {
        String result = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        // 1、创建HttpClient实例
        HttpClient httpClient = new HttpClient();
        // 2、设置http连接主机服务器的超时时间：15000毫秒
        // 2.1、先获取连接管理器对象，再获取参数对象,再进行参数的赋值
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 3、创建一个post方法实例对象
        PostMethod postMethod = new PostMethod(httpUrl);
        // 3.1、设置post请求超时时间60000毫秒
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        NameValuePair[] nameValuePair = null;
        // 4、判断参数param集合是否为空
        if (null != param && param.size() > 0) {
            // 4.1、创建键值对参数对象数组，大小为参数的个数
            nameValuePair = new NameValuePair[param.size()];
            // 4.2、循环遍历参数集合
            Set<Map.Entry<String, Object>> entries = param.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                // 4.3、从entry中获取key和value创建键值对象存放到数组中
                try {
                    nameValuePair[index] = new NameValuePair(entry.getKey(), new String(entry.getValue().toString().getBytes(StandardCharsets.UTF_8.name()), StandardCharsets.UTF_8.name()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
        // 5、判断nameValuePair是否为空
        if (null != nameValuePair && nameValuePair.length > 0) {
            // 5.1、将参数存放到requestBody对象中
            postMethod.setRequestBody(nameValuePair);
        }
        try {
            // 6、执行post方法
            int statusCode = httpClient.executeMethod(postMethod);
            // 6.1、判断返回状态码做对应的处理
            if (statusCode != HttpStatus.SC_OK) {
                // 6.1、如果状态码返回的不是ok,说明失败了,打印错误信息
                System.err.println("Method faild: " + postMethod.getStatusLine());
            }
            // 6.2.1、通过postMethod实例，获取远程一个输入流
            inputStream = postMethod.getResponseBodyAsStream();
            // 6.2.2、封装输入流，并指定字符集
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
            // 6.2.3、存放数据
            StringBuffer stringBuffer = new StringBuffer();
            // 7、读取封装的输入流返回
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp).append("\r\n");
            }
            result = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放连接
            postMethod.releaseConnection();
        }
        return result;
    }
}
