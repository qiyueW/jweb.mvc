package weixinkeji.vip.jweb.mvc._init;

import java.util.List;

import weixinkeji.vip.jweb.mvc._component.mvc_mp_model.MvcMethodParameterModelConfig;
import weixinkeji.vip.jweb.mvc._component.mvc_mp_model.MvcMethodParameterModelConfigModel;

final public class _3_InitMvcMethodParameterConfigModel extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _3_InitMvcMethodParameterConfigModel(List<Class<?>> list) {
		super(list);
		this.initConfig();
	}

	/**
	 * 初始化配置
	 */
	@Override
	protected void initConfig() {
		MvcMethodParameterModelConfigModel.init(super.findClass_T(MvcMethodParameterModelConfig.class));
	}
}
