package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
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
	
	
	
	public void setObjectValue(Field f,Object obj, String webVO) throws Exception {
		switch (f.getType().getSimpleName()) {
		case "String":
			f.set(obj, webVO);
			return;
		case "boolean":
		case "Boolean":
			f.set(obj, Boolean.parseBoolean(webVO));
			return;
		case "short":
		case "Short":
			f.set(obj, Short.parseShort(webVO));
			return;
		case "int":
		case "Integer":
			f.set(obj, Integer.parseInt(webVO));
			return;
		case "long":
		case "Long":
			f.set(obj, Long.parseLong(webVO));
			return;
		case "float":
		case "Float":
			f.set(obj, Float.parseFloat(webVO));
			return;
		case "double":
		case "Double":
			f.setDouble(obj, Double.parseDouble(webVO));
			return;
		case "Date":
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			f.set(obj, sf.parse(webVO));
		}
	}

}
