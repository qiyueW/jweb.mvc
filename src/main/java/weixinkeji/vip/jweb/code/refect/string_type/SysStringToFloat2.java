package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToFloat2 extends _SysStringVoModel {
	
	public SysStringToFloat2() {
		super(Float.class);
	}
	
	/**
	 * String类型转Float类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return null;
		}
		return Float.parseFloat(vo);
	}
	
}
