package weixinkeji.vip.jweb.mvc._init;

import java.util.List;

import weixinkeji.vip.jweb.mvc._component.mvc_bind_url.MvcBindUrl;
import weixinkeji.vip.jweb.mvc._component.mvc_bind_url.MvcBindUrlDefaultImpl;
import weixinkeji.vip.jweb.mvc._component.mvc_bind_url.MvcBindUrlModel;

final public class _1_InitMvcBindUrlModel extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _1_InitMvcBindUrlModel(List<Class<?>> list) {
		super(list);
		this.initConfig();
	}
	
	/**
	 * 初始化配置
	 * 
	 */
	@Override
	protected void initConfig() {
		MvcBindUrlModel.init(super.findObject(MvcBindUrl.class,new MvcBindUrlDefaultImpl()));
	}
}
