package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class DoubleDefault implements MvcStringDataConver<Double> {

	@Override
	public Double toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return Double.parseDouble(webValue);
	}

}
