package weixinkeji.vip.jweb.mvc.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import weixinkeji.vip.jweb.mvc._component.mvc_bind_url.MvcBindUrl;
import weixinkeji.vip.jweb.mvc._component.mvc_bind_url.MvcBindUrlModel;
import weixinkeji.vip.jweb.mvc.ann.UrlActionSort;

/**
 * 模型中，有部分数据是从用户开发中来，此类是对外部分数据，整合到我们的模型中来
 * 
 * @author wangchunzi
 *
 */
public class _InitMvcModel {

	private static boolean init = false;

	synchronized public static void init(List<Class<?>> list) {
		if (init) {
			return;
		}
		init = true;
		// 专用处理绑定在类、方法上的路径的操作方法
		MvcBindUrl bindUrlModel = MvcBindUrlModel.getMvcBindUrl();
		// 临时变量
		String headUrl, methodUrl;
		UrlActionSort urlActionSort;
		for (Class<?> c : list) {
			try {
				headUrl = bindUrlModel.getClassUrl(c);
				if (null != headUrl) {// 当类上有控制类的注解时
					for (Method m : c.getDeclaredMethods()) {
						methodUrl = bindUrlModel.getMethodUrl(m); // 方法上绑定的路径
						urlActionSort = bindUrlModel.getUrActionSort(m); // get\post\put\.....
						// 注册mvc处理
						MvcMethodModel.regMvcMethodModel(getUrl(headUrl, methodUrl), m, urlActionSort);
						
					}
					c.getConstructor().newInstance();// 实例
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 合并 请求地址，变成一个完整的请求地址<br>
	 * 请求地址格式 /asdfsdfdsaf/asdfsdf/sdafsdfsadfsdaf
	 * 
	 * @param headUrl   绑定在类上的请求路径
	 * @param methodUrl 绑定在方法上的请求路径
	 * @return String 完整的请求路径 （当返回null时，表示 请求路径为空）
	 */
	private static String getUrl(String headUrl, String methodUrl) {
//当类没有绑定路径时
		if (null == headUrl || headUrl.isEmpty()) {
			// 方法也没有绑定路径时，返回null——即此方法没有绑定请求路径
			return null == methodUrl || methodUrl.isEmpty() ? null : formartUrl(methodUrl);
		}
// 类绑定了，方法没绑定。直接返回null
		if (null == methodUrl || methodUrl.isEmpty()) {
			return null;
		}
//类、方法都绑定了路径
		headUrl = formartUrl(headUrl); // 类上的路径两种情况： / 或 /adsaf/asdf....
		methodUrl = formartUrl(methodUrl);
		return formartUrl(headUrl + methodUrl);
	}

	/**
	 * 格式化路径。 确保路径 必定以/开头，不以/结尾<br>
	 * 当 url为null或空时，直接返回空<br>
	 * 当只有/时，不处理，直接返回/
	 * 
	 * @param url 需要被格式化的路径
	 * @return String 格式化后的路径
	 */
	private static String formartUrl(String url) {
		url = url.trim();
		if (null == url || url.isEmpty()) {
			return "";
		}
		if (url.equalsIgnoreCase("/")) {
			return url;
		}
		char[] s = url.toCharArray();
		StringBuilder sb = new StringBuilder();
		int lastIndex = s.length - 1;

		for (int i = 0; i < s.length; i++) {
			if (i == 0 && s[0] != '/') {// 如果不以/开头
				sb.append('/');
			}
			if (i == 1 && s[i] == '/') {// 如果出现//的情况 去除
				continue;
			}
			if (i == lastIndex && s[i] == '/') {// 最后一位是/时，去除
				break;
			}
			sb.append(s[i]);
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		String url = "// sdfsdf/sdfsdf  /sdfsdf/";
		String url2 = " // ";
		System.out.println(formartUrl(url));
		System.out.println(formartUrl(url2));
	}

}
