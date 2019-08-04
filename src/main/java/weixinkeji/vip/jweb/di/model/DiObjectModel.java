package weixinkeji.vip.jweb.di.model;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc.ann.JWebController;

@SuppressWarnings("unchecked")
final public class DiObjectModel {
	private static final Map<Class<?>, DiObject> objs = new HashMap<>();

	/**
	 * 取得对象
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> T get(Class<T> c) {
		DiObject obj = objs.get(c);
		if (null == obj) {
			return null;
		}
		Object rs = obj.getObject();
		return null == rs ? null : (T) rs;
	}

	private static boolean init = false;

	public static void closeInit() {
		init = true;
	}

	private static Class<? extends Annotation>[] scanClassByAnnotation;
	static {
		init_0_config(JWebController.class);
	}

	/**
	 * 首先初始此。被哪些 【注解】的类 会被扫描
	 * 
	 * @param ann 【注解】
	 */
	public static void init_0_config(Class<? extends Annotation>... ann) {
		if (init) {
			return;
		}
		scanClassByAnnotation = ann;
	}

	/**
	 * 检查你的类上，是否有我们指定的注解。是的话，返回true; 其他：返回false
	 * 
	 * @param c Class 你的类
	 * @return boolean 是否有我们指定的注解。是的话，返回true; 其他：返回false
	 */
	public static boolean classHasTheAnnotation(Class<?> c) {
		if (null == scanClassByAnnotation) {
			return false;
		}
		for (Class<? extends Annotation> ac : scanClassByAnnotation) {
			if (null == c.getAnnotation(ac)) {
				return true;
			}
		}
		return false;
	}

	public static void init_1_Annotation(List<Class<?>> c) {
		if (init) {
			return;
		}
	}

	public static void init_1_RegDiObject() {
		if (init) {
			return;
		}
	}

}
