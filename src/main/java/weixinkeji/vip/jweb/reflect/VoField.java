package weixinkeji.vip.jweb.reflect;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public interface VoField {

	/**
	 * 反射 给vo对象的属性，设置上value性值
	 * 
	 * @param vo 
	 * @param value
	 */
	default void setValue(Field f,Object vo, String value) throws Exception {
		switch (f.getType().getSimpleName()) {
		case "String":
			f.set(vo, value);
			return;
		case "boolean":
		case "Boolean":
			f.set(vo, Boolean.parseBoolean(value));
			return;
		case "short":
		case "Short":
			f.set(vo, Short.parseShort(value));
			return;
		case "int":
		case "Integer":
			f.set(vo, Integer.parseInt(value));
			return;
		case "long":
		case "Long":
			f.set(vo, Long.parseLong(value));
			return;
		case "float":
		case "Float":
			f.set(vo, Float.parseFloat(value));
			return;
		case "double":
		case "Double":
			f.setDouble(vo, Double.parseDouble(value));
			return;
		case "Date":
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			f.set(vo, sf.parse(value));
		}
	}
}
