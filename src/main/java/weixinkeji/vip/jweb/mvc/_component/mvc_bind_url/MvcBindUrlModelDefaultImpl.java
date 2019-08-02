package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.JWebController;
import weixinkeji.vip.jweb.mvc.ann.JWebGet;
import weixinkeji.vip.jweb.mvc.ann.JWebPost;
import weixinkeji.vip.jweb.mvc.ann.JWebPut;
import weixinkeji.vip.jweb.mvc.ann.JWebRequest;

public class MvcBindUrlModelDefaultImpl implements MvcBindUrlModel {
	/**
	 * 取得绑定在类上的路径
	 * 
	 * 如果没有注解，直接返回空；<br>
	 * 如果有注解，检查注解里的路径，如是有，优先返回value的，次url(value与url都表示空)；没有，返回空<br>
	 * 
	 * @param c 控制类
	 * @return String 路径
	 */
	@Override
	public String getClassUrl(Class<?> c) {
		JWebController controller = c.getAnnotation(JWebController.class);
		if (null == controller) {// 没有控制类专属注解，直接返回 空
			return "";
		} else {
			return this.getNotEmptyValue("", controller.value(), controller.url());
		}
	}

	/**
	 * 取得绑定在方法上的路径
	 * 
	 * @param m 控制类里的方法
	 * @return String 路径
	 */
	@Override
	public String getMethodUrl(Method m) {

		return m.getName();
	}

	/**
	 * 该方法 绑定 的url ，属于哪个请求方式（比如 post、get、put），把它返回出来<br>
	 * 默认 返回 get(在此方法中处理中)<br>
	 * 
	 * 优先级：JWebRequest>JWebPost>JWebGet>JWebPut(在此方法中处理中)<br>
	 * 
	 * @param m 方法
	 * @return String 请求方式 （比如 post、get、put）
	 */
	@Override
	public String getUrActionSort(Method m) {
		if (null != m.getAnnotation(JWebRequest.class)) {
			return "all";// 表示所有
		}
		if (null != m.getAnnotation(JWebPost.class)) {
			return "post";// 表示所有
		}
		if (null != m.getAnnotation(JWebGet.class)) {
			return "get";// 表示所有
		}
		if (null != m.getAnnotation(JWebPut.class)) {
			return "put";// 表示所有
		}
		// 默认的方式
		return "get";
	}

	/**
	 * 取不为空，不为null的值。<br>
	 * 从第value1参数开始，谁先满足要求，谁先返回。都不满足，直接返回默认值<br>
	 * 
	 * @param defaultValue 默认值
	 * @param value1       参数
	 * @param value2       参数
	 * @return String
	 */
	private String getNotEmptyValue(String defaultValue, String value1, String value2) {
		String rs;
		if (null != value1 && (rs = value1.trim()).length() > 0) {
			return rs;
		}
		if (null != value2 && (rs = value2.trim()).length() > 0) {
			return rs;
		}
		return defaultValue;
	}
}
