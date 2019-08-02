package weixinkeji.vip.jweb.mvc.config;

/**
 * 配置载体-最终
 * 
 * @author wangchunzi
 *
 */
public final class JWebConfigModel {

	/**
	 *vo对象属性专用： 取web客户端传参,是vo对象时，对其内的字段取参名字，自动把驼峰转成下划线,比如，Aa 转成  a_a 默认 true
	 */
	public final boolean webValue_autoTransform_vo;

	/**
	 * 取web客户端传参时，自动把驼峰转成下划线,比如，Aa 转成  a_a 默认false
	 */
	public final boolean webValue_autoTransform;

	/**
	 * 使用jweb.validate权限框架
	 */
	public final boolean jweb_validate;
	
	/**
	 * 检验失败后，是否立马中止检验。<br>
	 * 推荐是。
	 */
	public final boolean errorReturn;
	
	private JWebConfigModel(ConfigVoTemp vo) {
		this.webValue_autoTransform = vo.webValue_autoTransform;
		this.webValue_autoTransform_vo = vo.webValue_autoTransform_vo;
		this.jweb_validate = vo.jweb_validate;
		this.errorReturn=vo.errorReturn;
	}

	private static JWebConfigModel config;
	private static boolean hasIni = false;

	/**
	 * 得取配置
	 * 
	 * @return JWebConfigModel
	 */
	public static JWebConfigModel getJWebConfigModel() {
		return config;
	}

	/**
	 * 初始化。只一次。第二次开始，无法初始化（开关已关）
	 * 
	 * @param vo ConfigVoTemp
	 */
	synchronized public static void init(ConfigVoTemp vo) {
		if (hasIni) {
			return;
		}
		hasIni = true;
		config = new JWebConfigModel(vo);
	}

}
