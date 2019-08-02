package weixinkeji.vip.jweb.mvc._init;

import java.util.List;

import weixinkeji.vip.jweb.mvc._component.mvc_mp_model.MvcMethodParameterModelConfig;
import weixinkeji.vip.jweb.mvc._component.mvc_mp_model._InitMethodParameterModelConfig;

public class _1_InitMvcMethodParameterConfigModel extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _1_InitMvcMethodParameterConfigModel(List<Class<?>> list) {
		super(list);
		this.initConfig();
	}

	/**
	 * 初始化配置
	 */
	private void initConfig() {
		_InitMethodParameterModelConfig.init(super.findClass_T(MvcMethodParameterModelConfig.class));
	}
}
