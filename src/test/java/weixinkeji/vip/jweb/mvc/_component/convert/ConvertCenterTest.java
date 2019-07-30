package weixinkeji.vip.jweb.mvc._component.convert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import weixinkeji.vip.jweb.mvc._init._0_InitConvert;

public class ConvertCenterTest {

	@Before
	public void init() {
		List<Class<?>> list = new ArrayList<>();
		list.add(HH.class);

		// 初始化
		new _0_InitConvert(list);
	}

	@Test
	public void testGetMvcDataConver() {
		MvcStringDataConver<?> c = MvcStringConvertModel.getMvcDataConver(int.class);
		System.out.println(c.toT("1"));
	}

}
