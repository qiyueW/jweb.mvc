package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.JWebController;
import weixinkeji.vip.jweb.mvc.ann.UrlActionSort;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebDelete;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebGet;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebHead;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebOptions;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebPost;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebPut;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebRequest;
import weixinkeji.vip.jweb.mvc.ann.action_sort.JWebTrace;

public class MvcBindUrlDefaultImpl implements MvcBindUrl {

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
	 * JWebRequest(1)>JWebPost(2)>JWebGet(3)>JWebPut(4)>JWebDelete(5)>JWebHead(6)>JWebOptions(7)>JWebTrace(8)
	 * 
	 * @param m 控制类里的方法
	 * @return String 路径
	 */
	@Override
	public String getMethodUrl(Method m) {
		String rs;
//JWebRequest(1)		
		JWebRequest ann1 = m.getAnnotation(JWebRequest.class);
		if (null != ann1) {
			if (null != (rs = this.getNotEmptyValue(null, ann1.value(), ann1.url()))) {
				return rs;
			}
			return m.getName();
		}
//JWebPost(2)
		JWebPost ann2 = m.getAnnotation(JWebPost.class);
		if (null != ann2) {
			if (null != (rs = this.getNotEmptyValue(null, ann2.value(), ann2.url()))) {
				return rs;
			}
			return m.getName();
		}
//JWebGet(3)
		JWebGet ann3 = m.getAnnotation(JWebGet.class);
		if (null != ann3) {
			if (null != (rs = this.getNotEmptyValue(null, ann3.value(), ann3.url()))) {
				return rs;
			}
			return m.getName();
		}
//JWebPut(4)		
		JWebPut ann4 = m.getAnnotation(JWebPut.class);
		if (null != ann4) {
			if (null != (rs = this.getNotEmptyValue(null, ann4.value(), ann4.url()))) {
				return rs;
			}
			return m.getName();
		}
//JWebDelete(5)
		JWebDelete ann5 = m.getAnnotation(JWebDelete.class);
		if (null != ann5) {
			if (null != (rs = this.getNotEmptyValue(null, ann5.value(), ann5.url()))) {
				return rs;
			}
			return m.getName();
		}
// JWebHead(6)
		JWebHead ann6 = m.getAnnotation(JWebHead.class);
		if (null != ann6) {
			if (null != (rs = this.getNotEmptyValue(null, ann6.value(), ann6.url()))) {
				return rs;
			}
			return m.getName();
		}
// JWebOptions(7)
		JWebOptions ann7 = m.getAnnotation(JWebOptions.class);
		if (null != ann7) {
			if (null != (rs = this.getNotEmptyValue(null, ann7.value(), ann7.url()))) {
				return rs;
			}
			return m.getName();
		}
// JWebTrace(8)
		JWebTrace ann8 = m.getAnnotation(JWebTrace.class);
		if (null != ann8) {
			if (null != (rs = this.getNotEmptyValue(null, ann8.value(), ann8.url()))) {
				return rs;
			}
			return m.getName();
		}
//默认
		return m.getName();
	}

	/**
	 * 取得该方法绑定的url,属于哪个请求方式（比如 post、get、put）<br>
	 * 默认 返回 all(在此方法中处理中)<br>
	 * 
	 * 优先级：JWebRequest(1)>JWebPost(2)>JWebGet(3)>JWebPut(4)>JWebDelete(5)>JWebHead(6)>JWebOptions(7)>JWebTrace(8)
	 * (在此方法中处理中)<br>
	 * 
	 * @param m 方法
	 * @return String 请求方式 （比如 post、get、put）
	 */
	@Override
	public UrlActionSort getUrActionSort(Method m) {
		if (null != m.getAnnotation(JWebRequest.class)) {
			return UrlActionSort.ALL;// JWebRequest(1)
		}
		if (null != m.getAnnotation(JWebPost.class)) {
			return UrlActionSort.POST;// JWebPost(2)
		}
		if (null != m.getAnnotation(JWebGet.class)) {
			return UrlActionSort.GET;// JWebGet(3)
		}
		if (null != m.getAnnotation(JWebPut.class)) {
			return UrlActionSort.PUT;// JWebPut(4)
		}
		if (null != m.getAnnotation(JWebDelete.class)) {
			return UrlActionSort.DELETE;// JWebDelete(5)
		}
		if (null != m.getAnnotation(JWebHead.class)) {
			return UrlActionSort.HEAD;// JWebHead(6)
		}
		if (null != m.getAnnotation(JWebOptions.class)) {
			return UrlActionSort.OPTIONS;// JWebOptions(7)
		}
		if (null != m.getAnnotation(JWebTrace.class)) {
			return UrlActionSort.TRACE;// JWebTrace(8)
		}
		// 默认的方式
		return UrlActionSort.ALL;
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
