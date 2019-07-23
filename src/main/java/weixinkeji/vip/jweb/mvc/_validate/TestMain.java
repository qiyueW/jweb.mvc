package weixinkeji.vip.jweb.mvc._validate;

import weixinkeji.vip.jweb.mvc._DoControllerMethod;
import weixinkeji.vip.jweb.mvc.bean.Hello;
import weixinkeji.vip.jweb.reflect.ClassFieldsModel;

public class TestMain {

	public static void main(String[] args) {

		ClassFieldsModel cfm = new ClassFieldsModel(_DoControllerMethod.class);
		MethodValidateModel mvm;
		Hello obj = new Hello();
		obj.setAge(123);
		obj.setHelloId("1");
		obj.setHelloName("name", 2, null);
		
		String id = "123";
		mvm = new MethodValidateModel(cfm.methodsModels[0].method);
		mvm.check(1,obj);
		mvm.check(2, id);
	}

}
