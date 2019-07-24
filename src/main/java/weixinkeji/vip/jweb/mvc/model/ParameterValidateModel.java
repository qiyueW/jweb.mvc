package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.Field;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._validate._common.JWebMVCValidateVo;

/**
 * Controller方法 参数模型
 * 
 * @author wangchunzi
 *
 */
public class ParameterValidateModel {

	/**
	 * 如何取得參數的值
	 * @author wangchunzi
	 */
	public enum paramGetType {
		/**
		 * 键值对方式 
		 */
		keyValue(1),
		/**
		 * json方式 
		 */
		json(2),
		/**
		 *键值对方式，但值是json格式 
		 */
		keyValueByJson(3),
		/**
		 * io流方式 
		 */
		io(4);
		public final int code;
		private paramGetType(int i) {
			this.code = i;
		}
	}

	public final Class<?> parameTerType;// 参数类型
	public final String key;//用來取值的key
	public final int getParam;// 客户端传来的参数方式：键值对 ；json; 键值对里的json
	//參數綁定的校驗
	public final Map<Field,JWebMVCValidateVo> validate_ClassField;
	//普通類型綁定的校驗
	public final JWebMVCValidateVo onValidate;
	public final int checkType;
	
	public ParameterValidateModel(Class<?> ptype,String key,paramGetType type,Map<Field,JWebMVCValidateVo> validate_ClassField,JWebMVCValidateVo onValidate) {
		this.parameTerType=ptype;
		this.getParam=type.code;
		this.key=key;
		this.validate_ClassField=validate_ClassField;
		this.onValidate=onValidate;
		if(null!=this.validate_ClassField) {
			checkType=1;//用户vo类型的校验
		}else if(null!=onValidate) {
			checkType=2;//普通类型的校验
		}else {
			checkType=0;//无校验。
		}
	}
	
	
	
	/**
	 * web傳來的值，
	 * @param data
	 * @return
	 */
	public Object getValue(String data) {
		return null;
	}
	
}
