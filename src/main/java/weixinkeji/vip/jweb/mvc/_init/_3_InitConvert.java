package weixinkeji.vip.jweb.mvc._init;

import java.util.List;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;
import weixinkeji.vip.jweb.mvc._component.convert._InitConvert;

final public class _3_InitConvert extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _3_InitConvert(List<Class<?>> list) {
		super(list);
		this.initConfig();
	}

	/**
	 * 初始化配置
	 * 
	 */
	@Override
	protected  void initConfig() {
		_InitConvert.init(super.findClass_T(MvcStringDataConver.class));
	}
}
