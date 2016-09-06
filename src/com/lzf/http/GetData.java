package com.lzf.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {

	// ����һ����ȡ����ͼƬ���ݵķ���:
	public static byte[] getImage(String path) throws Exception {
		// 1.����һ��URL����
		URL url = new URL(path);
		// 2.����URL�����openConnection()����ȡHttpURLConnection����ʵ����Ĭ���Ǵ�gzipѹ���ġ�
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 3.�������ӳ�ʱΪ5�루�������ӳ�ʱ����ȡ��ʱ�ĺ��������Լ�������ϣ���õ���һЩ��Ϣͷ ��
		conn.setConnectTimeout(5000);
		// 4.������������ΪGet����
		conn.setRequestMethod("GET");
		// �ж�����Url�Ƿ�ɹ�
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("����urlʧ��");
		}
		// 5.����getInputStream()������÷��������ص�������
		InputStream inStream = conn.getInputStream();
		byte[] bt = FlowBinary.binary(inStream);
		inStream.close();
		// 6.������disconnect()������HTTP���ӹص�
		conn.disconnect();
		return bt;
	}

	// ��ȡ��ҳ��htmlԴ����
	public static String getHtml(String path) {
		String html = "ϵͳ����ά���У��ɴ˸��������Ĳ��㾴���½⣡��л����֧������⣡";
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
