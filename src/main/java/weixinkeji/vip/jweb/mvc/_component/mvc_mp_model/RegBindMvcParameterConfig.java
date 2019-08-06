package weixinkeji.vip.jweb.mvc._component.mvc_mp_model;

import java.util.List;

public interface RegBindMvcParameterConfig {

	/**
	 * 注册自定义的参数类型
	 * 
	 * @param reg List 存放容器
	 */
	public void regMyParameter(List<Class<? extends RegMvcParameter>> reg);
}
