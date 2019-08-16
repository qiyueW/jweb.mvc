package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToInt2 extends _SysStringVoModel {
	
	public SysStringToInt2() {
		super(Integer.class);
	}
	
	/**
	 * String类型转Integer类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return null;
		}
		return Integer.parseInt(vo);
	}
	
}
