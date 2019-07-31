package weixinkeji.vip.jweb.mvc.config;

public class JWebMVCConfigDefaultImpl implements JWebMVCConfig {

	@Override
	public void setJWebMvcConfig(ConfigVoTemp conf) {
		conf.jweb_validate = true;
	}

}
