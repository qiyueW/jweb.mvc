package weixinkeji.vip.jweb.mvc.config;

/**
 * 用户配置载体
 * 
 * @author wangchunzi
 *
 */
public class ConfigVoTemp {

	/**
	 *vo对象属性专用： 取web客户端传参,是vo对象时，对其内的字段取参名字，自动把驼峰转成下划线连接,比如，Aa   转成  a_a 默认 true
	 */
	public boolean webValue_autoTransform_vo = true;

	/**
	 * 取web客户端传参时，自动把驼峰转成下划线连接,比如，Aa  转成 a_a 默认false
	 */
	public boolean webValue_autoTransform = false;

	/**
	 * 使用jweb.validate权限框架
	 */
	public boolean jweb_validate = true;
	/**
	 * 检验失败后，是否立马中止检验。<br>
	 * 推荐是(true)。
	 */
	public boolean errorReturn=true;
}
