package weixinkeji.vip.jweb.mvc.model.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MvcMethodParameterModelConfig<T> {

	/**
	 * 此类的实例方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	T getObject(HttpServletRequest request, HttpServletResponse response);
}
