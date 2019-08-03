package weixinkeji.vip.jweb.mvc._component.mvc_bind_url;

public class MvcBindUrlModel {

//	资源——处理模型
	private static MvcBindUrl urlModel;

	// 初始化开关
	private static boolean init = false;

	/**
	 * 初始化入口
	 * 
	 * @param c
	 */
	synchronized public static void init(MvcBindUrl obj) {
		if (init) {
			return;
		}
		init = true;
		urlModel = obj;
	}

	/**
	 * 取得处理 绑定在类或方法上的路径、及其他路径请求有关的信息
	 * 
	 * @return MvcBindUrl
	 */
	public static MvcBindUrl getMvcBindUrl() {
		return urlModel;
	}
}
