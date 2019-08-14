package weixinkeji.vip.jweb.code.tools;

import java.util.Date;

/**
 * 标准 规范 辅助工具
 * 
 * @author wangchunzi
 *
 */
public class SysTools {

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

	/**
	 * 在这里，8种基本类型的拓展类型，也被当作java基本类型看待，满足即返回true
	 * 
	 * @param c 类型
	 * @return boolean
	 */
	public static boolean isJavaBaseType(Class<?> c) {
		return c == byte.class || c == Byte.class || c == byte[].class || c == Byte[].class // byte
				|| c == boolean.class || c == Boolean.class// boolean
				|| c == char.class || c == Character.class || c == char[].class || c == Character[].class// char
				|| c == short.class || c == Short.class// short
				|| c == int.class || c == Integer.class // int
				|| c == long.class || c == Long.class // long
				|| c == float.class || c == Float.class // float
				|| c == double.class || c == Double.class // double
				|| c == Date.class || c == java.sql.Date.class;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(humpTo_("AaAaAA"));
	}

}
