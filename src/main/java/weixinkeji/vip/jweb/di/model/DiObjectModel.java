package weixinkeji.vip.jweb.di.model;

import java.util.HashMap;
import java.util.Map;

public class DiObjectModel {
	private static final Map<Class<?>, DiObject> objs = new HashMap<>();

	/**
	 * 取得对象
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> c) {
		DiObject obj = objs.get(c);
		if (null == obj) {
			return null;
		}
		Object rs = obj.getObject();
		return null == rs ? null : (T) rs;
	}
	
}
