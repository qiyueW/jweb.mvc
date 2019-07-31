package weixinkeji.vip.jweb.mvc.config;

/**
 * 初始化
 * 
 * @author wangchunzi
 *
 */
public class _InitConfigJWebMvcConfig {

	/**
	 * 初始化入口
	 * 
	 * @param vo ConfigVoTemp 用户配置载体
	 */
	public void init(ConfigVoTemp vo) {
		JWebConfigModel.init(vo);
	}
}
