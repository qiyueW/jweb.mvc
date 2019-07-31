package weixinkeji.vip.jweb.mvc._init;

import java.util.List;

import weixinkeji.vip.jweb.mvc.config.ConfigVoTemp;
import weixinkeji.vip.jweb.mvc.config.JWebMVCConfig;
import weixinkeji.vip.jweb.mvc.config.JWebMVCConfigDefaultImpl;
import weixinkeji.vip.jweb.mvc.config._InitConfigJWebMvcConfig;

public class _0_InitMvcConfig extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _0_InitMvcConfig(List<Class<?>> list) {
		super(list);
		this.initConfig();
	}

	/**
	 * 初始化配置
	 * 
	 * @param sysValidationLib 正则表达式数据中心
	 */
	private void initConfig() {
		ConfigVoTemp tempVo = new ConfigVoTemp();// 配置载体
		JWebMVCConfig config = super.findObject(JWebMVCConfig.class, new JWebMVCConfigDefaultImpl());// 找配置。找不到则采用默认的实现类
		config.setJWebMvcConfig(tempVo);// 加载用户配置
		new _InitConfigJWebMvcConfig().init(tempVo);// 初始化配置（把用户配置，装到系统配置vo）
	}
}
