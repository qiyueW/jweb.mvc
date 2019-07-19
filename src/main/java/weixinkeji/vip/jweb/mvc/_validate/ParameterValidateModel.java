package weixinkeji.vip.jweb.mvc._validate;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._validate.ann.RegexAttribute;

/**
 * Controller方法 参数模型
 * 
 * @author wangchunzi
 *
 */
public class ParameterValidateModel {

	/**
	 * 如何取得參數的值
	 * 
	 * @author wangchunzi
	 */
	public enum paramCheckType {
		/**
		 * 键值对方式
		 */
		vo(1),
		/**
		 * json方式
		 */
		javaType(2),
		/**
		 * 键值对方式，但值是json格式
		 */
		noCheck(0);
		public final int code;

		private paramCheckType(int i) {
			this.code = i;
		}
	}

	public final Class<?> parameTerType;// 参数类型
	// 參數綁定的校驗
	public final Map<String, JWebMVCValidateVo> validate_ClassField;
	private final Field[] fs = {};

	// 普通類型綁定的校驗
	public final JWebMVCValidateVo onValidate;
	private final int checkType;

	public ParameterValidateModel(Parameter pobj) {
		this.parameTerType = pobj.getType();
		if (Tools.isJavaBaseType(pobj.getType())) {
			this.validate_ClassField = null;
			this.onValidate = Tools.getJWebMVCValidateVo_fromJavaBaseParamter(pobj);
		} else {
			RegexAttribute rat = pobj.getAnnotation(RegexAttribute.class);
			this.validate_ClassField = Tools.getJWebMVCValidateVo_fromVo(pobj.getType(),
					null != rat ? rat.fieldsAlloyNull() : null);
			this.onValidate = null;
		}
		if (null != this.validate_ClassField) {
			checkType = paramCheckType.vo.code;// 用户vo类型的校验
		} else if (null != onValidate) {
			checkType = paramCheckType.javaType.code;// 普通类型的校验
		} else {
			checkType = paramCheckType.noCheck.code;// 无校验。
		}
	}

	public boolean check(Object obj) {
		// 不校验的
		if (checkType == paramCheckType.noCheck.code) {
			return true;
		}
		return false;
	}

	// java基本类型的校验
	private boolean checkByJavaBaseType(Object obj) {
		if (null == obj) {
			return this.onValidate.alloyNull;// 是否允许null
		}
		return obj.toString().matches(this.onValidate.regex);
	}

	// 用户vo校验
	private boolean checByVo(Object obj) {
		
		return false;
	}

}
