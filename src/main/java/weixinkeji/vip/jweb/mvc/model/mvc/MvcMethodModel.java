package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Method;

public class MvcMethodModel {

	/**
	 * 绑定的请求路径
	 */
	public String bindUrl;
	/**
	 * 方法
	 */
	public Method method;
	
	// 方法包含的参数模型
	private MvcMethodParameterModel[] paramMode = new MvcMethodParameterModel[method.getParameterCount()];
	
	
	
	
	public Object doMethod(Object mvcObj) throws Exception {
		return method.invoke(mvcObj, null);
	}
	
}
