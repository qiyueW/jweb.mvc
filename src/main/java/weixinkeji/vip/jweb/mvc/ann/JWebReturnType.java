package weixinkeji.vip.jweb.mvc.ann;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface JWebReturnType {

	/**
	 * value="c" 表示控制类
	 * 
	 * @return String
	 */
	public String value() default "";

	/**
	 * 与 value同义词
	 * 
	 * @return String
	 */
	public String type() default "";

}
