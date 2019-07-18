package weixinkeji.vip.jweb.mvc.tools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpWebParamTool {
		private HttpServletRequest req;
		private HttpServletResponse resp;

		public HttpWebParamTool(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			this.req = req;
			this.resp = resp;
		}

		/**
		 * 
		 * 目标：UserVO obj=get(UserVO.class) 传入一个类，得到与它属性同名的 传参（客户端提交的表单的值）
		 * 
		 * @param c  类
		 * @return T
		 * @throws Exception 异常
		 */
		public <T>T get(Class<T> c) throws Exception {
			if (null == c) {
				return null;
			}
			T obj = null;
			try {
//				通过反射，实例化一个对象（等价于 new 类())
				obj = c.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				return null;
			}
			String webVO;
	//====================解决第1步的问题=======================
			// 通过反射，取得c类的所有属性（包含私有的）
			Field[] fields = c.getDeclaredFields();
			// 拿到一个一个属性来处理
			for (Field f : fields) {
				webVO = req.getParameter(f.getName());
				System.out.println("用户提交的请求：" + webVO);
				System.out.println("装箱类的属性类型：" + f.getType().getSimpleName());

				// 如果webVO是null,说明用户没有提交此参数
				if (null == webVO) {
					continue;
				}
				webVO=webVO.trim();
				f.setAccessible(true);
				
				//解决第二步的问题
				this.setObjectValue(f, obj, webVO);
			}
			return obj;
		}
		
		private void setObjectValue(Field f,Object obj,String webVO)throws Exception  {
			switch (f.getType().getSimpleName()) {
			case "String":
				f.set(obj, webVO);
				return;
			case "boolean":
			case "Boolean":
				f.set(obj, Boolean.parseBoolean(webVO));
				return;
			case "short":
			case "Short":
				f.set(obj, Short.parseShort(webVO));
				return;
			case "int":
			case "Integer":
				f.set(obj, Integer.parseInt(webVO));
				return;
			case "long":
			case "Long":
				f.set(obj, Long.parseLong(webVO));
				return;
			case "float":
			case "Float":
				f.set(obj, Float.parseFloat(webVO));
				return;
			case "double":
			case "Double":
				f.setDouble(obj, Double.parseDouble(webVO));
				return;
			case "Date": 
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				f.set(obj,sf.parse(webVO));
			}
		}
}
