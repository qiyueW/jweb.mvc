package weixinkeji.vip.jweb.mvc._component.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.BooleanDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.ByteDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.CharDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.DateDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.DoubleDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.FloatDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.IntegerDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.LongDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.ShortDefault;

final public class MvcStringConvertModel {
	private final static Map<Class<?>, MvcStringDataConver<?>> convertDo = new HashMap<>();
	static {
		regConverCenter(new BooleanDefault());
		regConverCenter(new ByteDefault());
		regConverCenter(new CharDefault());
		regConverCenter(new DateDefault());
		regConverCenter(new DoubleDefault());
		regConverCenter(new FloatDefault());
		regConverCenter(new IntegerDefault());
		regConverCenter(new LongDefault());
		regConverCenter(new ShortDefault());
	}

	/**
	 * 取得类型转换处理实例
	 * 
	 * @param c 类型（要转成的类型）
	 * @return MvcStringDataConver 处理实例
	 */
	public static MvcStringDataConver<?> getMvcDataConver(Class<?> c) {
		return convertDo.get(c);
	}
	
//----------------------------------------------------------------------------
	// 初始化开关
	private static boolean init = false;

	/**
	 * 初始化入口——模型建立 ，需要提前准备的资源，会在此方法中补齐
	 * 
	 * @param list 集合里的元素：实现了 {@link MvcStringDataConver} 接口的类
	 */
	synchronized public static void init(List<Class<?>> list) {
		if (init) {
			return;
		}
		init = true;
		for (Class<?> c : list) {
			try {
				regConverCenter((MvcStringDataConver<?>) c.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 注册一个 ConvertCenter
	 * 
	 * @param obj ConvertCenter实例
	 */
	private static void regConverCenter(MvcStringDataConver<?> obj) {
		Class<?> c = getMethodT(obj);
		if (c == Boolean.class) {
			convertDo.put(boolean.class, obj);
		} else if (c == Short.class) {
			convertDo.put(short.class, obj);
		} else if (c == Integer.class) {
			convertDo.put(int.class, obj);
		} else if (c == Long.class) {
			convertDo.put(long.class, obj);
		} else if (c == Float.class) {
			convertDo.put(float.class, obj);
		} else if (c == Double.class) {
			convertDo.put(double.class, obj);
		}
		convertDo.put(c, obj);
	}

	// 取得方法返回的类型
	private static Class<?> getMethodT(MvcStringDataConver<?> convert) {
		for (Method m : convert.getClass().getDeclaredMethods()) {
			if (m.getName().equals("toT")) {
				return m.getReturnType();
			}
		}
		return null;
	}
}