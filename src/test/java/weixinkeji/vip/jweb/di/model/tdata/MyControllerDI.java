package weixinkeji.vip.jweb.di.model.tdata;

import weixinkeji.vip.jweb.mvc.ann.JWebController;

@JWebController
public class MyControllerDI {

	int i = 1;

	public void haha() {
		System.out.println(this + " = haha" + (i++));
	}
}
