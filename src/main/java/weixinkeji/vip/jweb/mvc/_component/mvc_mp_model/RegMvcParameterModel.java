package weixinkeji.vip.jweb.mvc._component.mvc_mp_model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegMvcParameterModel {

	private final static Map<Class<?>, RegMvcParameter<?>> configModel = new HashMap<>();
	private static boolean init = false;

	/**
	 * 初始化
	 * 
	 * @param list List
	 */
	synchronized public static void init(List<Class<?>> list) {
		if (init) {
			return;
		}
		init = true;
		RegMvcParameter<?> obj;
		try {
			for (Class<?> c : list) {
				obj = (RegMvcParameter<?>) c.getConstructor().newInstance();
				configModel.put(getMethodT(obj), obj);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得 MvcMethodParameterModelConfig
	 * 
	 * @param c 类型
	 * @return MvcMethodParameterModelConfig
	 */
	public static RegMvcParameter<?> getMvcMethodParameterModelConfig(Class<?> c) {
		return configModel.get(c);
	}

	// 取得方法返回的类型
	private static Class<?> getMethodT(RegMvcParameter<?> convert) {
		for (Method m : convert.getClass().getDeclaredMethods()) {
			if (m.getName().equals("getObject")) {
				return m.getReturnType();
			}
		}
		return null;
	}

}
