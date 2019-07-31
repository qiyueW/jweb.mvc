package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.Method;

import weixinkeji.vip.jweb.mvc.ann.JWebReturnType;

/**
 * 返回模型，主要用途有：<br>
 * 跳转|重定向 视图|其他控制类<br>
 * 
 * @author wangchunzi
 *
 */
public class MvcMethodReturnModel {
	/**
	 * 方法返回 类型
	 */
	public final Class<?> returnType;

	/**
	 * -1：无返回（当 returnType=void.class时）<br>
	 * 0：控制类 通过判断方法上的注解 @JWebReturnType("C")小写或大写都行<br>
	 * 1：jsp视图(默认）通过判断方法上的注解 @JWebReturnType("jsp")小写或大写都行,没有注解，默认jsp<br>
	 */
	public final int returnUrlType;
	// 是否有返回类型。通过 this.isNotReturn = this.returnType == void.class;
	// 取得
	private final boolean isNotReturn;

	public MvcMethodReturnModel(Method m) {
		this.returnType = m.getReturnType();
		this.returnUrlType = getMethodReturnType(m);
		this.isNotReturn = this.returnType == void.class;
	}

	// 框架定义，只返回的是String类型或void类型
	// return "xxx";表示跳转 return ":xxx";表示重定向
	/**
	 * 得取符合http要求的返回路径。当为null时，表示没有返回值，或返回了错误的空值
	 * 
	 * @param url 框架封装的路径
	 * @return String
	 */
	public String getUrl(String url) {
		if (this.isNotReturn || url.length() == 0) {
			return null;
		}
		char[] urlc = url.toCharArray();
		if (urlc[0] != ' ') {// 如果非空开头，直接判断是否以:开头，是的话，进行切除
			return urlc[0] == ':' ? String.copyValueOf(urlc, 1, urlc.length - 1) : url;
		}
		int i = 0;
		while (urlc[i] == ' ') {
			i++;
		}
		return urlc[i] == ':' ? String.copyValueOf(urlc, i + 1, urlc.length - i - 1) : url;
	}

	/**
	 * -1：无返回（当 returnType=void.class时）<br>
	 * 0：控制类 通过判断方法上的注解 @JWebReturnType("C")小写或大写都行<br>
	 * 1：jsp视图(默认）通过判断方法上的注解 @JWebReturnType("jsp")小写或大写都行,没有注解，默认jsp<br>
	 */
	private static int getMethodReturnType(Method m) {
		JWebReturnType jt = m.getAnnotation(JWebReturnType.class);
		if (null == jt || (null != jt.value() && jt.value().trim().equalsIgnoreCase("jsp"))
				|| (jt.type().trim().equalsIgnoreCase("jsp"))) {
			return 1;// jsp视图
		} else if ((null != jt.value() && jt.value().trim().equalsIgnoreCase("c"))
				|| (jt.type().trim().equalsIgnoreCase("c"))) {
			return 0;// 控制区的路径
		}
		return 1;// 其他后续视图的支持与整合（暂时不写上）
	}

}
