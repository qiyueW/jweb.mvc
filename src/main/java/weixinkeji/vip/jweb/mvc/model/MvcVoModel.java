package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * vo用于mvc的拓展模型
 * 
 * @author wangchunzi
 *
 */
public class MvcVoModel {

	// voModel集合
	private static final Map<Class<?>, MvcVoModel[]> voModel = new HashMap<>();

	/**
	 * 属性
	 */
	public final Field f;
	/**
	 * 接受web参数的key.默认等于属性名
	 */
	public final String requestKey;
	/**
	 * 时间格式-当Field类型是Date时，才生效
	 */
	public final String dateFormat;

	public MvcVoModel(Field f, String requestKey, String dateFormat) {
		this.f = f;
		this.requestKey = requestKey;
		this.dateFormat = dateFormat;
	}

}
