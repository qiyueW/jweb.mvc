package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class LongDefault implements MvcStringDataConver<Long> {

	@Override
	public Long toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Long.parseLong(webValue);
	}

}
