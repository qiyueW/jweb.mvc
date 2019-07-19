package weixinkeji.vip.jweb.mvc.bean;

import weixinkeji.vip.jweb.mvc._validate.ann.BindRegex;

public class Hello {

	@BindRegex(regex = "[0-9] {??1,??2}", placeholder = { "1", "2" }, alloyNull = false, error = "主键")
	private String helloId;
	private String helloName;

	public String getHelloId() {
		return helloId;
	}

	public void setHelloId(String helloId, String helloId2) {
		this.helloId = helloId + helloId2;
	}

	public String getHelloName() {
		return helloName;
	}

	public void setHelloName(String helloName, int i, Hello h) {
		this.helloName = helloName;
	}

	private void tos() {

	}

}
