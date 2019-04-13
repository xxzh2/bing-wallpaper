package com.ginkgo.bing.wallpaper.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 加密工具类
 * @author Think
 * @version 1.0
 */
public class Keygen {

	private static Log log = LogFactory.getLog(Keygen.class);

	public static String rot13(String args) {
		StringBuffer sbuf = new StringBuffer();
		String s = args;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'm')
				c += 13;
			else if (c >= 'A' && c <= 'M')
				c += 13;
			else if (c >= 'n' && c <= 'z')
				c -= 13;
			else if (c >= 'N' && c <= 'Z')
				c -= 13;
			sbuf.append(c);
		}
		log.info(sbuf.toString());
		return sbuf.toString();
	}

	/**
	 * 生成密串
	 * @param user
	 * @param pwd
	 * @return
	 */
	public static String keyGen(String user, String pwd) {
		String r = rot13(user + pwd);
		int start = (int) (Math.random() * 10 + 6);
		String md5 = randomCase(getMD5(r));
		String key = md5.substring(start, start + 16);

		String stamp = Long.toString(new Date().getTime() / 1000, 16);
		log.debug(md5.toLowerCase());
		log.debug(stamp.substring(4));
		String gen = key + stamp.substring(4);
		log.debug(gen);
		return gen;
	}

	public static String unpack(String key) {
		if (key == null || key.length() != 20) {
			return null;
		}
		String keystamp = key.substring(key.length() - 4);
		String prefix = Long.toString(new Date().getTime() / 1000, 16).substring(0, 4);
		String stamp = prefix + keystamp;
		try {
			long l = Long.valueOf(stamp, 16) * 1000;
			Date d = new Date();
			d.setTime(l);
			log.debug(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// String k = rot13(key.substring(0, 16));
		String k = key.substring(0, 16);

		log.debug(k);
		return k;
	}

	/**
	 * 对字符串md5加密(小写+字母)
	 *
	 * @param str
	 *            传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String randomCase(String str) {
		if (str == null)
			return null;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if ((int) (Math.random() * 10) % 2 == 0) {
				buf.append(str.substring(i, i + 1).toUpperCase());
			} else {
				buf.append(str.substring(i, i + 1).toLowerCase());
			}
		}
		return buf.toString();
	}
}
