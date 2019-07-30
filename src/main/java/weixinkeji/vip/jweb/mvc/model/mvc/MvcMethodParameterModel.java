package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import weixinkeji.vip.jweb.mvc.ann.ParamKey;

/**
 * mvc方法 参数模型
 * 
 * @author wangchunzi
 *
 */
public class MvcMethodParameterModel {

	/**
	 * 取得参数值的方式
	 * 
	 * @author wangchunzi
	 *
	 */
	public enum getParamWebValueWay {
		/**
		 * java基本类型
		 */
		javaBaseType,
		/**
		 * 用户vo类型
		 */
		vo,
		/**
		 * 文件模型 ，当参数是 {@link weixinkeji.vip.jweb.mvc._component.file.FileModel} 时
		 */
		fileModel,
		/**
		 * 从io流中，转成json格式的类型
		 */
		jsonIO
	}

	/**
	 * 原生参数反射对象
	 */
	public final Parameter parameter;
	/**
	 * 参数值 的类型
	 */
	public final Class<?> parameterVoClassType;
	/**
	 * 从请求中，取得值的key。默认是参数名，或通过注解@paramKey取得的参数
	 */
	public final String requestKey;

	public final getParamWebValueWay paramType;

	private MvcMethodParameterModel(Parameter parameter, String requestKey, getParamWebValueWay paramType) {
		this.parameter = parameter;
		this.parameterVoClassType = parameter.getType();
		this.requestKey = requestKey;
		this.paramType = paramType;
	}

	private static MvcMethodParameterModel[] EMP = new MvcMethodParameterModel[] {};

	/**
	 * 得取mvc方法的参数模型
	 * 
	 * @param method 方法
	 * @return MvcMethodParameterModel[] 参数模型数组
	 */
	public static MvcMethodParameterModel[] getMvcMethodParameterModel(Method method) {
		Parameter[] params = method.getParameters();
		ParamKey paramKey;
		String requestKey;
		getParamWebValueWay paramType;
		List<MvcMethodParameterModel> list = new ArrayList<>();
		for (Parameter p : params) {
			paramKey = p.getAnnotation(ParamKey.class);
			requestKey = null == paramKey || paramKey.value().trim().isEmpty() ? p.getName() : paramKey.value().trim();
			paramType=null;
			list.add(new MvcMethodParameterModel(p, requestKey,paramType));
		}
		if (list.isEmpty())
			return EMP;
		return list.toArray(new MvcMethodParameterModel[list.size()]);
	}

}
