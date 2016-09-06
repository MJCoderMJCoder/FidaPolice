package com.lzf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * encrypt£∫º”√‹
 */
public class Encrypt {

	public static String encrypt(String raw) {
		raw = md5(raw);
		raw = sha(raw);
		return raw;
	}

	/**
	 * MD5º”√‹
	 */
	private static String md5(String raw) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(raw.getBytes());
			return getHashString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String getHashString(MessageDigest messageDigest) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : messageDigest.digest()) {
			stringBuilder.append(Integer.toHexString((b >> 4) & 0xf));
			stringBuilder.append(Integer.toHexString(b & 0xf));
		}
		return stringBuilder.toString();
	}

	/**
	 * SHAº”√‹
	 */
	private static String sha(String mes) {
		MessageDigest md = null;
		String digestStr = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (null != md) {
			byte[] origBytes = mes.getBytes();
			md.update(origBytes);
			byte[] digestRes = md.digest();
			digestStr = getDigestStr(digestRes);
		}
		return digestStr;
	}

	private static String getDigestStr(byte[] origBytes) {
		String tempStr = null;
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < origBytes.length; i++) {
			tempStr = Integer.toHexString(origBytes[i] & 0xff);
			if (tempStr.length() == 1) {
				stb.append("0");
			}
			stb.append(tempStr);
		}
		return stb.toString();
	}
}
