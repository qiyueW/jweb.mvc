package weixinkeji.vip.jweb.mvc._component.mvc_mp_model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegMvcParameterModel {

	private static Class<? extends RegMvcParameter>[] configModel = null;
	private static boolean init = false;

	/**
	 * 初始化
	 * 
	 * @param conf RegBindMvcParameterConfig
	 */
	synchronized public static void init(RegBindMvcParameterConfig conf) {
		if (init) {
			return;
		}
		init = true;
		if (null == conf) {// 没有找到注册的类时
			return;
		}
		List<Class<? extends RegMvcParameter>> vo = new ArrayList<>();
		conf.regMyParameter(vo);
		if (vo.isEmpty()) {
			return;
		}
		vo.toArray(new Class[vo.size()]);
	}

	/**
	 * 取得用户类型。没有时，直接返回 null
	 * 
	 * @param c 参数类型
	 * @return Class 实现了RegMvcParameter接口的类型。
	 */
	public static Class<? extends RegMvcParameter> getUserRegMvcParameter(Class<?> c) {
		for (Class<? extends RegMvcParameter> pc : configModel) {
			if (pc == c) {
				return pc;
			}
		}
		return null;
	}

	/**
	 * 取得用户类型的实例
	 * 
	 * @param c         类
	 * @param parameter 参数
	 * @param request   Servlet请求实例
	 * @param response  Servlet请求响应
	 * @return Object
	 */
	public static Object getObject(Class<? extends RegMvcParameter> c, Parameter parameter, HttpServletRequest request,
			HttpServletResponse response) {
		RegMvcParameter obj;
		try {
			obj = c.getConstructor().newInstance();
			obj.init(parameter, request, response);
			return obj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
