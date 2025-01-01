package com.aegis.ultima.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class DateUtils {
	
	private final static String dateFormatEncrypt="YYYY-MM-dd-hh-mm-ss"; 
	private final static String generalDateFormat="yyyy-MM-dd hh:mm:ss";
	private final static Logger logger = LogManager.getLogger(DateUtils.class);

	public static Date getCurrentDate() {
		java.util.Date utilDate = new java.util.Date();
		Date sqlDate = new Date(utilDate.getTime());
		return sqlDate;
	}
	
	public static Date convertJavaUtilDateToSqlDate(java.util.Date input) {
//		java.util.Date utilDate = new java.util.Date();
		Date sqlDate = new Date(input.getTime());
		return sqlDate;
	}
	
	public static Date addOneWeek(Date dateInput) {   
		Date returnDate = null;
		Calendar c = Calendar.getInstance(); 
//		try {
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		c.setTime(dateInput);
		c.add(Calendar.DAY_OF_MONTH, 7);
		returnDate = new Date(c.getTime().getTime());
		return returnDate;  
	}
	
	public static Date addMinutes(Date dateInput,Integer addition) {   
		Date returnDate = null;
		Calendar c = Calendar.getInstance(); 
//		try {
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		c.setTime(dateInput);
		c.add(Calendar.MINUTE, addition);
		returnDate = new Date(c.getTime().getTime());
		return returnDate;  
	}
	
	public static Boolean isBeforeDate(java.util.Date date1,java.util.Date date2){  
		Boolean returnValue = false;
        if(date1.before(date2)){
        	returnValue = true;
        } 
        return returnValue;
    } 
	
	public static Boolean isBeforeDate2(Date date1,java.util.Date date2){  
		java.util.Date date1New = new java.util.Date(date1.getTime()); 
		Boolean returnValue = false;
        if(date2.before(date1New)){
        	returnValue = true;
        } 
        return returnValue;
    } 
	
	public static java.util.Date convertStringToDate(String date) {
		java.util.Date resultDate = null;
		//String formatOutput = generalDateFormat;
		String formatInput = dateFormatEncrypt; 
		SimpleDateFormat sdf = new SimpleDateFormat(formatInput); 
		//java.sql.Date sqlDate = new java.sql.Date(sdf.parse(date).getTime());

		try {
			resultDate = new java.util.Date(sdf.parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e);
		}
		return resultDate;
	}

	
	public static Date reduceOneDay(Date dateInput) {   
		Date returnDate = null;
		Calendar c = Calendar.getInstance(); 
//		try {
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		c.setTime(dateInput);
		c.add(Calendar.DAY_OF_MONTH, -1);
		returnDate = new Date(c.getTime().getTime());
		return returnDate;  
	}
	
	public static String convertDateToString(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String text = df.format(date);
		return text;
	}
	
	public static long differentDaysOfDate(java.util.Date Date1, java.util.Date Date2) {
		
		long diffInMillies = Math.abs(Date1.getTime() - Date2.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}
	
	
}
