package weixinkeji.vip.jweb.mvc.model.config;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class _InitMethodParameterModelConfig {

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
