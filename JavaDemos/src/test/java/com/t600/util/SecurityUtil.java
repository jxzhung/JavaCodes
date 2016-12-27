package com.jzhung.launcher.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;


public class SecurityUtil {

	public static final int KEY = 7;

	public static void main(String[] args) throws IOException {

		Reader reader = new FileReader(new File("d:\\userinfo.dat"));
//		Reader reader = new FileReader(new File("d:\\1.txt"));
		BufferedReader br = new BufferedReader(reader);
		StringBuffer buf = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			buf.append(line);
		}

		// String key = "asdfj12312<123..^^@*&^%*";
		String key = buf.toString();
		//System.out.println("原始:" + key);
		//String enString = StrEncode(key);
		//System.out.println("加密后:" + enString);
		//String deString = StrDecode(enString);
		System.out.println("解密后:" + key);
		//System.out.println(key.equals(deString));
	}

	/**
	 * 字符串加密
	 * @author suntao
	 * @param str
	 * @return
	 */
	public static String StrEncode(String str) {
		StringBuffer result = new StringBuffer();
		String base64encrypt = Base64.encode(str.getBytes());
		String shiftencrypt = Base64.encrypt1(base64encrypt.toString());
		for (int i = 0; i < shiftencrypt.length(); i++) {
			char c = (char) (shiftencrypt.charAt(i) ^ KEY);
			result.append(c);
		}
		return result.toString();
	}

	/**
	 * 字符串解密
	 * @author suntao
	 * @param str
	 * @return
	 */
	public static String StrDecode(String str) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = (char) (str.charAt(i) ^ KEY);
			result.append(c);
		}
		String shiftdeciphe = Base64.decrypt1(result.toString());
		byte[] decodeStr = Base64.decode(shiftdeciphe);
		String base64decipher = new String(decodeStr);
		return base64decipher;
	}

	
	/**
	 * base64
	 * @author suntao
	 *
	 */
	public static class Base64 {

		private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
				.toCharArray();

		public static String encode(byte[] data) {
			int start = 0;
			int len = data.length;

			StringBuffer buf = new StringBuffer(data.length * 3 / 2);
			int end = len - 3;
			int i = start;
			int n = 0;

			while (i <= end) {
				int d = ((((int) data[i]) & 0x0ff) << 16)
				| ((((int) data[i + 1]) & 0x0ff) << 8)
				| (((int) data[i + 2]) & 0x0ff);

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append(legalChars[(d >> 6) & 63]);
				buf.append(legalChars[d & 63]);

				i += 3;
				if (n++ >= 14) {
					n = 0;
					buf.append(" ");
				}
			}

			if (i == start + len - 2) {
				int d = ((((int) data[i]) & 0x0ff) << 16)
				| ((((int) data[i + 1]) & 255) << 8);

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append(legalChars[(d >> 6) & 63]);
				buf.append("=");

			} else if (i == start + len - 1) {
				int d = (((int) data[i]) & 0x0ff) << 16;

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append("==");
			}
			return buf.toString();
		}

		public static int decode(char c) {
			if (c >= 'A' && c <= 'Z')
				return ((int) c) - 65;
			else if (c >= 'a' && c <= 'z')
				return ((int) c) - 97 + 26;
			else if (c >= '0' && c <= '9')
				return ((int) c) - 48 + 26 + 26;
			else
				switch (c) {
				case '+':
					return 62;
				case '/':
					return 63;
				case '=':
					return 0;
				default:
					//throw new RuntimeException("unexpected code: " + c);
				}
			return c;
		}

		public static byte[] decode(String s) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				decode(s, bos);
			} catch (IOException e) {
				throw new RuntimeException();
			}
			byte[] decodedBytes = bos.toByteArray();
			try {
				bos.close();
				bos = null;
			} catch (IOException ex) {
				System.err.println("Error while decoding BASE64: "
						+ ex.toString());
			}
			return decodedBytes;
		}

		private static void decode_bak(String s, OutputStream os)
				throws IOException {
			int i = 0;
			int len = s.length();

			while (true) {
				while (i < len && s.charAt(i) <= ' ')
					i++;
				if (i == len)
					break;
				/*int tri = (decode(s.charAt(i)) << 18)
				+ (decode(s.charAt(i + 1)) << 12)
				+ (decode(s.charAt(i + 2)) << 6)
				+ (decode(s.charAt(i + 3)));
				os.write((tri >> 16) & 255);*/
				int tri = (decode(s.charAt(i)) << 18);
				
				if(i + 1 < len){
					tri += (decode(s.charAt(i + 1)) << 12);
				}
				
				if(i + 2 < len){
					tri +=  (decode(s.charAt(i + 2)) << 6);
				}
				if(i + 3 < len){
					tri += (decode(s.charAt(i + 3)));
				}
						
				os.write((tri >> 16) & 255);
				if (s.charAt(i + 2) == '=')
					break;
				os.write((tri >> 8) & 255);

				if (s.charAt(i + 3) == '=')
					break;
				os.write(tri & 255);
				i += 4;
			}
		}

		/**
		*解密时报错备份
		*/
		private static void decode(String s, OutputStream os)
				throws IOException {
			int i = 0;
			int len = s.length();

			while (true) {
				while (i < len && s.charAt(i) <= ' ')
					i++;
				if (i == len)
					break;
				int tri = (decode(s.charAt(i)) << 18)
				+ (decode(s.charAt(i + 1)) << 12)
				+ (decode(s.charAt(i + 2)) << 6)
				+ (decode(s.charAt(i + 3)));
				os.write((tri >> 16) & 255);
				
				if (s.charAt(i + 2) == '=')
					break;
				os.write((tri >> 8) & 255);

				if (s.charAt(i + 3) == '=')
					break;
				os.write(tri & 255);
				i += 4;
			}
		}

		//
		static final String STR_AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ =.-'?<>\"abcdefghijklmnopqrstuvwxyz0123456789+/";
		static char[] ssTemp;

		/**
		 * 移位
		 * @param s
		 * @return
		 */
		public static String encrypt1(String s) {
			StringBuffer ss = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char tempi = s.charAt(i);
				for (int j = 0; j < STR_AZ.length(); j++) {
					if (tempi == STR_AZ.charAt(j)) {
						if ((j + 3) >= STR_AZ.length()) {
							int z = j + 3 - STR_AZ.length();
							ss.append(STR_AZ.charAt(z));
						} else {
							ss.append(STR_AZ.charAt(j + 3));
						}
					}
				}
			}
			return ss.toString();
		}

		/**
		 * 反移位
		 * @param s
		 * @return
		 */
		public static String decrypt1(String s) {
			StringBuffer ssde = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char tempi = s.charAt(i);
				for (int j = 0; j < STR_AZ.length(); j++) {
					if (tempi == STR_AZ.charAt(j)) {
						if ((j - 3) < 0) {
							int z = STR_AZ.length() + (j - 3);
							ssde.append(STR_AZ.charAt(z));
						} else {
							ssde.append(STR_AZ.charAt(j - 3));
						}
					}
				}
			}
			return ssde.toString();
		}

	}
}
