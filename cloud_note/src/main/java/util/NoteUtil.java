package util;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
public class NoteUtil {
	public static String md5(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//md5加密处理
			byte[] output = md.digest(src.getBytes());
			//Base64处理
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new RuntimeException("加密失败", e);
		}
	}
	/*
	 *生成不重复的ID值 
	 */
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id;
	}
	public static void main(String[] args) {
		System.out.println(NoteUtil.md5("11111111"));
		System.out.println(NoteUtil.md5("123456"));
		System.out.println(NoteUtil.createId());
		System.out.println(new Date().getTime());
	}
}
