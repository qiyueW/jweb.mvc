package weixinkeji.vip.jweb.code.tools;

/**
 * 标准 规范 辅助工具
 * 
 * @author wangchunzi
 *
 */
public class STTools {

	/**
	 * 驼峰 转 下划线相接
	 * 
	 * @param source
	 * @return String
	 */
	public static String humpTo_(String source) {
		char[] cs = source.toCharArray();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (char c : cs) {
			if (i == 0 && c >= 'A' && c <= 'Z') {// 首字母大写时，强制转成小写。
				i++;
				sb.append(Character.toLowerCase(c));
				continue;
			}
			if (c >= 'A' && c <= 'Z') {// 非首字母大写时，强制转成小写，并在前面加多一个_
				sb.append('_').append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(humpTo_("AaAaAA"));
	}

}
