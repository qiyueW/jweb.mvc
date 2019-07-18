package weixinkeji.vip.jweb.mvc._init;

import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.mvc.config.JWebMVCValidate;
import weixinkeji.vip.jweb.mvc.config.impl.JWebMVCValidateImpl;
import weixinkeji.vip.jweb.mvc.vo.JWebMVCValidateVo;

public class _0_init_validate extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _0_init_validate(List<Class<?>> list) {
		super(list);
	}

	/**
	 * 初始化配置
	 * @param sysValidation 正则表达式数据中心
	 */
	public void initConfig(Map<String, JWebMVCValidateVo> sysValidation ) {
		//系统默认的配置
		this.initSysConfig(sysValidation);
		//用户的配置
		JWebMVCValidate obj=super.findObject(JWebMVCValidate.class,new JWebMVCValidateImpl());
		obj.regRegex(sysValidation);
	}
	
	private void initSysConfig(Map<String, JWebMVCValidateVo> sysValidation ) {
		sysValidation.put("sysEmail", new JWebMVCValidateVo("",""));
		
	}
	
	
}
