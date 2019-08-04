package weixinkeji.vip.jweb.di.model;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc.ann.JWebController;

final public class DiObjectModel {
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

	private static boolean init = false;

	/**
	 * 关闭 开关。初始化方法不再起作用
	 */
	public static void closeInit() {
		init = true;
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
			if (null != c.getAnnotation(ac)) {
				return true;
			}
		}
		return false;
	}

	private static Class<? extends Annotation>[] scanClassByAnnotation;
	static {
		init_0_config(JWebController.class);
	}

	/**
	 * 首先初始此。被哪些 【注解】的类 会被扫描（每次调用此方法，都会覆盖其他的）
	 * 
	 * @param ann 【注解】
	 */
	@SafeVarargs
	public static void init_0_config(Class<? extends Annotation>... ann) {
		if (init) {
			return;
		}
		scanClassByAnnotation = ann;
	}

	/**
	 * 初始化
	 * 
	 * @param c
	 */
	public static void init_1_Annotation(List<Class<?>> list) {
		if (init) {
			return;
		}

		for (Class<?> c : list) {
			if (classHasTheAnnotation(c)) {
				DiObjectModel.objs.put(c, new DiObject(c));
			}
		}
	}

	/**
	 * 初始化
	 * 
	 * @param yourClass
	 */
	public static void init_1_RegDiObject(List<RegDiObject> list) {
		if (init) {
			return;
		}
		DiObject di;
		for (RegDiObject obj : list) {
			di = new DiObject(obj);
			DiObjectModel.objs.put(di.yourClass, di);
		}
	}

}
