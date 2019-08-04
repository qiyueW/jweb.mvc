package weixinkeji.vip.jweb.di.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import weixinkeji.vip.jweb.di.model.tdata.MyControllerDI;
import weixinkeji.vip.jweb.di.model.tdata.MyControllerDI_N;
import weixinkeji.vip.jweb.mvc.ann.JWebController;

public class DiObjectModelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		List<Class<?>> list = new ArrayList<>();
		list.add(MyControllerDI.class);
		list.add(MyControllerDI_N.class);

		DiObjectModel.init_0_config(JWebController.class);
		DiObjectModel.init_1_Annotation(list);
	}

	@Test
	public void testCloseInit() {
		MyControllerDI obj = DiObjectModel.get(MyControllerDI.class);
		obj.haha();
		MyControllerDI obj2 = DiObjectModel.get(MyControllerDI.class);
		obj2.haha();
		System.out.println("==================================");
		MyControllerDI_N objn = DiObjectModel.get(MyControllerDI_N.class);
		objn.haha2();
		MyControllerDI_N obj2n = DiObjectModel.get(MyControllerDI_N.class);
		obj2n.haha2();
	}

}
