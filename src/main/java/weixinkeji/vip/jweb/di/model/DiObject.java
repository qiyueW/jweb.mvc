package weixinkeji.vip.jweb.di.model;

import java.lang.reflect.InvocationTargetException;

public class DiObject {

	/**
	 * 是单例（默认） 还是多例
	 */
	public final NewType newType;
	/**
	 * 单例时
	 */
	private static Object singletonObject;

	/**
	 * 多例时
	 */
	private final Class<?> c;
	// 方便判断——是否是单例（true:是； false:否）
	private final boolean isSingleton;
	// 注册源：true=来自注解； false=来自手工注册
	private final boolean regSourceByAnnotation;
	// 手工注册源
	private final RegDiObject reg;

	public DiObject(NewType newType, Class<?> c) {
		super();
		this.regSourceByAnnotation = true;// 注册源
		this.newType = newType;
		this.c = c;
		try {
			singletonObject = this.newType == NewType.singleton ? c.getConstructor().newInstance() : null;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} // 如果是单例模式，把实例附值给 静态的obj
		this.isSingleton = null != singletonObject;

		this.reg = null;
	}

	public DiObject(RegDiObject reg) {
		this.regSourceByAnnotation = false;// 不从注解中来
		Object robj = reg.reg();// 不管是什么，调用一次，取一次实例
		this.newType = reg.regObjectSort();// 单例还是原型
		this.c = robj.getClass();// 取得用户注册的类型
		singletonObject = this.newType == NewType.singleton ? robj : null;// 如果是单例模式，把实例附值给 静态的obj
		this.reg = this.newType == NewType.prototype ? reg : null;// 如果是原型，缓存RegDiObject，方便每次访问调用 reg.reg() 取实例
		this.isSingleton = null != singletonObject;// 方便校验，是否是单例

	}

	/**
	 * 取得实例
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.isSingleton ? singletonObject : newObject();
	}
	
	private Object newObject() {

		try {
			if (this.regSourceByAnnotation) {
				return c.getConstructor().newInstance();
			}
			return reg.reg();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
