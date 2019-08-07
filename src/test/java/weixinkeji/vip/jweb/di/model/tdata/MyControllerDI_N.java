package weixinkeji.vip.jweb.di.model.tdata;

import weixinkeji.vip.jweb.code.di.ann.JWebDINewType;
import weixinkeji.vip.jweb.mvc.ann.JWebController;

@JWebDINewType
@JWebController
public class MyControllerDI_N {

	int i = 1;

	public void haha2() {
		System.out.println(this + " = haha" + (i++));
	}
}
