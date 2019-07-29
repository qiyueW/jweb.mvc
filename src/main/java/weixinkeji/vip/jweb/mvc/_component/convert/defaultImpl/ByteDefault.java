package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcDataConver;

public class ByteDefault implements MvcDataConver<byte[]> {

	@Override
	public byte[] toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return webValue.getBytes();
	}

}
