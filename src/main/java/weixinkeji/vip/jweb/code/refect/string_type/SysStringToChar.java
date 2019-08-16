package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToChar extends _SysStringVoModel {

	public SysStringToChar() {
		super(char.class);
	}

	/**
	 * String类型转char类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo) {
			return null;
		}
		return vo.toCharArray()[0];
	}

}
