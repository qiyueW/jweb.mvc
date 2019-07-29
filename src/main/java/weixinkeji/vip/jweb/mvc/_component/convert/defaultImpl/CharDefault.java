package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class CharDefault implements MvcStringDataConver<char[]> {

	@Override
	public char[] toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return webValue.toCharArray();
	}

}
