package weixinkeji.vip.jweb.mvc.model;

public class MethodModel {
	
	public enum returnUrlType {
		controller(0), view(1);
		public final int code;
		private returnUrlType(int code) {
			this.code = code;
		}
	}
//
//	/**
//	 * 是否有返回的路径
//	 */
//	private final boolean hasReturnUrl;
//	//
//	private final int returnType;// 0表示控制方法，1表示视图
	
}
