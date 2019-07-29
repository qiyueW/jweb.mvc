package weixinkeji.vip.jweb.mvc._component.convert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.BooleanDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.ByteDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.CharDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.DateDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.DoubleDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.FloatDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.IntegerDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.LongDefault;
import weixinkeji.vip.jweb.mvc._component.convert.defaultImpl.ShortDefault;

public class ConvertCenter {
	private final static Map<Class<?>, MvcStringDataConver<?>> convertDo = new HashMap<>();
	static {
		ConvertCenter cc = new ConvertCenter();
		cc.regConverCenter(new BooleanDefault());
		cc.regConverCenter(new ByteDefault());
		cc.regConverCenter(new CharDefault());
		cc.regConverCenter(new DateDefault());
		cc.regConverCenter(new DoubleDefault());
		cc.regConverCenter(new FloatDefault());
		cc.regConverCenter(new IntegerDefault());
		cc.regConverCenter(new LongDefault());
		cc.regConverCenter(new ShortDefault());
	}

	public ConvertCenter regConverCenter(MvcStringDataConver<?> obj) {
		Class<?> c = this.getMethodT(obj);
		if (c == Boolean.class) {
			convertDo.put(boolean.class, obj);
		} else if (c == Short.class) {
			convertDo.put(short.class, obj);
		} else if (c == Integer.class) {
			convertDo.put(int.class, obj);
		} else if (c == Long.class) {
			convertDo.put(long.class, obj);
		} else if (c == Float.class) {
			convertDo.put(float.class, obj);
		} else if (c == Double.class) {
			convertDo.put(double.class, obj);
		}
		convertDo.put(c, obj);
		return this;
	}

	public MvcStringDataConver<?> getMvcDataConver(Class<?> c) {
		return convertDo.get(c);
	}

	private Class<?> getMethodT(MvcStringDataConver<?> convert) {
		for (Method m : convert.getClass().getDeclaredMethods()) {
			if (m.getName().equals("toT")) {
				return m.getReturnType();
			}
		}
		return null;
	}

	public static void main(String args[]) {
		new ConvertCenter().regConverCenter(new My()).regConverCenter(new MyInt());
		System.out.println(convertDo.get(int.class).toT("aaaaaaaa"));
	}
}

class MyInt implements MvcStringDataConver<Integer> {

	@Override
	public Integer toT(String webValue) {
		return 1;
	}
}

class My implements MvcStringDataConver<String> {

	@Override
	public String toT(String webValue) {
		return webValue;
	}
}
