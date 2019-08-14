package weixinkeji.vip.jweb.code.refect.string_type;

public abstract class _SysStringVoModel {
	/**
	 * 转换成的类型
	 */
	public final Class<?> targetClass;

	/**
	 * 值转换
	 * 
	 * @param vo
	 * @return
	 */
	abstract Object toYourType(String vo);

	/**
	 * 构造函数
	 * 
	 * @param sourcesClaass Class
	 * @param targetClass   Class
	 */
	public _SysStringVoModel(Class<?> sourcesClaass, Class<?> targetClass) {
		this.targetClass = targetClass;
	}
}
