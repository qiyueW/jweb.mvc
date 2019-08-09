package weixinkeji.vip.jweb.code.di.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.code.di.ann.BindProperties;
import weixinkeji.vip.jweb.code.tools.JWPPathTool;
import weixinkeji.vip.jweb.code.tools.JWPPropertiesTool;
import weixinkeji.vip.jweb.code.tools.STTools;

/**
 * 绑定属性文件 处理类
 * 
 * @author wangchunzi
 *
 */
public class BingClassPathPropertiesModel {
	// 处理目标 类
//	private Class<?> c;
	private List<Map<String, String>> propertiesList = new ArrayList<>();
	private BindProperties ann;
	private Boolean eq;

	/**
	 * 设置处理目标 类
	 * 
	 * @param c 类
	 */
	public void setClass_0(Class<?> c) {
//		this.c = c;
		this.propertiesList.clear();
		this.ann = c.getAnnotation(BindProperties.class);
		this.eq = null == this.ann ? null : ann.eq();
	}

	/**
	 * 是否有 绑定属性文件的注解
	 * 
	 * @return boolean true:表示存在。false：表示不存在
	 */
	public boolean isPropertiesAnnatotion_1() {
		return null != ann;
	}

	/**
	 * -1表示没有文件、出错。<br>
	 * 0表示文件内容为空<br>
	 * 1表示成功加载一个文件 <br>
	 * 2表示成功加载二个文件 <br>
	 * ...<br>
	 * n 表示成功加载n个文件 <br>
	 *
	 * @return int
	 */
	public int loadProperties_1() {
		JWPPropertiesTool tools = new JWPPropertiesTool();
		if (null == ann || null == ann.path() || ann.path().length == 0) {
			return -1;
		}
		int i = 0;
		Map<String, String> map;
		for (String path : ann.path()) {
			map = tools.loadPropertiesToMap(new File(JWPPathTool.getMyProjectPath(path)));
			if (null != map) {
				if (map.isEmpty()) {// 空，则继续下一个文件
					continue;
				}
				i++;// 非空时，下标加1
				this.propertiesList.add(map);// 加入集合
			} else {
				return -1;
			}
		}
		return i;
	}

	/**
	 * 取得属性文件里面的值
	 * 
	 * @param key 属性key
	 * @return String 属性文件中的value
	 */
	public String getValue(String key) {
		if (null == eq) {
			return null;
		}
		String rs;
		if (eq) {
			for (Map<String, String> m : this.propertiesList) {
				if (null != (rs = m.get(key))) {
					return rs;
				}
			}
		} else {
			String hto_ = STTools.humpTo_(key);
			for (Map<String, String> m : this.propertiesList) {
				if (null != (rs = m.get(key)) || null != (rs = m.get(hto_))) {
					return rs;
				}
			}
		}
		return null;
	}
}
