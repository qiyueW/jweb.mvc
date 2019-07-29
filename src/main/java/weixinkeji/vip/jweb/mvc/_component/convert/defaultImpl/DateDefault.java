package weixinkeji.vip.jweb.mvc._component.convert.defaultImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import weixinkeji.vip.jweb.mvc._component.convert.MvcStringDataConver;

public class DateDefault implements MvcStringDataConver<Date> {

	private SimpleDateFormat sf_hg = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sf_hg_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sf_xg = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sf_xg_time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public Date toT(String webValue) {
		if (null == webValue || webValue.isEmpty()) {
			return null;
		}
		try {
			webValue = webValue.trim();
			int lent = webValue.length();
			if (lent < 11) {
				return webValue.contains("-") ? sf_hg.parse(webValue) : sf_xg.parse(webValue);
			} else {
				return webValue.contains("-") ? sf_hg_time.parse(webValue) : sf_xg_time.parse(webValue);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
