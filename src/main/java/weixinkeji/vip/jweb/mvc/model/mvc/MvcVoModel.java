package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringConvertModel;
import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;
import weixinkeji.vip.jweb.mvc.config.JWebConfigModel;
import weixinkeji.vip.jweb.mvc.tools.MvcTools;

/**
 * vo用于mvc的拓展模型
 * 
 * @author wangchunzi
 *
 */
public final class MvcVoModel {

	// voModel集合
	private static final Map<Class<?>, MvcVoModel[]> voModel = new HashMap<>();

	/**
	 * 实例一个对象
	 * 
	 * @param c 类
	 * @return Object
	 */
	public static Object getObject(Class<?> c) {
		try {
			return c.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得取MvcVo模型
	 * 
	 * @param c vo类
	 * @return MvcVoModel[]
	 */
	synchronized public static MvcVoModel[] getMvcVoModel(Class<?> c) {
		MvcVoModel[] model = voModel.get(c);
		if (null == model) {
			model = getModels(c);
			voModel.put(c, model);
		}
		return voModel.get(c);
	}

	// 生产 MvcVoModel[]
	private static MvcVoModel[] getModels(Class<?> c) {
		JWebConfigModel config = JWebConfigModel.getJWebConfigModel();
		Field[] fs = c.getDeclaredFields();
		MvcVoModel[] models = new MvcVoModel[fs.length];
		String requestKey;
		for (int i = 0; i < fs.length; i++) {
			fs[i].setAccessible(true);
			requestKey = config.webValue_autoTransform_vo ? MvcTools.strTransform_(fs[i].getName()) : fs[i].getName();
			try {
				models[i] = new MvcVoModel(fs[i], requestKey, "");
			} catch (Exception e) {
				e.printStackTrace();
			} //
		}
		return models;
	}

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
	private final MvcStringDataConver<?> model;

	private MvcVoModel(Field f, String requestKey, String dateFormat) throws Exception {
		this.f = f;
		this.requestKey = requestKey;
		this.dateFormat = dateFormat;
		model = MvcStringConvertModel.getMvcDataConver(f.getType());
		if (null == model) {
			throw new Exception("未知类型,请注册 String类型对" + f.getType() + "类型的转换处理");
		}
	}

	/**
	 * 设置值
	 * 
	 * @param vo    对象
	 * @param value 属性值
	 * @throws Exception 异常信息
	 */
	public void setValue(Object vo, Object value) throws Exception {
		f.set(vo, model.toT(value.toString()));
	}

}
