package weixinkeji.vip.jweb.validate._common;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.scan.JWPScanClassFactory;
import weixinkeji.vip.jweb.validate.init._0_init_validateLib;

public class _ValidateDataCenter {
	/**
	 * 正则表达式库
	 */
	static final Map<String, JWebMVCValidateVo> sysValidationLib = new HashMap<>();
	// 用户vo最终表达式
	private static final Map<Class<?>, Map<Field, JWebMVCValidateVo>> voValidate = new HashMap<>();

	// 方法参数上的vo最终表达式
	private static final Map<Class<?>, Map<Parameter, JWebMVCValidateVo>> webParamValidate = new HashMap<>();
	private static boolean isInit = false;

	/**
	 * 通过key，取得关联的正则表达式
	 * 
	 * @param key 表达式key
	 * @return JWebMVCValidateVo
	 */
	public static JWebMVCValidateVo getJWebMVCValidateVoByRegexKey(String key) {
		return sysValidationLib.get(key);
	}

	/**
	 * 初始化
	 * 
	 * @param list 类集合
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
