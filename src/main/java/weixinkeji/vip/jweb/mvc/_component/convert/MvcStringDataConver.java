package weixinkeji.vip.jweb.mvc._component.convert;

public interface MvcStringDataConver<T> {

	/**
	 * String值，转成T类型
	 * 
	 * @param webValue String类型的值
	 * @return T 转换的类型的实例
	 */
	public T toT(String webValue);


}
