package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcDataConver;

public class LongDefault implements MvcDataConver<Long> {

	@Override
	public Long toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Long.parseLong(webValue);
	}

}
