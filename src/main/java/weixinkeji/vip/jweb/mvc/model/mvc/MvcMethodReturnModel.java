package weixinkeji.vip.jweb.mvc.model.mvc;

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
	public Class<?> returnType;

	/**
	 * -1：无返回（当 returnType=void.class时）<br>
	 * 0：控制类 通过判断方法上的注解 @JWebReturnType("C")小写或大写都行<br>
	 * 1：jsp视图(默认）通过判断方法上的注解 @JWebReturnType("jsp")小写或大写都行,没有注解，默认jsp<br>
	 */
	public int returnUrlType;

	// 框架定义，只返回的是String类型或void类型
	// return "xxx";表示跳转 return ":xxx";表示重定向
	public void say() {

	}
	public MvcMethodReturnModel(Method m) {
		this.returnType=m.getReturnType();
		this.returnUrlType=getMethodReturnType(m);
	}
	
	/**
	 * -1：无返回（当 returnType=void.class时）<br>
	 * 0：控制类 通过判断方法上的注解 @JWebReturnType("C")小写或大写都行<br>
	 * 1：jsp视图(默认）通过判断方法上的注解 @JWebReturnType("jsp")小写或大写都行,没有注解，默认jsp<br>
	 */
	private static int getMethodReturnType(Method m) {
		JWebReturnType jt = m.getAnnotation(JWebReturnType.class);
		if (null == jt
				||(null!=jt.value()&&jt.value().trim().equalsIgnoreCase("jsp"))||(jt.type().trim().equalsIgnoreCase("jsp"))
			) {
			return 1;// jsp视图
		}else if((null!=jt.value()&&jt.value().trim().equalsIgnoreCase("c"))||(jt.type().trim().equalsIgnoreCase("c"))) {
			return 0;// 控制区的路径
		}
		return 1;//其他后续视图的支持与整合（暂时不写上）
	}
	
//	
//	
//	
//	public static void main(String args[]) {
//		for (Method m : MvcMethodReturnModel.class.getDeclaredMethods()) {
//			System.out.println(m.getReturnType());
//		}
//	}

}
