package weixinkeji.vip.jweb;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class FieldModel {

	public final Field f;
	public final fieldType type;
	public final String dateFormat;

	public FieldModel(Field f) {
		this.f = f;
		this.f.setAccessible(true);
		this.type = getFileType(f);
		this.dateFormat = "yyyy-MM-dd";
	}

	public FieldModel(Field f, String dateFormat) {
		this.f = f;
		this.f.setAccessible(true);
		this.type = getFileType(f);
		this.dateFormat = dateFormat;
	}

	enum fieldType {
		type_String, type_boolean, type_Boolean, type_short, type_Short, type_int, type_Integer, type_long, type_Long,
		type_float, type_Float, type_double, type_Double, type_Date, type_noJavaBaseType
	}

	private fieldType getFileType(Field f) {
		switch (f.getType().getSimpleName()) {
		case "String":
			return fieldType.type_String;
		case "boolean":
			return fieldType.type_boolean;
		case "Boolean":
			return fieldType.type_Boolean;
		case "short":
			return fieldType.type_short;
		case "Short":
			return fieldType.type_Short;
		case "int":
			return fieldType.type_int;
		case "Integer":
			return fieldType.type_Integer;
		case "long":
			return fieldType.type_long;
		case "Long":
			return fieldType.type_Long;
		case "float":
			return fieldType.type_float;
		case "Float":
			return fieldType.type_Float;
		case "double":
			return fieldType.type_double;
		case "Double":
			return fieldType.type_Double;
		case "Date":
			return fieldType.type_Date;
		default:
			return fieldType.type_noJavaBaseType;
		}
	}

	/**
	 * 设置属性值
	 * 
	 * @param obj   对象
	 * @param webVO String类型的值（被会强制转成属性类型，并设置进属性）
	 * @throws Exception 异常
	 */
	public void setObjectValue(Object obj, String webVO) throws Exception {
		switch (this.type) {
		case type_String:
			f.set(obj, webVO);
			return;
		case type_boolean:
		case type_Boolean:
			f.set(obj, Boolean.parseBoolean(webVO));
			return;
		case type_short:
		case type_Short:
			f.set(obj, Short.parseShort(webVO));
			return;
		case type_int:
		case type_Integer:
			f.set(obj, Integer.parseInt(webVO));
			return;
		case type_long:
		case type_Long:
			f.set(obj, Long.parseLong(webVO));
			return;
		case type_float:
		case type_Float:
			f.set(obj, Float.parseFloat(webVO));
			return;
		case type_double:
		case type_Double:
			f.setDouble(obj, Double.parseDouble(webVO));
			return;
		case type_Date:
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			f.set(obj, sf.parse(webVO));
			return;
		default:
			break;
		}
	}
	
	/**
	 * 取得属性值
	 * 
	 * @param obj 对象
	 * @return Object 属性值
	 * @throws Exception 异常
	 */
	public Object getFieldValue(Object obj) throws Exception {
		switch (this.type) {
		case type_String:
			return f.get(obj);
		case type_boolean:
		case type_Boolean:
			return f.getBoolean(obj);
		case type_short:
		case type_Short:
			return f.getShort(obj);
		case type_int:
		case type_Integer:
			return f.getInt(obj);
		case type_long:
		case type_Long:
			return f.getLong(obj);
		case type_float:
		case type_Float:
			return f.getFloat(obj);
		case type_double:
		case type_Double:
			return f.getDouble(obj);
		case type_Date:
			return f.get(obj);
		default:
			return f.get(obj);
		}
	}

}
