package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.mvc.ann.UrlActionSort;

public final class MvcMethodModel {
	// 存放 url映射Method 的集合
	private static final Map<String, MvcMethodModel> urlMapMethod = new HashMap<>();
	// urlMapMethod的开关-
	private static boolean urlMapMethod_open = true;

	/**
	 * 取得mvc方法模型
	 * 
	 * @param url 请求方法
	 * @return MvcMethodModel mvc方法模型
	 */
	public static MvcMethodModel getMvcMethodModel(String url) {
		return urlMapMethod.get(url);
	}

	/**
	 * 放入mvc方法模型
	 * 
	 * @param bindUrl 绑定的请求路径
	 * @param method  方法
	 * @param uas     UrlActionSort路径请求类型 post,get,put,......
	 * @param model   MvcMethodModel 方法模型
	 */
	public static void regMvcMethodModel(String bindUrl, Method method, UrlActionSort uas) {
		if (urlMapMethod_open) {
			urlMapMethod.put(bindUrl, new MvcMethodModel(method, uas));
		}
	}

	/**
	 * 关闭配置，关闭后，无法再加入方法模型
	 */
	public static void closeConfig() {
		urlMapMethod_open = false;
	}

	// ---------------------------------------------------------------------------

	/**
	 * 方法
	 */
	public Method method;

	/**
	 * 方法包含的参数模型
	 */
	public final MvcMethodParameterModel[] paramMode;
	private final boolean paramLengthIs0;
	/**
	 * 方法返回类型
	 */
	public final MvcMethodReturnModel returnModel;
	/**
	 * 绑定的路径归属 哪种请求类型：ALL、POST、GET......
	 */
	public final String urlActionSort;

	private MvcMethodModel(Method method, UrlActionSort uas) {
		this.method = method;
		this.urlActionSort = uas.name();
		this.paramMode = MvcMethodParameterModel.getMvcMethodParameterModel(method);
		this.returnModel = new MvcMethodReturnModel(method);
		this.paramLengthIs0 = this.paramMode.length == 0;
	}

	/**
	 * vo对象执行方法
	 * 
	 * @param mvcObj vo对象
	 * @param args   参数值
	 * @return Object 返回的应该是框架的路径或直接为null
	 * @throws Exception 可能发生的错误
	 */
	public Object doMethod(Object mvcObj, Object... args) throws Exception {
		if (this.paramLengthIs0) {
			return method.invoke(mvcObj);
		}
		return method.invoke(mvcObj, args);
	}

}
