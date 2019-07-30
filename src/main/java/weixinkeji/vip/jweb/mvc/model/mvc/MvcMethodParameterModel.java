package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Method;

/**
 * mvc方法 参数模型
 * 
 * @author wangchunzi
 *
 */
public class MvcMethodParameterModel {
	Method Method;

	private MvcMethodParameterModel(Method m) {
		this.Method = m;
	}

	/**
	 * 得取mvc方法的参数模型
	 * 
	 * @param method 方法
	 * @return MvcMethodParameterModel[] 参数模型数组
	 */
	public static MvcMethodParameterModel[] getMvcMethodParameterModel(Method method) {
		return null;
	}
	
	
//	/**
//	 * 从参数模型中，取出位置对应参数对应的值
//	 * 
//	 * @param paramMode 参数模型
//	 * @return Object[] 参数值
//	 */
//	public static Object[] getParamByModels(MvcMethodParameterModel paramMode[]) {
//		return null;
//	}

}
