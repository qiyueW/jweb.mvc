package weixinkeji.vip.jweb.code.di.ann;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import weixinkeji.vip.jweb.code.di.model.NewType;

/**
 * 用在类上，表示此类是多例还是单例。默认是多例
 * 
 * @author wangchunzi
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface JWebDINewType {

	/**
	 * 默认是原型模型
	 * 
	 * @return NewType
	 */
	public NewType value() default NewType.prototype;
}
