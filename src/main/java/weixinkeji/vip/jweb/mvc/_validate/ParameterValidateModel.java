package weixinkeji.vip.jweb.mvc._validate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._DoControllerMethod;
import weixinkeji.vip.jweb.mvc._validate.ann.RegexAttribute;
import weixinkeji.vip.jweb.mvc.bean.Hello;

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
	// vo-属性关联的校验数据
	public final Map<Field, JWebMVCValidateVo> validate_ClassField;

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
		} else if (checkType == paramCheckType.javaType.code) {
			return checkByJavaBaseType(obj);
		} else {
			return checByVo(obj);
		}
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
		if (null == obj) {
			return this.validate_ClassField.isEmpty();// 传来的对象是null,同时，用户没有强制校验项，返回true
		}
		Object ovalue;
		for (Map.Entry<Field, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
			try {
				ovalue = kv.getKey().get(obj);
				if (null == ovalue) {
					return kv.getValue().alloyNull;
				}
				if (!ovalue.toString().matches(kv.getValue().regex)) {
					return false;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static void main(String args[]) {
		Method[] ms = _DoControllerMethod.class.getDeclaredMethods();
		for (Method m : ms) {
			System.out.println("方法名：" + m.getName() + "//" + m.getModifiers() + "//" + m.getParameterCount());
			for (Parameter p : m.getParameters()) {
				System.out.println(p + "=" + p.getModifiers() + "//" + p.getName() + "//" + p.getClass() + p.getType());
				ParameterValidateModel model = new ParameterValidateModel(p);
				if (p.getType() == Hello.class) {
					Hello obj = new Hello();
					obj.setHelloId("1", "2");
					obj.setHelloName("ab", 1, null);
					System.out.println(model.check(obj));
				}
			}
		}

	}
}
