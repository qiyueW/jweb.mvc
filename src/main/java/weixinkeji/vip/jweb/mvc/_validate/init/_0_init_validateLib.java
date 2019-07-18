package weixinkeji.vip.jweb.mvc._validate.init;

import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._init.__InitModel;
import weixinkeji.vip.jweb.mvc._validate.JWebMVCValidateVo;
import weixinkeji.vip.jweb.mvc._validate.config.JWebMVCValidateLib;
import weixinkeji.vip.jweb.mvc._validate.config.JWebMVCValidateLibImpl;

public class _0_init_validateLib extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _0_init_validateLib(List<Class<?>> list) {
		super(list);
	}

	/**
	 * 初始化配置
	 * @param sysValidationLib 正则表达式数据中心
	 */
	public void initConfig(Map<String, JWebMVCValidateVo> sysValidationLib ) {
		//系统默认的配置
		this.initSysConfig(sysValidationLib);
		//用户的配置
		JWebMVCValidateLib obj=super.findObject(JWebMVCValidateLib.class,new JWebMVCValidateLibImpl());
		obj.regRegex(sysValidationLib);
	}
	
	private void initSysConfig(Map<String, JWebMVCValidateVo> sysValidationLib ) {
		sysValidationLib.put("sysEmail", new JWebMVCValidateVo("",""));
		
	}
	
	
}
