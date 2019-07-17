package weixinkeji.vip.jweb.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import weixinkeji.vip.jweb.mvc.bean.Hello;

public class HelloController {
	
	

	public static void main(String args[]) throws Exception {
		Method[] ms=Hello.class.getDeclaredMethods();
		Hello obj=new Hello();
		
		for(Method m:ms) {
			System.out.println("方法名："+m.getName()+"//"+m.getModifiers()+"//"+m.getParameterCount());
//			m.invoke(obj, null);
			for(Parameter p:m.getParameters()) {
				System.out.println(p.getModifiers()+"//"+p.getName()+"//"+p.getClass()+p.getType());
			}
		}
	}
}
