package weixinkeji.vip.jweb.mvc.model.mvc;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import weixinkeji.vip.jweb.mvc.JWebMvc;
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

	private static ParamWebValueSort checkGetParamWebValueWay(Parameter parameter) {
		Class<?> vtype = parameter.getType();
//基本类型		
		if (MvcTools.isJavaBaseType(vtype)) {// java普通类型
			return ParamWebValueSort.baseType;
		}
//框架封装		
		if (vtype == FileModel.class) { // 文件上传类型
			return ParamWebValueSort.fileModel;
		}
		if (vtype == FileModel[].class) { // 文件上传类型
			return ParamWebValueSort.fileModel_N;
		}
		if (vtype == JWebMvc.class) { // 框架mvc中心
			return ParamWebValueSort.JWebMvc;
		}
//Servlet封装		
		if (vtype == HttpServletRequest.class) { // servlet-HttpServletRequest
			return ParamWebValueSort.HttpServletRequest;
		}
		if (vtype == HttpServletResponse.class) { // servlet-HttpServletResponse
			return ParamWebValueSort.HttpServletResponse;
		}
		if (vtype == HttpSession.class) { // servlet-HttpSession
			return ParamWebValueSort.HttpSession;
		}
//注解后		
		if (null != parameter.getAnnotation(JsonIO.class)) {
			return ParamWebValueSort.jsonIO;
		}
		if (null != parameter.getAnnotation(JsonKV.class)) {
			return ParamWebValueSort.JsonKV;
		}
//默认是用户的vo类型
		return ParamWebValueSort.vo;
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

	/**
	 * 参数归属——基本的（普通类型的键值对），fileModel模型的，jsonIO的，JsonKV的，Vo的
	 * 
	 */
	public final ParamWebValueSort paramType;
	private final MvcVoModel voModel[];// 当类型是vo时，专属vo字段处理的模型

	private MvcMethodParameterModel(Parameter parameter, String requestKey, ParamWebValueSort paramType) {
		this.parameter = parameter;
		this.parameterVoClassType = parameter.getType();
		this.requestKey = requestKey;
		this.paramType = paramType;
		if (this.paramType == ParamWebValueSort.vo || this.paramType == ParamWebValueSort.JsonKV) {
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
	public Object getValue(final HttpServletRequest req, final HttpServletResponse response) throws Exception {
		String value;
		switch (this.paramType) {
//基本类型 1			
		case baseType: {
			value = req.getParameter(requestKey);
			return null == value ? null : MvcStringConvertModel.getMvcDataConver(this.parameterVoClassType).toT(value);
		}
//框架封装 3		
		case fileModel: {
			return new FileModel(req, parameter);
		}
		case fileModel_N: {
			return new FileModel(req, parameter);
		}
		case JWebMvc: {
			return new JWebMvc(req, response);
		}
//Servlet封装 3
		case ServletRequest: {// servlet-ServletRequest
			return (ServletRequest) req;
		}
		case HttpServletRequest: {// servlet-HttpServletRequest
			return req;
		}
		case ServletResponse: {// servlet-ServletResponse
			return (ServletResponse) response;
		}
		case HttpServletResponse: {// servlet-HttpServletResponse
			return response;
		}
		case HttpSession: {// servlet-HttpSession
			return req.getSession();
		}

//注解后 2		
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
		case JsonKV: {
			value = req.getParameter(requestKey);
			if (null == value) {
				return null;
			}
			return JSON.parseObject(value, this.parameterVoClassType);
		}
//用户的vo类型 1(逻辑默认）
		case vo: {
			value = req.getParameter(requestKey);
			// 指定值是JSON结果时，强制json方式处理
			if (this.paramType == ParamWebValueSort.JsonKV) {
				return null == value || value.isEmpty() ? null : JSON.parseObject(value, this.parameterVoClassType);
			}
			// 当value不为null时，
			if (null == value) {// 当为null时，使用vo属性key，去取参数。如果一个参数都取不到，直接返回null
				Object voObject = MvcVoModel.getObject(parameterVoClassType);// 实例一个对象
				boolean hasValue = false;// 开关——默认无此vo任何一项属性值
				for (MvcVoModel vm : voModel) {
					if (null != (value = req.getParameter(vm.requestKey))) {// 从web请求中取值
						hasValue = true;// 开关，表示有值
						vm.setValue(voObject, value);// 装箱-设置vo对象属性值
					}
				}
				return hasValue ? voObject : null;// 如果找不到一项对应的属性，则直接返回null
			}
			// 当作json方式处理
			return JSON.parseObject(value, this.parameterVoClassType);
		}
//未知类型		
		default: {
			value = req.getParameter(requestKey);
			return null == value ? null : MvcStringConvertModel.getMvcDataConver(this.parameterVoClassType).toT(value);
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
		ParamWebValueSort paramType;

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
