package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcDataConver;

public class DoubleDefault implements MvcDataConver<Double> {

	@Override
	public Double toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Double.parseDouble(webValue);
	}

}
