package weixinkeji.vip.jweb.mvc._validate;

import java.lang.reflect.Parameter;

import weixinkeji.vip.jweb.mvc._validate.ann.BingRegex;

class Tools {
	/**
	 * 是否java基本类型
	 * 
	 * @param type
	 * @return boolean
	 * @throws Exception
	 */
	static boolean isJavaBaseType(Class<?> type) {
		switch (type.getSimpleName()) {
		case "String":
		case "boolean":
		case "Boolean":
		case "short":
		case "Short":
		case "int":
		case "Integer":
		case "long":
		case "Long":
		case "float":
		case "Float":
		case "double":
		case "Double":
		case "Date":
			return true;
		default:
			return false;
		}
	}

	/**
	 * 取得java官方基本类型 绑定的校验数据
	 * 
	 * @param pobj 参数
	 * @return JWebMVCValidateVo 校验数据
	 */
	static JWebMVCValidateVo getJWebMVCValidateVo_fromJavaBaseParamter(Parameter pobj) {
		return getRegex(pobj.getAnnotation(BingRegex.class));
	}

	/**
	 * 取得属性上的校验数据
	 * 
	 * @param br BingRegex
	 * @return JWebMVCValidateVo
	 */
	static JWebMVCValidateVo getRegex(BingRegex br) {
		if (null == br) {
			return null;
		}
		String regex = br.regex();// 正则表达式
		String error = br.error();// 错误信息
		boolean alloyNull = br.alloyNull();// 是否允许null;
		String[] placeholder = br.placeholder();
		JWebMVCValidateVo ref = _ValidateDataCenter.getJWebMVCValidateVoByRegexKey(regex);
		if (null != ref) {// 表示 不是引用公共库的表达式
			regex = ref.regex;// 使用引用过来的表达式
			if (null == error || error.trim().isEmpty()) {// 如果用户填写错误提示信息，则用引用的
				error = ref.errorMessage;
			}
			if (br.refAlloyNull() == RegexNullType.unknow) {// 如果用户填写是否允许null，则用引用的
				alloyNull = ref.alloyNull;
			}
		}
		regex = formatStrByPlaceholder(regex, placeholder);// 占位符处理
		return new JWebMVCValidateVo(regex, error, alloyNull);
	}

	/**
	 * 对占位符，逐一替换。<br>
	 * 占位符语法： ??下标 <br>
	 * 如：??1 表达第1个占位符，??2表达第2个占位符
	 * 
	 * @param str         源
	 * @param placeholder 替换占位符的数据
	 * @return String 被替换后的源
	 */
	private static String formatStrByPlaceholder(String str, String... placeholder) {// 逐一替换
		if (null != placeholder && placeholder.length > 0) {// 如果存在占位符
			for (int i = 0; i < placeholder.length; i++) {
				str = str.replace("??" + (i + 1), placeholder[i]);// 逐一替换
			}
			return str;
		}
		return str;
	}
}
