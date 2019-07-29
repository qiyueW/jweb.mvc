package weixinkeji.vip.jweb.mvc.convert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConvertCenter {
	private final static Map<Class<?>, MvcDataConver<?>> convertDo = new HashMap<>();

	public ConvertCenter regConverCenter(MvcDataConver<?> obj) {
		Class<?> c = this.getMethodT(obj);
		if (c == Boolean.class) {
			convertDo.put(boolean.class, obj);
		} else if (c == Short.class) {
			convertDo.put(short.class, obj);
		} else if (c == Integer.class) {
			convertDo.put(int.class, obj);
		} else if (c == Long.class) {
			convertDo.put(long.class, obj);
		} else if (c == Float.class) {
			convertDo.put(float.class, obj);
		} else if (c == Double.class) {
			convertDo.put(double.class, obj);
		}
		convertDo.put(c, obj);
		return this;
	}

	public MvcDataConver<?> getMvcDataConver(Class<?> c) {
		return convertDo.get(c);
	}

	private Class<?> getMethodT(MvcDataConver<?> convert) {
		for (Method m : convert.getClass().getDeclaredMethods()) {
			if (m.getName().equals("toT")) {
				return m.getReturnType();
			}
		}
		return null;
	}

	public static void main(String args[]) {
		new ConvertCenter().regConverCenter(new My()).regConverCenter(new MyInt());
		System.out.println(convertDo.get(int.class).toT("aaaaaaaa"));
	}
}

class MyInt implements MvcDataConver<Integer> {

	@Override
	public Integer toT(String webValue) {
		return 1;
	}
}

class My implements MvcDataConver<String> {

	@Override
	public String toT(String webValue) {
		return webValue;
	}
}
