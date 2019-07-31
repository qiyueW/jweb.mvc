package weixinkeji.vip.jweb.mvc._component.convert;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class _InitConvert {
	private static boolean init = false;

	synchronized public static void init(List<Class<?>> list) {
		if (init) {
			return;
		}
		init = true;
		MvcStringConvertModel cc = new MvcStringConvertModel();
		for (Class<?> c : list) {
			try {
				cc.regConverCenter((MvcStringDataConver<?>)c.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
}
