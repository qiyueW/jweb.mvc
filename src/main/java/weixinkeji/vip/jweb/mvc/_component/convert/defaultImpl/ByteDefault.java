package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class ByteDefault implements MvcStringDataConver<byte[]> {

	@Override
	public byte[] toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		return webValue.getBytes();
	}

}
