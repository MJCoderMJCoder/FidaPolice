package com.lzf.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {

	// 定义一个获取网络图片数据的方法:
	public static byte[] getImage(String path) throws Exception {
		// 1.创建一个URL对象
		URL url = new URL(path);
		// 2.调用URL对象的openConnection()来获取HttpURLConnection对象实例【默认是带gzip压缩的】
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 3.设置连接超时为5秒（设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头 ）
		conn.setConnectTimeout(5000);
		// 4.设置请求类型为Get类型
		conn.setRequestMethod("GET");
		// 判断请求Url是否成功
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("请求url失败");
		}
		// 5.调用getInputStream()方法获得服务器返回的输入流
		InputStream inStream = conn.getInputStream();
		byte[] bt = FlowBinary.binary(inStream);
		inStream.close();
		// 6.最后调用disconnect()方法将HTTP连接关掉
		conn.disconnect();
		return bt;
	}

	// 获取网页的html源代码
	public static String getHtml(String path) {
		String html = "系统升级维护中，由此给您带来的不便敬请谅解！感谢您的支持与理解！";
		HttpURLConnection conn = null;
		try {

			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				byte[] data = FlowBinary.binary(in);
				html = new String(data, "UTF-8");
				conn.disconnect();
				return html;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return html;
	}
}
