package weixinkeji.vip.jweb.mvc.ann;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface BingRegex {

	/**
	 * 正则表达式
	 * 
	 * @return String
	 */
	public String value() default "";// 与regex是等价关系

	/**
	 * 正则表达式
	 * 
	 * @return String
	 */
	public String regex() default "";// 等价上述

	/**
	 * 校验不通过时的错误信息
	 * 
	 * @return String
	 */
	public String error() default "";

	/**
	 * 是否允许null（默认false,不允许）
	 * 
	 * @return boolean
	 */
	public boolean alloyNull() default false;

}
