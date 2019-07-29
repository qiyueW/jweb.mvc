package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcDataConver;

public class FloatDefault implements MvcDataConver<Float> {

	@Override
	public Float toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Float.parseFloat(webValue);
	}

}
