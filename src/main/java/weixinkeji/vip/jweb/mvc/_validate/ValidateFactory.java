package weixinkeji.vip.jweb.mvc._validate;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._validate.init._0_init_validateLib;
import weixinkeji.vip.jweb.scan.JWPScanClassFactory;

public class ValidateFactory {

	/**
	 * 正则表达式库
	 */
	static final Map<String, JWebMVCValidateVo> sysValidationLib = new HashMap<>();
	// 用户vo最终表达式
	private static final Map<Class<?>, Map<Field, JWebMVCValidateVo>> voValidate = new HashMap<>();
	
	// 方法参数上的vo最终表达式
	private static final Map<Class<?>, Map<Parameter, JWebMVCValidateVo>> webParamValidate = new HashMap<>();
	private static boolean isInit = false;
	
	
	
	synchronized private static Map<Field, JWebMVCValidateVo> getClassFieldsRegex(Class<?> c){
		Map<Field, JWebMVCValidateVo> kv=voValidate.get(c);
		if(null!=kv) {
			return kv;
		}
		kv=new HashMap<>();
		
		
		voValidate.put(c, kv);
		return kv;
	}
	
	/**
	 * 初始化
	 * 
	 * @param list
	 */
	synchronized public static void _0_init(List<Class<?>> list) {
		if (isInit) {
			return;
		}
		isInit = true;
		// 初始化：
		// JWebMvc.properties
		List<Class<?>> scanclass = JWPScanClassFactory.getClassByFilePath("xxxxxxxxxx");
		// 初始化正则表达式库
		new _0_init_validateLib(scanclass).initConfig(sysValidationLib);

	}

}