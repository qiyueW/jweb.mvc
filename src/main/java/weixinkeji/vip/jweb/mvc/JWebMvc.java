package weixinkeji.vip.jweb.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWebMvc {

	public final HttpServletRequest request;
	public final HttpServletResponse response;
	
	public JWebMvc(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 设置 键值对
	 * @param name 属性名 key
	 * @param o		属性值 value
	 */
	public void setAttribute(String name, Object o){
		this.request.setAttribute(name, o);
	}
	
}
