package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToBytes extends _SysStringVoModel {

	public SysStringToBytes() {
		super(byte[].class);
	}

	/**
	 * String类型转String类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo) {
			return null;
		}
		return vo.getBytes();
	}

}
