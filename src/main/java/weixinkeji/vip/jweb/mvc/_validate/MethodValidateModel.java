package weixinkeji.vip.jweb.mvc._validate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import weixinkeji.vip.jweb.mvc._validate.ParameterValidateModel.paramCheckType;
import weixinkeji.vip.jweb.reflect.MethodModel;
import weixinkeji.vip.jweb.reflect.ParameterModel;

public class MethodValidateModel {

	private final ValidateModel[] vm;

	public MethodValidateModel(Method m) {
		ParameterValidateModel pvm;
		// 方法模型
		MethodModel mModel = new MethodModel(m);
		// 参数模型
		ParameterModel[] yourParameters = mModel.yourParameters;
		List<ValidateModel> list = new ArrayList<>();

		for (int i = 0; i < mModel.parameterCount; i++) {
			pvm = new ParameterValidateModel(yourParameters[i].field);
			if (pvm.checkType != paramCheckType.noCheck.code) {
				list.add(new ValidateModel(i, pvm));
			}
		}
		vm = list.toArray(new ValidateModel[list.size()]);
	}

	/**
	 * null表达无需校验；false表示校验失败；true表示校验成功
	 * 
	 * @param i    第几个参数
	 * @param args 参数实例
	 * @return Boolean
	 */
	public Boolean check(int i, Object args) {
		ParameterValidateModel pm;
		for (ValidateModel v : vm) {
			if (v.index == i) {
				pm = v.pvModel;
				return pm.check(args);
			}
		}
		return null;
	}
}

class ValidateModel {
	public final int index;
	public final ParameterValidateModel pvModel;

	public ValidateModel(int index, ParameterValidateModel pvModel) {
		this.index = index;
		this.pvModel = pvModel;
	}
}
