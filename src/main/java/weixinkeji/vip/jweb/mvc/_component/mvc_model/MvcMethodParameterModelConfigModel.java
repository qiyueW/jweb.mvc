package weixinkeji.vip.jweb.mvc._component.mvc_model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcMethodParameterModelConfigModel {

	private final static Map<Class<?>, MvcMethodParameterModelConfig<?>> configModel = new HashMap<>();
	private static boolean init = false;

	/**
	 * 初始化
	 * 
	 * @param list List
	 */
	synchronized public static void init(List<MvcMethodParameterModelConfig<?>> list) {
		if (init) {
			return;
		}
		init = true;
		for (MvcMethodParameterModelConfig<?> config : list) {
			configModel.put(getMethodT(config), null);
		}
	}

	/**
	 * @param c 类型
	 * @return MvcMethodParameterModelConfig
	 */
	public static MvcMethodParameterModelConfig<?> getMvcMethodParameterModelConfig(Class<?> c) {
		return configModel.get(c);
	}

	// 取得方法返回的类型
	private static Class<?> getMethodT(MvcMethodParameterModelConfig<?> convert) {
		for (Method m : convert.getClass().getDeclaredMethods()) {
			if (m.getName().equals("getObject")) {
				return m.getReturnType();
			}
		}
		return null;
	}

}
