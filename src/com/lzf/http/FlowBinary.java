package com.lzf.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * дһ���ཫ��ת��Ϊ����������
 */
public class FlowBinary {
	// �����ж�ȡ����
	public static byte[] binary(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// ���������ж�ȡһ���������ֽڣ�������洢�ڻ���������buffer��
		while ((len = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		inputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
}
