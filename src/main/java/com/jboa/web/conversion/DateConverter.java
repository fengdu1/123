package com.jboa.web.conversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.XWorkException;

public class DateConverter extends StrutsTypeConverter {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Date date = null;
		df.setLenient(false);
		try {
			date = df.parse(values[0]);
		} catch (ParseException e) {
			throw new XWorkException("不能把" + values[0] + "转换成日期！", e);
		}
		return date;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
