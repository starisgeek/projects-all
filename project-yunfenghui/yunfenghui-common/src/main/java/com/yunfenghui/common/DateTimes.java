package com.yunfenghui.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日期时间工具类
 * 
 * @author wanghao 2017年10月30日
 */
public abstract class DateTimes {
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.sss";
	public static final String PATTERN_YYMMDDHHMMSS = "yyMMddHHmmss";
	public static final String PATTERN_HH_MM_SS = "HH:mm:ss";

	/**
	 * 获取给定日期的最早时间。比如：传入的参数为2017-10-30 20:28:53，则返回2017-10-30 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date earliestOf(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date earliestOf(String dateString, String pattern) {
		return earliestOf(dateOf(dateString, pattern));
	}

	/**
	 * 获取给定日期的最早时间。比如：传入的参数为2017-10-30 20:28:53，则返回2017-10-30 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date latestOf(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Date latestOf(String dateString, String pattern) {
		return latestOf(dateOf(dateString, pattern));
	}

	/**
	 * 获取给定日期的最早时间和最晚时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] earliestAndLatestOf(Date date) {
		Date[] result = new Date[2];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		result[0] = new Date(c.getTimeInMillis());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		result[1] = new Date(c.getTimeInMillis());
		return result;
	}

	public static Date[] earliestAndLatestOf(String dateString, String pattern) {
		Date day = dateOf(dateString, pattern);
		Date[] result = new Date[2];
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		result[0] = new Date(c.getTimeInMillis());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		result[1] = new Date(c.getTimeInMillis());
		return result;
	}

	public static Date dateOf(String dateString, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("failed to parse date string:" + dateString, e);
		}
	}

	public static String dayStringOf(Date day, String pattern) {
		return new SimpleDateFormat(pattern).format(day);
	}

	public static String dayStringOf(Date day) {
		return new SimpleDateFormat(PATTERN_YYYY_MM_DD).format(day);
	}

	public static int dayIntOf(Date day) {
		return Integer.valueOf(new SimpleDateFormat(PATTERN_YYYYMMDD).format(day));
	}

	public static String fullDayStringOf(Date day) {
		return new SimpleDateFormat(PATTERN_YYYY_MM_DD_HH_MM_SS).format(day);
	}

	public static long pastMillisOfToday() {
		Date now = new Date();
		return now.getTime() - earliestOf(now).getTime();
	}

	public static Date addDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + days);
		return c.getTime();
	}

	public static void sleep(long timeout, TimeUnit timeUnit) {
		if (timeout > 0) {
			try {
				timeUnit.sleep(timeout);
			} catch (InterruptedException e) {
			}
		}
	}

	public static int timeIntervalAsMinute(Date earliest, Date latestDate) {
		long milliseconds = latestDate.getTime() - earliest.getTime();
		return (int) (milliseconds / 60_000);
	}

}
