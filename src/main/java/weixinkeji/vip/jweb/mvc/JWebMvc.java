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
	
	
}
