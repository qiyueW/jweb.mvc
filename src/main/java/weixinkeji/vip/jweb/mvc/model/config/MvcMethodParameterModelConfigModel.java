package weixinkeji.vip.jweb.mvc.model.config;

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
	 * @param c        类型
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Object
	 */
	public static Object getMvcMethodParameterModelConfig(Class<?> c, HttpServletRequest request,
			HttpServletResponse response) {
		MvcMethodParameterModelConfig<?> config = configModel.get(c);
		if (null == config) {
			return null;
		}
		return config.getObject(request, response);
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
