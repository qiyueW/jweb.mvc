package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcDataConver;

public class CharDefault implements MvcDataConver<char[]> {

	@Override
	public char[] toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return webValue.toCharArray();
	}

}
