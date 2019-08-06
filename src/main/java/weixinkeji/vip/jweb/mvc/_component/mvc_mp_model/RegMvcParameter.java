package weixinkeji.vip.jweb.mvc._component.mvc_mp_model;

import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对外接口。用户只要实现此接口，框架即会自动注册到mvc方法中的参数列里。然后 你可以在方法里传入此参数
 * 
 * @author wangchunzi
 *
 * @param <T> 泛型
 */
public interface RegMvcParameter {

	/**
	 * 此类的实例方法
	 * 
	 * @param parameter Parameter
	 * @param request   HttpServletRequest
	 * @param response  HttpServletResponse
	 */
	void init(Parameter parameter, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 校验
	 * 
	 * @return boolean
	 */
	boolean validate();
}
