package weixinkeji.vip.jweb.mvc;

import java.util.List;

import weixinkeji.vip.jweb.mvc._validate.ValidateFactory;
import weixinkeji.vip.jweb.scan.JWPScanClassFactory;

public class DataCenter {
	static {
		// JWebMvc.properties
		List<Class<?>> scanclass = JWPScanClassFactory.getClassByFilePath("xxxxxxxxxx");

		ValidateFactory._0_init(scanclass);//检验组件  开始初始化
		
	}
}
