package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MvcMethodModel {
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
	 * @param url   请求方法
	 * @param model MvcMethodModel 方法模型
	 */
	public static void regMvcMethodModel(String url, MvcMethodModel model) {
		if (urlMapMethod_open) {
			urlMapMethod.put(url, model);
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
	 * 绑定的请求路径
	 */
	public String bindUrl;
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

	public MvcMethodModel(Method method) {
		this.method = method;
		this.paramMode = MvcMethodParameterModel.getMvcMethodParameterModel(method);
		this.returnModel = new MvcMethodReturnModel(method);
		this.paramLengthIs0 = this.paramMode.length == 0;
	}

	/**
	 * 执行方法
	 * 
	 * @param mvcObj 对象
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
