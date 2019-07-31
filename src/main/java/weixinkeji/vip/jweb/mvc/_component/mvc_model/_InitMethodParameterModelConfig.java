package weixinkeji.vip.jweb.mvc._component.mvc_model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class _InitMethodParameterModelConfig {

	/**
	 * 初始化
	 * 
	 * @param list List
	 */
	synchronized public static void init(List<Class<?>> list) {
		List<MvcMethodParameterModelConfig<?>> rs = new ArrayList<>();
		for (Class<?> c : list) {
			try {
				rs.add((MvcMethodParameterModelConfig<?>) c.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		MvcMethodParameterModelConfigModel.init(rs);
	}
}
