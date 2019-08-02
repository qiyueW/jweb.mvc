package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.JWebController;
import weixinkeji.vip.jweb.mvc.ann.JWebRequest;

/**
 * 绑定路径模型
 * 
 * @author wangchunzi
 *
 */
public interface MvcBindUrlModel {

	/**
	 * 取得绑定在类上的路径
	 * 
	 * 如果没有注解，直接返回空；<br>
	 * 如果有注解，检查注解里的路径，如是有，优先返回value的，次url(value与url都表示空)；没有，返回空<br>
	 * 
	 * @param c 控制类
	 * @return String 路径
	 */
	default String getClassUrl(Class<?> c) {
		JWebController controller = c.getAnnotation(JWebController.class);
		if (null == controller) {// 没有控制类专属注解，直接返回 空
			return "";
		} else {
			// 先检查 注解的value值
			if (null == controller.value() || controller.value().trim().isEmpty()) {// 当value值为null或空时
				// 返回url的。因为 value与url表示同个意思。
				// url也为null或空时，直接返回空
				return null == controller.url() || controller.url().trim().isEmpty() ? "" : controller.url().trim();
			} else {
				// 返回value的值
				return controller.value().trim();
			}
		}
	}

	/**
	 * 取得绑定在方法上的路径
	 * 
	 * @param m 控制类里的方法
	 * @return String 路径
	 */
	default String getMethodUrl(Method m) {

		return m.getName();
	}

	/**
	 * 取得该方法 绑定 的url ，属于哪个请求方式（比如 post、get、put）<br>
	 * 默认 返回 get(在此方法中处理中)<br>
	 * 
	 * 优先级：JWebRequest>JWebPost>JWebGet>JWebPut(在此方法中处理中)<br>
	 * 
	 * @param m 方法
	 * @return String 请求方式 （比如 post、get、put）
	 */
	default String getUrActionSort(Method m) {
		JWebRequest request=m.getAnnotation(JWebRequest.class);
		if(null!=request) {
			 
		}
		return "get";
	}
	
	
}
