package weixinkeji.vip.jweb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodModel {

	/**
	 * 方法
	 */
	public final Method method;

	/**
	 * 方法名
	 */
	public final String methodName;

	/**
	 * 方法名 的别名
	 */
	public final String methodKey;
	/**
	 * 方法名 类型
	 */
	public final int type;

	/**
	 * 方法参数数量
	 */
	public final int parameterCount;

	/**
	 * 参数模型
	 */
	public final ParameterModel[] yourParameters;

	private Object[] args;

	private final boolean isNullParameter;

	public MethodModel(Method m) {
		this.method = m;
		this.methodName = m.getName();
		this.methodKey = m.getName();
		this.type = m.getModifiers();
		this.parameterCount = m.getParameterCount();
		this.yourParameters = new ParameterModel[m.getParameterCount()];
		
		Parameter[] ps = m.getParameters();
		for (int i = 0; i < m.getParameterCount(); i++) {
			this.yourParameters[i] = new ParameterModel(ps[i]);
		}
		
		this.args = this.parameterCount == 0 ? null : new Object[this.parameterCount];
		this.isNullParameter = null == this.args;
	}

	/**
	 * 设置 方法参数的实例（执行方法时会用到）
	 * 
	 * @param index 第几个参数（从0开始算）
	 * @param obj
	 */
	public void setArg(int index, Object obj) {
		args[index] = obj;
	}

	/**
	 * 重置参数数组（全部设置为null）
	 */
	public void resetAgrs() {
		if (this.isNullParameter) {
			return;
		}
		for (int i = 0; i < args.length; i++) {
			args[i] = null;
		}
	}

	/**
	 * 取得第i个参数模型（从0开始算）
	 * 
	 * @param i 参数模型数组下标
	 * @return ParameterModel 参数模型
	 */
	public ParameterModel getParameterModel(int i) {
		return yourParameters[i];
	}

	/**
	 * 执行方法
	 * 
	 * @param obj 对象
	 * @return Object 方法返回值
	 * @throws Exception 方法执行可能出现异常
	 */
	public Object doMethod(Object obj) throws Exception {
		return this.method.invoke(obj, args);
	}

}
