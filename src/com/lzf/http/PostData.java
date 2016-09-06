package com.lzf.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostData {
	public static String submit(String path, String username, String password) {
		String message = "系统升级维护中，由此给您带来的不便敬请谅解！感谢您的支持与理解！";
		try {
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			httpURLConnection.setRequestMethod("POST");
			// 请求超时信息
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setReadTimeout(5000);
			// 设置允许输入,输出:
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			// POST方式不能缓存,需手动设置为false
			httpURLConnection.setUseCaches(false);
			// 我们请求的数据:
			String data = "username=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8");
			// 这里可以写一些请求头的东东...
			// 获取输出流
			OutputStream output = httpURLConnection.getOutputStream();
			// 将【data.getBytes().length】个字节从指定的 byte 数组写入此输出流。
			output.write(data.getBytes());
			// 刷新此输出流并强制写出所有缓冲的输出字节
			output.flush();
			if (httpURLConnection.getResponseCode() == 200) {
				// 获取响应的输入流对象
				InputStream inputStream = httpURLConnection.getInputStream();

				// 创建字节输出流对象（用来将流转化为二进制数组）
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// 记录读取的长度
				int len = 0;
				// 定义临时缓存区
				byte[] buffer = new byte[1024];
				// 按照缓存区的大小，循环读取
				while ((len = inputStream.read(buffer)) != -1) {
					// 根据读取的长度写到byteArrayOutputStream中
					byteArrayOutputStream.write(buffer, 0, len);
				}
				// 释放资源
				inputStream.close();
				output.close();
				// 返回字符串
				message = new String(byteArrayOutputStream.toByteArray());
				byteArrayOutputStream.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message.trim();

	}
}
