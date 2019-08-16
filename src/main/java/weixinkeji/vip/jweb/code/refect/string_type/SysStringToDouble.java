package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToDouble extends _SysStringVoModel {

	public SysStringToDouble() {
		super(double.class);
	}

	/**
	 * String类型转Double类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return 0.0D;
		}
		return Double.parseDouble(vo);
	}

}
