package weixinkeji.vip.jweb.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._init._0_init_validate;
import weixinkeji.vip.jweb.mvc.vo.JWebMVCValidateVo;
import weixinkeji.vip.jweb.scan.JWPScanClassFactory;

public class DataCenter {

	/**
	 * 正则表达式
	 */
	static final Map<String, JWebMVCValidateVo> sysValidation = new HashMap<>();
	static {
		//JWebMvc.properties
		List<Class<?>> scanclass=JWPScanClassFactory.getClassByFilePath("xxxxxxxxxx");
		//初始化正则表达式库
		new _0_init_validate(scanclass).initConfig(sysValidation);
		
		
		
	}
}
