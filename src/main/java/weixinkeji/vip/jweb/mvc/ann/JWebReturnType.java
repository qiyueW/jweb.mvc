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
	 * @return
	 */
	public String value() default "";

	/**
	 * 与 value同义词
	 * 
	 * @return
	 */
	public String type() default "";

}
