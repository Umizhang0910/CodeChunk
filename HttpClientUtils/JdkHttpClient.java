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
