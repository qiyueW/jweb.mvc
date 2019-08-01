package weixinkeji.vip.jweb.mvc.model;

/**
 * 参数分类
 * 
 * @author wangchunzi
 *
 */
public enum ParamWebValueSort {
// 用户自定义 1
	/**
	 * 用户自己注册的vo
	 */
	userVo,
//基本类型 1	
	/**
	 * 基本类型
	 */
	baseType,
//框架封装 3
	/**
	 * 文件模型 ，当参数是 {@link weixinkeji.vip.jweb.mvc._component.file.FileModel} 时
	 */
	fileModel,
	/**
	 * 上传多个模型，当参数是 {@link weixinkeji.vip.jweb.mvc._component.file.FileModel[]} 时
	 */
	fileModel_N,
	/**
	 * 框架mvc中心
	 */
	JWebMvc,
//Servlet封装 3
	/**
	 * servlet-ServletRequest
	 */
	ServletRequest,
	/**
	 * servlet-HttpServletRequest
	 */
	HttpServletRequest,
	/**
	 * servlet-ServletResponse
	 */
	ServletResponse,
	/**
	 * servlet-HttpServletResponse
	 */
	HttpServletResponse,
	/**
	 * servlet-HttpSession
	 */
	HttpSession,
//注解后 2
	/**
	 * io流-json格式-vo的类型
	 */
	jsonIO,
	/**
	 * 键值对-json格式-vo的类型
	 */
	JsonKV,

//默认是用户的vo类型 1
	/**
	 * vo类型
	 */
	vo

}
