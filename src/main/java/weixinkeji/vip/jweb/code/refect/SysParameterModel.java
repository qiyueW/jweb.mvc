package weixinkeji.vip.jweb.code.refect;

import java.lang.reflect.Parameter;

/**
 * 参数模型
 * 
 * @author wangchunzi
 *
 */
public class SysParameterModel {
	// 参数
	public final Parameter parameter;
	// 参数类型
	public final Class<?> parameterTypye;

	/**
	 * 构造函数
	 * 
	 * @param param 类
	 */
	public SysParameterModel(Parameter param) {
		this.parameter = param;
		this.parameterTypye = param.getType();
	}
	
}
