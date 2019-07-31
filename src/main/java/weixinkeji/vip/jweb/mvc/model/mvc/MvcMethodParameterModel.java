package weixinkeji.vip.jweb.mvc.model.mvc;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringConvertModel;
import weixinkeji.vip.jweb.mvc._component.file.FileModel;
import weixinkeji.vip.jweb.mvc.ann.JsonIO;
import weixinkeji.vip.jweb.mvc.ann.JsonKV;
import weixinkeji.vip.jweb.mvc.ann.ParamKey;
import weixinkeji.vip.jweb.mvc.tools.MvcTools;

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
		 * 基本类型
		 */
		baseType,
		/**
		 * 文件模型 ，当参数是 {@link weixinkeji.vip.jweb.mvc._component.file.FileModel} 时
		 */
		fileModel,
		/**
		 * io流->json格式-> vo的类型
		 */
		jsonIO,
		/**
		 * 键值对->json格式-> vo的类型
		 */
		JsonKV,
		/**
		 * vo类型
		 */
		vo
	}

	private static getParamWebValueWay checkGetParamWebValueWay(Parameter parameter) {
		Class<?> vtype = parameter.getType();
		if (MvcTools.isJavaBaseType(vtype)) {// java普通类型
			return getParamWebValueWay.baseType;
		}
		if (vtype == FileModel.class) { // 文件上传类型
			return getParamWebValueWay.fileModel;
		}
		if (null != parameter.getAnnotation(JsonIO.class)) {
			return getParamWebValueWay.jsonIO;
		}
		if (null != parameter.getAnnotation(JsonKV.class)) {
			return getParamWebValueWay.JsonKV;
		}
		return getParamWebValueWay.vo;
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
	private final MvcVoModel voModel[];// 当类型是vo时，专属vo字段处理的模型

	private MvcMethodParameterModel(Parameter parameter, String requestKey, getParamWebValueWay paramType) {
		this.parameter = parameter;
		this.parameterVoClassType = parameter.getType();
		this.requestKey = requestKey;
		this.paramType = paramType;
		if (this.paramType == getParamWebValueWay.vo || this.paramType == getParamWebValueWay.JsonKV) {
			voModel = MvcVoModel.getMvcVoModel(parameterVoClassType);
		} else {
			voModel = null;
		}
	}

	/**
	 * 从web请求中，取得参数值
	 * 
	 * @param req HttpServletRequest请求
	 * @return Object 参数值
	 * @throws Exception
	 */
	public Object getValue(HttpServletRequest req) throws Exception {
		String value;
		switch (this.paramType) {
		case baseType: {
			value = req.getParameter(requestKey);
			return null == value ? null : MvcStringConvertModel.getMvcDataConver(this.parameterVoClassType).toT(value);
		}
		case JsonKV: {
			value = req.getParameter(requestKey);
			if (null == value) {
				return null;
			}
			return JSON.parseObject(value, this.parameterVoClassType);
		}
		case jsonIO: {
			byte[] b = new byte[512];
			int len = 0;
			InputStream in = req.getInputStream();
			while ((len = in.read(b, len, b.length)) != -1) {
				if (len == b.length) {
					b = Arrays.copyOf(b, len + 50);
				}
			}
			if (len < 2) {
				return null;
			}
			return JSON.parseObject(b, this.parameterVoClassType);
		}
		case vo: {
			value = req.getParameter(requestKey);
			// 指定值是JSON结果时，强制json方式处理
			if (this.paramType == getParamWebValueWay.JsonKV) {
				return null == value ? null : JSON.parseObject(value, this.parameterVoClassType);
			}
			// 当value不为null时，
			if (null == value) {// 当为null时，使用vo属性key，去取参数。如果一个参数都取不到，直接返回null
				Object voObject = MvcVoModel.getObject(parameterVoClassType);// 实例一个对象
				boolean hasValue = false;
				for (MvcVoModel vm : voModel) {
					if (null != (value = req.getParameter(vm.requestKey))) {
						hasValue = true;
						vm.setValue(voObject, value);// 装箱-设置vo对象属性值
					}
				}
				return hasValue ? voObject : null;// 如果找不到一项对应的属性，则直接返回null
			}
			// 当作json方式处理
			return JSON.parseObject(value, this.parameterVoClassType);
		}
		case fileModel: {
			return new FileModel(req, parameter);
		}
		default: {
			return null;
		}
		}

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
			// servlet技术，从请求中从取值的key
			paramKey = p.getAnnotation(ParamKey.class);
			// web取值的key
			requestKey = null == paramKey || paramKey.value().trim().isEmpty() ? p.getName() : paramKey.value().trim();
			// 参数的值的类型，所归属的类型
			paramType = checkGetParamWebValueWay(p);
			list.add(new MvcMethodParameterModel(p, requestKey, paramType));
		}
		if (list.isEmpty())
			return EMP;
		return list.toArray(new MvcMethodParameterModel[list.size()]);
	}

}
