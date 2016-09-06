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
		String message = "ϵͳ����ά���У��ɴ˸��������Ĳ��㾴���½⣡��л����֧������⣡";
		try {
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// ��������ʽ
			httpURLConnection.setRequestMethod("POST");
			// ����ʱ��Ϣ
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setReadTimeout(5000);
			// ������������,���:
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			// POST��ʽ���ܻ���,���ֶ�����Ϊfalse
			httpURLConnection.setUseCaches(false);
			// �������������:
			String data = "username=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8");
			// �������дһЩ����ͷ�Ķ���...
			// ��ȡ�����
			OutputStream output = httpURLConnection.getOutputStream();
			// ����data.getBytes().length�����ֽڴ�ָ���� byte ����д����������
			output.write(data.getBytes());
			// ˢ�´��������ǿ��д�����л��������ֽ�
			output.flush();
			if (httpURLConnection.getResponseCode() == 200) {
				// ��ȡ��Ӧ������������
				InputStream inputStream = httpURLConnection.getInputStream();

				// �����ֽ������������������ת��Ϊ���������飩
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// ��¼��ȡ�ĳ���
				int len = 0;
				// ������ʱ������
				byte[] buffer = new byte[1024];
				// ���ջ������Ĵ�С��ѭ����ȡ
				while ((len = inputStream.read(buffer)) != -1) {
					// ���ݶ�ȡ�ĳ���д��byteArrayOutputStream��
					byteArrayOutputStream.write(buffer, 0, len);
				}
				// �ͷ���Դ
				inputStream.close();
				output.close();
				// �����ַ���
				message = new String(byteArrayOutputStream.toByteArray());
				byteArrayOutputStream.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message.trim();

	}
}
