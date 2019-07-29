package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class ShortDefault implements MvcStringDataConver<Short> {

	@Override
	public Short toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Short.parseShort(webValue);
	}

}
