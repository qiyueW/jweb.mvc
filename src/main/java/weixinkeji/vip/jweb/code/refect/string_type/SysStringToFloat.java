package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToFloat extends _SysStringVoModel {

	public SysStringToFloat() {
		super(float.class);
	}

	/**
	 * String类型转float类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return 0.0F;
		}
		return Float.parseFloat(vo);
	}
	
}
