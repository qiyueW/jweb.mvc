package weixinkeji.vip.jweb.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class FieldModel {

	/**
	 * 类属性字段
	 */
	public final Field field;

	/**
	 * 类属性 名称（变量名）
	 */
	public final String fieldName;

	/**
	 * 类属性 名称（变量名）的别名
	 */
	public final String fieldKey;
	/**
	 * 类属性 类型
	 */
	public final JavaType type;

	/**
	 * 类属性 时间格式（仅当是Date类型的属性时，才有效），默认是 yyyy-MM-dd
	 */
	private String dateFormat = "yyyy-MM-dd";

	public FieldModel(Field f) {
		this.field = f;
		this.fieldName = f.getName();
		this.fieldKey = f.getName();
		this.field.setAccessible(true);
		this.type = getFileType(f);
	}

	public FieldModel(Field f, String dateFormat) {
		this.field = f;
		this.fieldName = f.getName();
		this.fieldKey = f.getName();
		this.field.setAccessible(true);
		this.type = getFileType(f);
		this.dateFormat = dateFormat;
	}

	private JavaType getFileType(Field f) {
		switch (f.getType().getSimpleName()) {
		case "String":
			return JavaType.type_String;
		case "boolean":
			return JavaType.type_boolean;
		case "Boolean":
			return JavaType.type_Boolean;
		case "short":
			return JavaType.type_short;
		case "Short":
			return JavaType.type_Short;
		case "int":
			return JavaType.type_int;
		case "Integer":
			return JavaType.type_Integer;
		case "long":
			return JavaType.type_long;
		case "Long":
			return JavaType.type_Long;
		case "float":
			return JavaType.type_float;
		case "Float":
			return JavaType.type_Float;
		case "double":
			return JavaType.type_double;
		case "Double":
			return JavaType.type_Double;
		case "Date":
			return JavaType.type_Date;
		default:
			return JavaType.type_noJavaBaseType;
		}
	}

	public <T extends Annotation> boolean isHasAnnotation(Class<T> annotationClass) {
		return null == this.field.getAnnotation(annotationClass);
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		return this.field.getAnnotation(annotationClass);
	}

	/**
	 * 设置日期格式，仅当字段类型是是Date类型时有效
	 * 
	 * @param dateFormat String 时间格式
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
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
			field.set(obj, webVO);
			return;
		case type_boolean:
		case type_Boolean:
			field.set(obj, Boolean.parseBoolean(webVO));
			return;
		case type_short:
		case type_Short:
			field.set(obj, Short.parseShort(webVO));
			return;
		case type_int:
		case type_Integer:
			field.set(obj, Integer.parseInt(webVO));
			return;
		case type_long:
		case type_Long:
			field.set(obj, Long.parseLong(webVO));
			return;
		case type_float:
		case type_Float:
			field.set(obj, Float.parseFloat(webVO));
			return;
		case type_double:
		case type_Double:
			field.setDouble(obj, Double.parseDouble(webVO));
			return;
		case type_Date:
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			field.set(obj, sf.parse(webVO));
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
		return this.field.get(obj);
//		switch (this.type) {
//		case type_String:
//			return field.get(obj);
//		case type_boolean:
//		case type_Boolean:
//			return field.getBoolean(obj);
//		case type_short:
//		case type_Short:
//			return field.getShort(obj);
//		case type_int:
//		case type_Integer:
//			return field.getInt(obj);
//		case type_long:
//		case type_Long:
//			return field.getLong(obj);
//		case type_float:
//		case type_Float:
//			return field.getFloat(obj);
//		case type_double:
//		case type_Double:
//			return field.getDouble(obj);
//		case type_Date:
//			return field.get(obj);
//		default:
//			return field.get(obj);
//		}
	}

}
