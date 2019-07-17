package weixinkeji.vip.jweb.mvc.vo;

public class JWebMVCValidateVo {

	public final String regex;
	public final String errorMessage;
	public final boolean isMust;
	
	public JWebMVCValidateVo(String regex, String errorMessage, boolean isMust) {
		this.regex = regex;
		this.errorMessage = errorMessage;
		this.isMust = isMust;
	}
	
	/**
	 * 檢查不通過時，返回錯誤的信息
	 * @param source 被檢測的字符串
	 * @return String null|錯誤的字符串
	 */
	public String check(String source) {
		return source.matches(regex)?null:errorMessage;
	}

}
