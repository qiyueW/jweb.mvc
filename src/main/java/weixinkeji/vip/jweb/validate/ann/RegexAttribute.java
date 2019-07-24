package weixinkeji.vip.jweb.validate.ann;

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
@Target({PARAMETER })
public @interface RegexAttribute {

	/**
	 * 属性=是否为null
	 * @return
	 */
	public String[] fieldsAlloyNull();
}
