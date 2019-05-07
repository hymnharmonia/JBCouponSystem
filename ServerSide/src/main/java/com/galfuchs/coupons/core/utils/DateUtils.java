package com.galfuchs.coupons.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static Date stringDateToUtilDate(String date) throws ApplicationException {
		Date formattedDate = null;
		try {
			formattedDate = sdf.parse(date);
		} catch (ParseException e) {
			throw new ApplicationException(e, "Error parsing date",ErrorType.SYSTEM_ERROR);
		}
		
		return formattedDate;
		
	}
	
	public static java.sql.Date stringDateToSqlDate(String date) throws ApplicationException {
		java.sql.Date formattedDate = new java.sql.Date(stringDateToUtilDate(date).getTime());
		return formattedDate;
	}
	
	public static String utilDateToStringDate(Date date) {
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static String sqlDateToStringDate(java.sql.Date date) {
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

	public static java.sql.Date utilDateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static Date sqlDateToUtilDate(java.sql.Date date) {
		return new Date(date.getTime());
	}
}
