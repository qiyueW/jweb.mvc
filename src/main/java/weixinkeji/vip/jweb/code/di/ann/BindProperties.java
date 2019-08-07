package weixinkeji.vip.jweb.code.di.ann;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface BindProperties {

	/**
	 * 绑定的路径
	 * 
	 * @return 按字段自动匹配
	 */
	public String[] path();
	
	/**
	 * 是否完全匹配<br>
	 * 默认false.false时，则自动寻找驼峰转_方法 aAa 会变成 a_aa 也会匹配<br>
	 * true时，全字匹配
	 * 
	 * @return boolean布尔值
	 */
	public boolean eq() default false;
}
