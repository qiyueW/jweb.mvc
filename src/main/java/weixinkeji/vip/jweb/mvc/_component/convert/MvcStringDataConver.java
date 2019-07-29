package weixinkeji.vip.jweb.mvc._component.convert;

public interface MvcStringDataConver<T> {

	/**
	 * String值，转成<T>类型
	 * 
	 * @param <T>      泛型
	 * @param webValue String类型的值
	 * @param t        转成的类型
	 * @return T 转换的类型的实例
	 */
	public T toT(String webValue);


}
