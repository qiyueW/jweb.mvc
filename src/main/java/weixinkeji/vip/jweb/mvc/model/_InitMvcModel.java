package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 模型中，有部分数据是从用户开发中来，此类是对外部分数据，整合到我们的模型中来
 * 
 * @author wangchunzi
 *
 */
public class _InitMvcModel {

	private static boolean init = false;

	synchronized public static void init(List<Class<?>> list) {
		if (init) {
			return;
		}
		init = true;
		for (Class<?> c : list) {
			try {
				c.getConstructor().newInstance();// 实例
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 路径由 类上的注解[如果没有，调用xx方法，取。没有xx实例，返回空] + 方法上的注解
	 * 
	 * @param c
	 * @param m
	 * @return
	 */
	private String getUrl(Class<?> c, Method m) {
		return null;
	}

}
