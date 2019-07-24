package weixinkeji.vip.jweb.mvc;

import weixinkeji.vip.jweb.mvc.ann.request.JWebGet;
import weixinkeji.vip.jweb.mvc._validate.ann.BindRegex;
import weixinkeji.vip.jweb.mvc._validate.ann.RegexAttribute;
import weixinkeji.vip.jweb.mvc.ann.request.JWebController;
import weixinkeji.vip.jweb.mvc.ann.request.ParamKey;
import weixinkeji.vip.jweb.mvc.bean.Hello;

@JWebController("/test")
public class _DoControllerMethod {

	@JWebGet
	public String sayHello(JWebMvc jw,
			// 表示对Hello属性id，改写校验属性，alloyNull,让其允许为null
			// 同时，表达参数时，key=hello,如果不指定接收参数的key,默认key=变量名obj
			@RegexAttribute(fieldsAlloyNull = { "id2=true" }) @ParamKey("hello") Hello obj,
			@BindRegex(regex = "[0-9]+") String wo) {
		jw.setAttribute("key", "value");

		return null;
	}

	@JWebGet
	public String sayHello2(JWebMvc jw,
			// 表示对Hello属性id，改写校验属性，alloyNull,让其允许为null
			// 同时，表达参数时，key=hello,如果不指定接收参数的key,默认key=变量名obj
			@RegexAttribute(fieldsAlloyNull = { "id=true" }) @ParamKey("hello") Hello obj,
			@BindRegex(regex = "[0-9]+") String wo) {
		jw.setAttribute("key", "value");

		return null;
	}
}
