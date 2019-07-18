package weixinkeji.vip.jweb.mvc._validate.config;

import java.util.Map;

import weixinkeji.vip.jweb.mvc._validate.JWebMVCValidateVo;

public interface JWebMVCValidateLib {

	/**
	 * 注册正则表达式 
	 * 
	 * @param kv Map
	 */
	void regRegex(Map<String, JWebMVCValidateVo> kv);

}
