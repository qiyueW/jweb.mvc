package weixinkeji.vip.jweb.mvc._validate.model;

import java.util.Map;

import weixinkeji.vip.jweb.mvc._validate.ValidateResult;
import weixinkeji.vip.jweb.mvc._validate._common.JWebMVCValidateVo;
import weixinkeji.vip.jweb.mvc._validate._tools.Tools;
import weixinkeji.vip.jweb.mvc._validate.ann.RegexAttribute;
import weixinkeji.vip.jweb.mvc.bean.Hello;
import weixinkeji.vip.jweb.reflect.FieldModel;

/**
 * Vo校验模型
 * 
 * @author wangchunzi
 *
 */
public class VoValidateModel {
	private final Class<?> voClass;
	// vo-属性关联的校验数据
	public final Map<FieldModel, JWebMVCValidateVo> validate_ClassField;

	public VoValidateModel(Class<?> voClass) {
		this.voClass = voClass;
		// 当vo作为方法传参时，才有的注解符
		RegexAttribute rat = this.voClass.getAnnotation(RegexAttribute.class);
		this.validate_ClassField = Tools.getJWebMVCValidateVo_fromVo(this.voClass,
				null != rat ? rat.fieldsAlloyNull() : null);
	}

	public ValidateResult check(Object obj) {
		return checByVo(obj);
	}

	// 用户vo校验
	private ValidateResult checByVo(Object obj,boolean errorReturn) {
		ValidateResult vd = new ValidateResult();
		if (null == obj) {
			return vd;
		}
		Object ovalue;
		for (Map.Entry<FieldModel, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
			try {
				ovalue = kv.getKey().getFieldValue(obj);
				if (null == ovalue||!ovalue.toString().matches(kv.getValue().regex)) {
					System.out.println(kv.getKey().fieldKey + "校验失败！");
					vd.addError(kv.getKey().fieldKey, kv.getValue().errorMessage);
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vd;
	}

	public static void main(String args[]) {
		Hello obj = new Hello();
		obj.setHelloId("1");
		obj.setHelloName("ab", 1, null);
		VoValidateModel model=new VoValidateModel(Hello.class);
		System.out.println(model.check(obj).toJsonStrs());
	}
}
