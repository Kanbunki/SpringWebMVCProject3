package com.bookShop.onlineShop.storehouse.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			date = dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
