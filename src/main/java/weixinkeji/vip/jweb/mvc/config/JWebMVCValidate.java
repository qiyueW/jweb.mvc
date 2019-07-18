package weixinkeji.vip.jweb.mvc.config;

import java.util.Map;

import weixinkeji.vip.jweb.mvc.vo.JWebMVCValidateVo;

public interface JWebMVCValidate {

	/**
	 * 注册正则表达式 
	 * 
	 * @param kv Map
	 */
	void regRegex(Map<String, JWebMVCValidateVo> kv);

}
