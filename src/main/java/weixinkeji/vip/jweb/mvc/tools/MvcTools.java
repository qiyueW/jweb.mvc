package weixinkeji.vip.jweb.mvc.tools;

import java.util.Date;

public class MvcTools {

	/**
	 * 把驼峰，转成_连接
	 * 
	 * @param str String
	 * @return String 转换后的字符串
	 */
	public static String strTransform_(String str) {
		char[] cs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (char c : cs) {
			if (i == 0 && c >= 'A' && c <= 'Z') {
				i++;
				sb.append(Character.toLowerCase(c));
				continue;
			}
			if (c >= 'A' && c <= 'Z') {
				sb.append('_').append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		String str = "myName";
		System.out.println(strTransform_(str));
	}
}
