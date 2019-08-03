package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.JWebController;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebGet;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebPost;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebPut;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebRequest;

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
	 * 取得绑定在方法上的路径 JWebRequest 1>JWebPost 2>JWebGet 3>JWebPut 4(在此方法中处理中)
	 * 
	 * @param m 控制类里的方法
	 * @return String 路径
	 */
	@Override
	public String getMethodUrl(Method m) {
		String rs;
		JWebRequest ann1 = m.getAnnotation(JWebRequest.class);
//1 JWebRequest注解不为null时		
		if (null != ann1) {
			if (null != (rs = this.getNotEmptyValue(null, ann1.value(), ann1.url()))) {
				return rs;
			}
			return m.getName();
		}

		JWebPost ann2 = m.getAnnotation(JWebPost.class);
//2 JWebPost注解不为null时
		if (null != ann2) {
			if (null != (rs = this.getNotEmptyValue(null, ann2.value(), ann2.url()))) {
				return rs;
			}
			return m.getName();
		}

		JWebGet ann3 = m.getAnnotation(JWebGet.class);
//3 JWebGet注解不为null时
		if (null != ann3) {
			if (null != (rs = this.getNotEmptyValue(null, ann3.value(), ann3.url()))) {
				return rs;
			}
			return m.getName();
		}
		JWebPut ann4 = m.getAnnotation(JWebPut.class);
//4 JWebPut注解不为null时
		if (null != ann4) {
			if (null != (rs = this.getNotEmptyValue(null, ann4.value(), ann4.url()))) {
				return rs;
			}
			return m.getName();
		}
		return m.getName();
	}

	/**
	 * 取得该方法绑定的url,属于哪个请求方式（比如 post、get、put）<br>
	 * 默认 返回 all(在此方法中处理中)<br>
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
		return "all";
	}

	/**
	 * 取不为空，不为null的值。<br>
	 * 从value 第1个元素开始，谁先满足要求，谁先返回。都不满足，直接返回默认值<br>
	 * 
	 * @param defaultValue 默认值
	 * @param value        参数列表
	 * @return String 符合的参数或默认值
	 */
	protected String getNotEmptyValue(String defaultValue, String... value) {
		if (null == value || value.length == 0) {
			return defaultValue;
		}
		String rs;
		for (String value1 : value) {
			if (null != value1 && (rs = value1.trim()).length() > 0) {
				return rs;
			}
		}
		return defaultValue;
	}

}
