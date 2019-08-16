package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToDouble2 extends _SysStringVoModel {

	public SysStringToDouble2() {
		super(Double.class);
	}

	/**
	 * String类型转Double类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return null;
		}
		return Double.parseDouble(vo);
	}

}
