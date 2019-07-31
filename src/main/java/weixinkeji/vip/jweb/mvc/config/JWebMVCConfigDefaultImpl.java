package weixinkeji.vip.jweb.mvc.config;

/**
 * 默认实现类——当找不到用户配置时起作用
 * 
 * @author wangchunzi
 *
 */
public class JWebMVCConfigDefaultImpl implements JWebMVCConfig {
	/**
	 * 配置入口
	 * 
	 * @param conf ConfigVoTemp 配置信息载体
	 */
	@Override
	public void setJWebMvcConfig(ConfigVoTemp conf) {
		conf.jweb_validate = true;
	}

}
