package weixinkeji.vip.jweb.mvc.bean;

import weixinkeji.vip.jweb.mvc._validate.ann.BindRegex;

public class Hello {

	@BindRegex(regex = "[0-9]{??1,??2}", placeholder = { "1", "2" }, alloyNull = false, error = "主键")
	private String helloId;
	@BindRegex(regex = "[a-z0-9]{??1,??2}", placeholder = { "1", "2" }, alloyNull = false, error = "名称")
	private String helloName;

	@BindRegex(regex = "[0-9]{??1,??2}", placeholder = { "1", "2" }, alloyNull = false, error = "名称")
	private int age=1;
	
	@BindRegex(regex = "[0-9.]{??1,??2}", placeholder = { "1", "4" }, alloyNull = false, error = "名称")
	private double num2=11.1;
	
	@BindRegex
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHelloId() {
		return helloId;
	}

	public void setHelloId(String helloId) {
		this.helloId = helloId;
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
