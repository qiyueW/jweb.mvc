package weixinkeji.vip.jweb.code.di.model;

import java.lang.reflect.InvocationTargetException;

import weixinkeji.vip.jweb.code.di.ann.JWebDINewType;

public class DiObject {

	/**
	 * 是单例（默认） 还是多例
	 */
	public final NewType newType;
	/**
	 * 单例时
	 */
	private Object singletonObject;

	/**
	 * 多例时
	 */
	public final Class<?> yourClass;
	// 方便判断——是否是单例（true:是； false:否）
	private final boolean isSingleton;
	// 注册源：true=来自注解； false=来自手工注册
	private final boolean regSourceByAnnotation;
	// 手工注册源
	private final RegDiObject reg;
	// 绑定属性文件 处理类
	BingClassPathPropertiesModel bindPropertiesModel = new BingClassPathPropertiesModel();
	
	public DiObject(Class<?> c) {
		JWebDINewType myNewType = c.getAnnotation(JWebDINewType.class);
		this.regSourceByAnnotation = true;// 注册源
		this.newType = null == myNewType || null == myNewType.value() ? NewType.singleton : myNewType.value();
		this.yourClass = c;
		try {
			singletonObject = this.newType == NewType.singleton ? c.getConstructor().newInstance() : null;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			this.singletonObject = null;
		} // 如果是单例模式，把实例附值给 静态的obj
		this.isSingleton = null != this.singletonObject;

		this.reg = null;
	}

	public void initObjectField(Object obj) {
		// 判断是否绑定了属性文件——通过特定的注解判断
		if (!bindPropertiesModel.isPropertiesAnnatotion_1()) {
			return;// 如果没有绑定，直接中止
		}
		// 加载配置文件
		if(bindPropertiesModel.loadProperties_1()<1) {//绑定的配置文件 没有找到，或读取时io流异常
			return;//中止
		}
	}

	public DiObject(RegDiObject reg) {
		this.regSourceByAnnotation = false;// 不从注解中来
		Object robj = reg.reg();// 不管是什么，调用一次，取一次实例
		this.newType = reg.regObjectSort();// 单例还是原型
		this.yourClass = robj.getClass();// 取得用户注册的类型
		this.singletonObject = this.newType == NewType.singleton ? robj : null;// 如果是单例模式，把实例附值给 静态的obj
		this.reg = this.newType == NewType.prototype ? reg : null;// 如果是原型，缓存RegDiObject，方便每次访问调用 reg.reg() 取实例
		this.isSingleton = null != this.singletonObject;// 方便校验，是否是单例
	}

	/**
	 * 取得实例
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.isSingleton ? this.singletonObject : newObject();
	}

	private Object newObject() {

		try {
			if (this.regSourceByAnnotation) {
				return yourClass.getConstructor().newInstance();
			}
			return reg.reg();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
