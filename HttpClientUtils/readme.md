# 欢迎来到我的博客

你好！ 欢迎来到我的GitHub，更新技术文章还可关注我的个人订阅号：**一只蓝色猿**。微信搜索即可关注，第一时间获取优质文章分享。

## 简单介绍

目前Java中实现Http请求的常用的有两种类型（包含3种实现）：

 1. 第一类是通过 **Jdk原生HttpURLConnection类实现** ；
 2. 第二类是通过 **第三方开源框架Apache的HttpClient[^1] 来实现** ；

说明：使用Apache的HttpClient有2中具体的实现方式，HttpClient对Http的封装性比较不错的，通过它能满足我们大部分的需求，一种方式是使用HttpClient3.1来实现的，HttpClient3.1 是 org.apache.commons.httpclient下操作远程 url的工具包，虽然已不再更新，但实现工作中使用httpClient3.1的代码还是很多，因为大多时候我们使用它就能满足我们需求；另一种是HttpClient4.5，HttpClient4.5是org.apache.http.client下操作远程 url的工具包，是Apchae旗下最新的。使用Jdk原生的HttpURLConnection当然就是一种具体的实现方式，HttpURLConnection是JAVA的标准类，当然它的执行效率方面肯定是有优势的。下面就是三种方式的具体实现代码：

## 第一种实现方式：java原生HttpURLConnection
```java
package com.example.demo.web.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName JdkHttpClient
 * @Description TODO
 * @Author umizhang
 * @Date 2019/8/5 12:11
 * @Version 1.0
 */
public class JdkHttpClient {

    /**
     * @Author: umizhang
     * @Title: doGet
     * @Description TODO get请求方法
     * @Date: 2019/8/5 12:17
     * @Param: [httpUrl]
     * @return: java.lang.String
     * @Exception:
     */
    public static String doGet(String httpUrl) {
        String result = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            // 1、创建远程URL连接对象
            URL url = new URL(httpUrl);
            // 2、通过远程URL对象来打开一个连接，并将它转换为HttpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 3、设置请求连接方式（方法）为 get请求
            connection.setRequestMethod("GET");
            // 4、设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 5、设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 6、发送请求
            connection.connect();
            // 7、通过connection连接来获取输入流
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // 7.1、封装输入流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
                // 7.2、存放数据
                StringBuffer stringBuffer = new StringBuffer();
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(temp);
                    stringBuffer.append("\r\n");
                }
                result = stringBuffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 8、关闭流资源
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 9、关闭远程连接
            connection.disconnect();
        }
        return result;
    }

    /**
     * @Author: umizhang
     * @Title: doPost
     * @Description TODO post请求方法
     * @Date: 2019/8/5 12:17
     * @Param: [httpUrl, param]
     * @return: java.lang.String
     * @Exception:
     */
    public static String doPost(String httpUrl, String param) {
        String result = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        byte[] bytes = param.getBytes();
        try {
            // 1、创建远程URL连接对象
            URL url = new URL(httpUrl);
            // 2、通过远程URL对象来打开一个连接，并将它转换为HttpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 3、设置请求方式（方法）为 post请求
            connection.setRequestMethod("POST");
            // 4、设置连接服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 5、设置读取服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 6、当向远程服务器传送数据/写数据时，需要设置为true，默认值为：false
            connection.setDoOutput(true);
            // 7、当从远程服务读取数据时，需要设置为true，默认值为：true，该参数可有可无
            connection.setDoInput(true);
            // 8.1、设置入参编码格式：请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 8.2、设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0（如果具有密钥的属性已存在，则使用新值覆盖其值。 ）
            // connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 8.3、设置请求内容的长度
            connection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            // 9、通过连接对象获取一个输出流
            outputStream = connection.getOutputStream();
            // 10、通过输出流对象将请求参数写出去/传输出去，它是通过字节数组写出的
            outputStream.write(bytes);
            // 11、通过connection连接来获取输入流，从远程读取
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // 11.1、封装输入流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
                // 11.2、存放数据
                StringBuffer buffer = new StringBuffer();
                String temp = null;
                // 12、循环遍历，按正行正行来读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\r\n");
                }
                result = buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 13、关闭资源
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 14、关闭远程连接
            connection.disconnect();
        }
        return result;
    }
}
```

## 第二种实现方式：Apache 提供的 HttpClient3.1
```java
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
```

## 第三种实现方式：Apache 提供的 HttpClient4.5
```java
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
```
