package weixinkeji.vip.jweb.mvc.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc.convert.MvcDataConver;

public class IntegerDefault implements MvcDataConver<Integer> {

	@Override
	public Integer toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Integer.parseInt(webValue);
	}

}
