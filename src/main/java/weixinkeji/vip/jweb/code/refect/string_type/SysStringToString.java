package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToString extends _SysStringVoModel {

	public SysStringToString() {
		super(String.class);
	}
	
	/**
	 * String类型转String类型
	 */
	@Override
	Object toYourType(String vo) {
		return vo;
	}

}
