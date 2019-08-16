package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToLong2 extends _SysStringVoModel {
	
	public SysStringToLong2() {
		super(Long.class);
	}
	
	/**
	 * String类型转Long类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return null;
		}
		return Long.parseLong(vo);
	}
	
}
