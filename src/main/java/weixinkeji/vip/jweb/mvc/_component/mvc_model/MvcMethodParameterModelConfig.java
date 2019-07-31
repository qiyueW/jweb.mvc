package weixinkeji.vip.jweb.mvc._component.mvc_model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MvcMethodParameterModelConfig<T> {

	/**
	 * 此类的实例方法
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return T
	 */
	T getObject(HttpServletRequest request, HttpServletResponse response);

}
