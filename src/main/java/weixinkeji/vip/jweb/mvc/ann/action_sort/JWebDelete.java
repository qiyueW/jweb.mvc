package weixinkeji.vip.jweb.mvc.ann.action_sort;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface JWebDelete {

	public String value() default "";
	public String url() default "";
}
