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

	public static void main(String args[]) {
		Class<?> c = Byte.class;
		System.out.println(Byte.class.getName() + "//" + (c == Byte[].class));
	}
}
