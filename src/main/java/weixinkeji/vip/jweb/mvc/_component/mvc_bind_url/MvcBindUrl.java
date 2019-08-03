package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.UrlActionSort;

/**
 * 绑定路径模型
 * 
 * @author wangchunzi
 *
 */
public interface MvcBindUrl {

	/**
	 * 取得绑定在类上的路径
	 * 
	 * 如果没有注解，直接返回空；<br>
	 * 如果有注解，检查注解里的路径，如是有，优先返回value的，次url(value与url都表示空)；没有，返回空<br>
	 * 
	 * @param c 控制类
	 * @return String 路径
	 */
	String getClassUrl(Class<?> c);

	/**
	 * 取得绑定在方法上的路径
	 * 
	 * @param m 控制类里的方法
	 * @return String 路径
	 */
	String getMethodUrl(Method m);

	/**
	 * 取得该方法绑定的url,属于哪个请求方式（比如 post、get、put）<br>
	 * 默认 返回 get(在此方法中处理中)<br>
	 * 
	 * 优先级：JWebRequest>JWebPost>JWebGet>JWebPut(在此方法中处理中)<br>
	 * 
	 * @param m 方法
	 * @return UrlActionSort 请求方式 （比如 post、get、put）
	 */
	UrlActionSort getUrActionSort(Method m);

}
