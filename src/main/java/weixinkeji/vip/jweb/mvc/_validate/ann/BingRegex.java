package weixinkeji.vip.jweb.mvc._validate.ann;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 绑定 正则表达式
 * 
 * 给属性（普通类型的属性）或参数（普通类型的参数） 绑定 正则表达式
 * @author wangchunzi
 *
 */
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
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
	 * 占位符
	 * 
	 * @return String[]
	 */
	public String[] placeholder() default "";

	/**
	 * 是否允许null（默认false,不允许）
	 * 
	 * @return boolean
	 */
	public boolean alloyNull() default false;

}
