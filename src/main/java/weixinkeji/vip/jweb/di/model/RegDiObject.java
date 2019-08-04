package weixinkeji.vip.jweb.di.model;

/**
 * 对外接口
 * 
 * @author wangchunzi
 *
 */
public interface RegDiObject {
	/**
	 * 提供你的对象实例
	 * 
	 * @return
	 */
	Object reg();

	/**
	 * 对象的
	 * @return
	 */
	default NewType regObjectSort() {
		return NewType.singleton;
	}
}
