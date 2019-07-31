package weixinkeji.vip.jweb.mvc.tools;

public class MvcTools {

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

		;
	}

	/**
	 * 把驼峰，转成_连接
	 * 
	 * @param str String
	 * @return String 转换后的字符串
	 */
	public static String strTransform_(String str) {
		char[] cs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : cs) {
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
