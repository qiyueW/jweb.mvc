package weixinkeji.vip.jweb;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

final public class ClassFieldsModel {

	private static final Map<Class<?>, FieldModel[]> CFM = new HashMap<>();

	// 取得类的字段模型
	synchronized public static FieldModel[] getFieldModel(Class<?> c) {
		FieldModel[] fms = CFM.get(c);
		if (null == fms) {
			fms = formatClassFields(c);
			CFM.put(c, fms);
		}
		return fms;
	}

	// 字段模型
	private static FieldModel[] formatClassFields(Class<?> c) {
		Field[] fs = c.getDeclaredFields();
		FieldModel[] fm = new FieldModel[fs.length];
		for (int i = 0; i < fs.length; i++) {
			fm[i] = new FieldModel(fs[i]);
		}
		return fm;
	}
}
