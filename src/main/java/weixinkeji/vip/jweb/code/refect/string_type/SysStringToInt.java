package weixinkeji.vip.jweb.code.refect.string_type;

public class SysStringToInt extends _SysStringVoModel {
	
	public SysStringToInt() {
		super(int.class);
	}
	
	/**
	 * String类型转int类型
	 */
	@Override
	Object toYourType(String vo) {
		if (null == vo || vo.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(vo);
	}
	
}
