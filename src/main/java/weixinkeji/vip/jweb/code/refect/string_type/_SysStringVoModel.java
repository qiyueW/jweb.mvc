package weixinkeji.vip.jweb.code.refect.string_type;

/**
 * String类型转其他类型的模型
 * 
 * @author wangchunzi
 *
 */
public abstract class _SysStringVoModel {
	/**
	 * 转换成的类型
	 */
	public final Class<?> targetClass;
	
	/**
	 * 值转换
	 * 
	 * @param vo String类型的值
	 * @return Object
	 */
	abstract Object toYourType(String vo);
	
	/**
	 * 构造函数
	 * 
	 * @param sourcesClaass Class
	 * @param targetClass   Class
	 */
	public _SysStringVoModel(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
}
