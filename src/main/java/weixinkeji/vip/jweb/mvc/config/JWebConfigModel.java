package weixinkeji.vip.jweb.mvc.config;

public class JWebConfigModel {

	/**
	 * 取web客户端传参时，自动把驼峰转成_,比如，Aa =>a_a
	 */
	public final boolean webValue_autoTranfa;

	/**
	 * 使用jweb.validate权限框架
	 */
	public final boolean jweb_validate;

	private JWebConfigModel(ConfigVoTemp vo) {
		this.webValue_autoTranfa = vo.webValue_autoTranfa;
		this.jweb_validate = vo.jweb_validate;
	}

	private static JWebConfigModel config;
	private static boolean hasIni = false;

	/**
	 * 得取配置
	 * 
	 * @return
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
