package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToLong extends _SysStringVoModel {
	
	public SysStringToLong() {
		super(long.class);
	}
	
	/**
	 * String类型转Long类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return 0;
		}
		return Long.parseLong(vo);
	}
	
}
