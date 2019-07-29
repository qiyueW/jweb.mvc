package weixinkeji.vip.jweb.mvc.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc.convert.MvcDataConver;

public class ShortDefault implements MvcDataConver<Short> {

	@Override
	public Short toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Short.parseShort(webValue);
	}

}
