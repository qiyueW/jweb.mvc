package weixinkeji.vip.jweb.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final public class ClassFieldsModel {

	private static final Map<Class<?>, FieldModel[]> CFM = new HashMap<>();
	private static final Map<Class<?>, MethodModel[]> CMM = new HashMap<>();
	public final FieldModel[] fieldModels;
	public final MethodModel[] methodsModels;

	public ClassFieldsModel(Class<?> c) {
		this.fieldModels = getFieldModel(c);
		this.methodsModels = CMM.get(c);
	}

	/**
	 * 取得指定属性模型
	 * 
	 * @param fieldName String 属性名称
	 * @return FieldModel 属性模型
	 */
	public FieldModel getFieldModel(String fieldName) {
		for (FieldModel fm : fieldModels) {
			if (fm.fieldName.equals(fieldName)) {
				return fm;
			}
		}
		return null;
	}

	/**
	 * 取得指定属性模型
	 * 
	 * @param field Field 属性
	 * @return FieldModel 属性模型
	 */
	public FieldModel getFieldModel(Field field) {
		for (FieldModel fm : fieldModels) {
			if (fm.field == field) {
				return fm;
			}
		}
		return null;
	}

	
//------------------------------- 静态区  取得指定的模型 --------------------------------
	/**
	 * 取得指定类的属性模型
	 * 
	 * @param c 类
	 * @return FieldModel[] 类的属性模型
	 */
	synchronized public static FieldModel[] getFieldModel(Class<?> c) {
		FieldModel[] fms = CFM.get(c);
		if (null == fms) {
			fms = loadClassFieldModels(c);
			CFM.put(c, fms);
		}
		return fms;
	}
	
	/**
	 * 取得指定类的方法模型
	 * @param c 指定类
	 * @return MethodModel[] 类的方法模型
	 */
	synchronized public static MethodModel[] getMethodModel(Class<?> c) {
		MethodModel[] fms = CMM.get(c);
		if (null == fms) {
			fms = loadMethodModels(c);
			CMM.put(c, fms);
		}
		return fms;
	}
//---------------------------------------------------------------------	
	// 初始化-加载-字段模型 不考虑父类的字段
	private static FieldModel[] loadClassFieldModels(Class<?> c) {
		Field[] fs = c.getDeclaredFields();
		FieldModel[] model = new FieldModel[fs.length];
		for (int i = 0; i < fs.length; i++) {
			model[i] = new FieldModel(fs[i]);
		}
		return model;
	}

	// 初始化-加载-方法模型 不考虑父类的方法
	private static MethodModel[] loadMethodModels(Class<?> c) {
		Method[] fs = c.getDeclaredMethods();
		MethodModel[] model = new MethodModel[fs.length];
		for (int i = 0; i < fs.length; i++) {
			model[i] = new MethodModel(fs[i]);
		}
		return model;
	}

}
