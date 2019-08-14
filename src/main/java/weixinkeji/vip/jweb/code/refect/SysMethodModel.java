package weixinkeji.vip.jweb.code.refect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class SysMethodModel {

	private final Method method;
	private final Parameter[] parameters;

	/**
	 * 构造函数
	 * 
	 * @param method 类
	 */
	public SysMethodModel(Method method) {
		this.method = method;
		this.parameters = this.method.getParameters();
		
	}

}
