package weixinkeji.vip.jweb.mvc.model.mvc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.mvc.config.JWebConfigModel;
import weixinkeji.vip.jweb.mvc.tools.MvcTools;

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
		Field[] fs = c.getDeclaredFields();
		MvcVoModel[] models = new MvcVoModel[fs.length];
		JWebConfigModel config = JWebConfigModel.getJWebConfigModel();
		String requestKey;
		for (int i = 0; i < fs.length; i++) {
			requestKey = config.webValue_autoTransform_vo ? MvcTools.strTransform_(fs[i].getName()) : fs[i].getName();
			models[i] = new MvcVoModel(fs[i], requestKey, "");//
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

	private MvcVoModel(Field f, String requestKey, String dateFormat) {
		this.f = f;
		this.requestKey = requestKey;
		this.dateFormat = dateFormat;
	}

}
