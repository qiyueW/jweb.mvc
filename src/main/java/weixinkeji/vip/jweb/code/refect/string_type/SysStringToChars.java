package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToChars extends _SysStringVoModel {

	public SysStringToChars() {
		super(char[].class);
	}
	
	/**
	 * String类型转char[]类型
	 */
	@Override
	Object toYourType(String vo) {
		return vo.toCharArray();
	}
	
}
