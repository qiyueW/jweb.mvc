package weixinkeji.vip.jweb.mvc._validate.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._DoControllerMethod;
import weixinkeji.vip.jweb.mvc._validate.ValidateResult;
import weixinkeji.vip.jweb.mvc._validate._common.JWebMVCValidateVo;
import weixinkeji.vip.jweb.mvc._validate._tools.Tools;
import weixinkeji.vip.jweb.mvc._validate.ann.RegexAttribute;
import weixinkeji.vip.jweb.mvc.bean.Hello;

/**
 * Vo校验模型
 * 
 * @author wangchunzi
 *
 */
public class VoValidateModel {
	private final Class<?> voClass;
	// vo-属性关联的校验数据
	public final Map<Field, JWebMVCValidateVo> validate_ClassField;
	public static final Map<Class<?>, VoValidateModel> voMap = new HashMap<>();
	public static final Map<Parameter, VoValidateModel> voParamMap = new HashMap<>();

	synchronized public static VoValidateModel getVoValidateModel(Class<?> c) {
		VoValidateModel vo = voMap.get(c);
		if (null != vo) {
			return vo;
		}
		vo = new VoValidateModel(c);
		voMap.put(c, vo);
		return vo;
	}

	synchronized public static VoValidateModel getVoValidateModel(Parameter param) {
		VoValidateModel vo = voParamMap.get(param);
		if (null != vo) {
			return vo;
		}
		vo = new VoValidateModel(param);
		voParamMap.put(param, vo);
		return vo;
	}

	private VoValidateModel(Class<?> voClass) {
		this.voClass = voClass;
		// 当vo作为方法传参时，才有的注解符
		RegexAttribute rat = this.voClass.getAnnotation(RegexAttribute.class);
		this.validate_ClassField = Tools.getJWebMVCValidateVo_fromVo(this.voClass,
				null != rat ? rat.fieldsAlloyNull() : null);
	}

	private VoValidateModel(Parameter param) {
		this.voClass = param.getType();
		// 当vo作为方法传参时，才有的注解符
		RegexAttribute rat = param.getAnnotation(RegexAttribute.class);
		this.validate_ClassField = Tools.getJWebMVCValidateVo_fromVo(this.voClass,
				null != rat ? rat.fieldsAlloyNull() : null);
	}

	public ValidateResult check(Object obj, boolean errorReturn) {
		return checByVo(obj, errorReturn);
	}
	
	/**
	 * 用户vo校验
	 * 
	 * @param obj         vo实例
	 * @param errorReturn 是否遇错返回;true表示是；false表示否
	 * @return ValidateResult
	 */
	private ValidateResult checByVo(Object obj, boolean errorReturn) {
		ValidateResult vd = new ValidateResult();
		if (null == obj) {
			return vd;
		}
		Object ovalue;
		if (errorReturn) {
			for (Map.Entry<Field, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
				try {
					ovalue = kv.getKey().get(obj);// 取出字段的值
					if (null == ovalue) {// 当值为null时，只有一种情况可以通过——规则允许为null
						if (kv.getValue().alloyNull) {// 如果允许字段为null,表示正常通过
							continue;// 继续下一个校验
						} else {
							vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
							return vd;
						}
					}
					// 执行正则表达式校验。如果不通过，直接返回
					if (!ovalue.toString().matches(kv.getValue().regex)) {
						System.out.println(kv.getKey().getName() + "校验失败！");
						vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
						return vd;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			for (Map.Entry<Field, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
				try {
					ovalue = kv.getKey().get(obj);
					if (null == ovalue) {// 当值为null时，只有一种情况可以通过——规则允许为null
						if (kv.getValue().alloyNull) {// 如果允许字段为null,表示正常通过
							continue;// 继续下一个校验
						} else {
							vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
							continue;// 继续下一个校验
						}
					}
					if (!ovalue.toString().matches(kv.getValue().regex)) {
						System.out.println(kv.getKey().getName() + "校验失败！");
						vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vd;
	}

	public static void main(String args[]) {
		Method[] ms = _DoControllerMethod.class.getDeclaredMethods();
		Method m1 = ms[0];
		Method m2 = ms[1];
		Parameter p1 = m1.getParameters()[1];
		Parameter p2 = m2.getParameters()[1];
		System.out.println(p1 == p2);
		System.out.println(p1.getType() + "//" + p2.getType());

		Hello obj = new Hello();
		obj.setHelloId("1");
		obj.setHelloName("ab", 1, null);
		VoValidateModel model = VoValidateModel.getVoValidateModel(Hello.class);
		System.out.println(model.check(obj, false).toJsonStrs());
		model = VoValidateModel.getVoValidateModel(p1);
		System.out.println(model.check(obj, false).toJsonStrs());
		model = VoValidateModel.getVoValidateModel(p2);
		System.out.println(model.check(obj, false).toJsonStrs());
	}
}
