package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class BooleanDefault implements MvcStringDataConver<Boolean> {

	@Override
	public Boolean toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Boolean.parseBoolean(webValue);
	}

}
