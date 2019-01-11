package com.mini.util.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.MILLISECOND;

/**
 * java.util.Date 工具类
 *
 * @author XChao
 */
public final class DateUtil {
	public static final long second = 1000; // 一秒的时间戳
	public static final long minute = second * 60; // 一分钟的时间戳
	public static final long hour = minute * 60; // 一小时的时间戳
	public static final long day = hour * 24; // 一天的时间戳
	public static final long week = day * 7; // 一周的时间戳

	/**
	 * 自定义格式化类
	 *
	 * @author xchao
	 */
	private static class MySimpleDateFormat extends SimpleDateFormat {
		private static final long serialVersionUID = 3594780101239835627L;

		public MySimpleDateFormat(String format) {
			super(format, Locale.getDefault());
		}

		@Override
		public Date parse(String source) {
			try {
				return super.parse(source);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 将日期格式化为 时间 (format) 格式
	 *
	 * @param date   日期
	 * @param format 时间格式
	 */
	public static String format(Date date, String format) {
		if (date == null || format == null) return null;
		return new MySimpleDateFormat(format).format(date);
	}

	/**
	 * 将日期格式化为 时间 (format) 格式
	 *
	 * @param date   日期
	 * @param format 时间格式
	 */
	public static String format(long date, String format) {
		return format(new Date(date), format);
	}

	/**
	 * 将日期格式化为 时间 (format) 格式
	 *
	 * @param date   日期
	 * @param format 时间格式
	 */
	public static String format(Calendar date, String format) {
		return format(date.getTime(), format);
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd 格式
	 *
	 * @param date
	 */
	public static String formatDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd 格式
	 *
	 * @param date
	 */
	public static String formatDate(long date) {
		return formatDate(new Date(date));
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd 格式
	 *
	 * @param date
	 */
	public static String formatDate(Calendar date) {
		return formatDate(date.getTime());
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatDateTime(long date) {
		return formatDateTime(new Date(date));
	}

	/**
	 * 将日期格式化成：yyyy-MM-dd HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatDateTime(Calendar date) {
		return formatDateTime(date.getTime());
	}

	/**
	 * 将日期格式化成：HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 将日期格式化成：HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatTime(long date) {
		return formatTime(new Date(date));
	}

	/**
	 * 将日期格式化成：HH:mm:ss 格式
	 *
	 * @param date
	 */
	public static String formatTime(Calendar date) {
		return formatTime(date.getTime());
	}

	/**
	 * 将日期格式的字符串转化成日期
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		if (date == null || format == null) return null;
		return new MySimpleDateFormat(format).parse(date);
	}

	/**
	 * 将 yyyy-MM-dd 日期格式的字符串转换成日期格式
	 *
	 * @param date
	 */
	public static Date parseDate(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 将(HH:mm:ss)时间格式的字符串转换成日期格式
	 *
	 * @param date
	 */
	public static Date parseTime(String date) {
		return parse(date, "HH:mm:ss");
	}

	/**
	 * 将(yyyy-MM-dd HH:mm:ss)时间格式的字符串转换成日期格式
	 *
	 * @param date
	 */
	public static Date parseDateTime(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据指定日期创建Calendar对象
	 *
	 * @param date
	 * @return
	 */
	public static Calendar createCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 根据指定日期创建Calendar对象
	 *
	 * @param date
	 * @return
	 */
	public static Calendar createCalendar(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar;
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		return getStartOfDay(createCalendar(date));
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(long date) {
		return getStartOfDay(createCalendar(date));
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		return getEndOfDay(createCalendar(date));
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(long date) {
		return getEndOfDay(createCalendar(date));
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfDay(Calendar date, int offset) {
		date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) + offset);
		return date.getTime();
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfDay(Date date, int offset) {
		return getDateOfDay(createCalendar(date), offset);
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfDay(long date, int offset) {
		return getDateOfDay(createCalendar(date), offset);
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfDay(Calendar date, int offset) {
		date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) + offset);
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfDay(Date date, int offset) {
		return getStartOfDay(createCalendar(date), offset);
	}

	/**
	 * 获取一天中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfDay(long date, int offset) {
		return getStartOfDay(createCalendar(date), offset);
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfDay(Calendar date, int offset) {
		date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) + offset);
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfDay(Date date, int offset) {
		return getEndOfDay(createCalendar(date), offset);
	}

	/**
	 * 获取一天中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfDay(long date, int offset) {
		return getEndOfDay(createCalendar(date), offset);
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfWeek(Calendar date) {
		date.set(Calendar.DAY_OF_WEEK, date.getActualMinimum(Calendar.DAY_OF_WEEK));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfWeek(Date date) {
		return getStartOfWeek(createCalendar(date));
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfWeek(long date) {
		return getStartOfWeek(createCalendar(date));
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfWeek(Calendar date) {
		date.set(Calendar.DAY_OF_WEEK, date.getActualMaximum(Calendar.DAY_OF_WEEK));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfWeek(Date date) {
		return getEndOfWeek(createCalendar(date));
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfWeek(long date) {
		return getEndOfWeek(createCalendar(date));
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfWeek(Calendar date, int offset) {
		date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) + offset);
		return date.getTime();
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfWeek(Date date, int offset) {
		return getDateOfWeek(createCalendar(date), offset);
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfWeek(long date, int offset) {
		return getDateOfWeek(createCalendar(date), offset);
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfWeek(Calendar date, int offset) {
		date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) + offset);
		date.set(Calendar.DAY_OF_WEEK, date.getActualMinimum(Calendar.DAY_OF_WEEK));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfWeek(Date date, int offset) {
		return getStartOfWeek(createCalendar(date), offset);
	}

	/**
	 * 获取一周中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfWeek(long date, int offset) {
		return getStartOfWeek(createCalendar(date), offset);
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfWeek(Calendar date, int offset) {
		date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) + offset);
		date.set(Calendar.DAY_OF_WEEK, date.getActualMaximum(Calendar.DAY_OF_WEEK));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfWeek(Date date, int offset) {
		return getEndOfWeek(createCalendar(date), offset);
	}

	/**
	 * 获取一周中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfWeek(long date, int offset) {
		return getEndOfWeek(createCalendar(date), offset);
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfMonth(Calendar date) {
		date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfMonth(Date date) {
		return getStartOfMonth(createCalendar(date));
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfMonth(long date) {
		return getStartOfMonth(createCalendar(date));
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfMonth(Calendar date) {
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfMonth(Date date) {
		return getEndOfMonth(createCalendar(date));
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfMonth(long date) {
		return getEndOfMonth(createCalendar(date));
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfMonth(Calendar date, int offset) {
		date.set(Calendar.MONTH, date.get(Calendar.MONTH) + offset);
		return date.getTime();
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfMonth(Date date, int offset) {
		return getDateOfMonth(createCalendar(date), offset);
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfMonth(long date, int offset) {
		return getDateOfMonth(createCalendar(date), offset);
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfMonth(Calendar date, int offset) {
		date.set(Calendar.MONTH, date.get(Calendar.MONTH) + offset);
		date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfMonth(Date date, int offset) {
		return getStartOfMonth(createCalendar(date), offset);
	}

	/**
	 * 获取一月中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfMonth(long date, int offset) {
		return getStartOfMonth(createCalendar(date), offset);
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfMonth(Calendar date, int offset) {
		date.set(Calendar.MONTH, date.get(Calendar.MONTH) + offset);
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfMonth(Date date, int offset) {
		return getEndOfMonth(createCalendar(date), offset);
	}

	/**
	 * 获取一月中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfMonth(long date, int offset) {
		return getEndOfMonth(createCalendar(date), offset);
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfYear(Calendar date) {
		date.set(Calendar.MONTH, date.getActualMinimum(Calendar.MONTH));
		date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfYear(Date date) {
		return getStartOfYear(createCalendar(date));
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfYear(long date) {
		return getStartOfYear(createCalendar(date));
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfYear(Calendar date) {
		date.set(Calendar.MONTH, date.getActualMaximum(Calendar.MONTH));
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfYear(Date date) {
		return getEndOfYear(createCalendar(date));
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfYear(long date) {
		return getEndOfYear(createCalendar(date));
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfYear(Calendar date, int offset) {
		date.set(Calendar.YEAR, date.get(Calendar.YEAR) + offset);
		return date.getTime();
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfYear(Date date, int offset) {
		return getDateOfYear(createCalendar(date), offset);
	}

	/**
	 * 根据偏移量获取日期
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDateOfYear(long date, int offset) {
		return getDateOfYear(createCalendar(date), offset);
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfYear(Calendar date, int offset) {
		date.set(Calendar.YEAR, date.get(Calendar.YEAR) + offset);
		date.set(Calendar.MONTH, date.getActualMinimum(Calendar.MONTH));
		date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMinimum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfYear(Date date, int offset) {
		return getStartOfYear(createCalendar(date), offset);
	}

	/**
	 * 获取一年中最早的时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getStartOfYear(long date, int offset) {
		return getStartOfYear(createCalendar(date), offset);
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfYear(Calendar date, int offset) {
		date.set(Calendar.YEAR, date.get(Calendar.YEAR) + offset);
		date.set(Calendar.MONTH, date.getActualMaximum(Calendar.MONTH));
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
		date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
		date.set(MILLISECOND, date.getActualMaximum(MILLISECOND));
		return date.getTime();
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfYear(Date date, int offset) {
		return getEndOfYear(createCalendar(date), offset);
	}

	/**
	 * 获取一年中最晚时刻
	 *
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getEndOfYear(long date, int offset) {
		return getEndOfYear(createCalendar(date), offset);
	}

	/**
	 * 比较两个日期是否为同一天
	 *
	 * @param date
	 * @param c
	 * @return
	 */
	public static boolean isSameDay(Calendar date, Calendar c) {
		boolean result = date.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH);
		result = result && date.get(Calendar.MONTH) == c.get(Calendar.MONTH);
		return result && date.get(Calendar.YEAR) == c.get(Calendar.YEAR);
	}

	/**
	 * 比较两个日期是否为同一天
	 *
	 * @param date
	 * @param d
	 * @return
	 */
	public static boolean isSameDay(Date date, Date d) {
		return isSameDay(createCalendar(date), createCalendar(d));
	}

	/**
	 * 比较两个日期是否为同一天
	 *
	 * @param date
	 * @param l
	 * @return
	 */
	public static boolean isSameDay(long date, long l) {
		return isSameDay(createCalendar(date), createCalendar(l));
	}

	/**
	 * 判断两个日期是否在同一周
	 *
	 * @param date
	 * @param c
	 * @return
	 */
	public static boolean isSameWeek(Calendar date, Calendar c) {
		long end = getEndOfWeek(date.getTimeInMillis()).getTime();
		long start = getStartOfWeek(date.getTimeInMillis()).getTime();
		return c.getTimeInMillis() >= start && c.getTimeInMillis() <= end;
	}

	/**
	 * 比较两个日期是否为同一周
	 *
	 * @param date
	 * @param d
	 * @return
	 */
	public static boolean isSameWeek(Date date, Date d) {
		return isSameWeek(createCalendar(date), createCalendar(d));
	}

	/**
	 * 比较两个日期是否为同一周
	 *
	 * @param date
	 * @param l
	 * @return
	 */
	public static boolean isSameWeek(long date, long l) {
		return isSameWeek(createCalendar(date), createCalendar(l));
	}

	/**
	 * 判断两个日期是否在同一月
	 *
	 * @param date
	 * @param c
	 * @return
	 */
	public static boolean isSameMonth(Calendar date, Calendar c) {
		boolean result = date.get(Calendar.MONTH) == c.get(Calendar.MONTH);
		return result && date.get(Calendar.YEAR) == c.get(Calendar.YEAR);
	}

	/**
	 * 比较两个日期是否为同一月
	 *
	 * @param date
	 * @param d
	 * @return
	 */
	public static boolean isSameMonth(Date date, Date d) {
		return isSameMonth(createCalendar(date), createCalendar(d));
	}

	/**
	 * 比较两个日期是否为同一月
	 *
	 * @param date
	 * @param l
	 * @return
	 */
	public static boolean isSameMonth(long date, long l) {
		return isSameMonth(createCalendar(date), createCalendar(l));
	}

	/**
	 * 判断两个日期是否在同一年
	 *
	 * @param date
	 * @param c
	 * @return
	 */
	public static boolean isSameYear(Calendar date, Calendar c) {
		return date.get(Calendar.YEAR) == c.get(Calendar.YEAR);
	}

	/**
	 * 比较两个日期是否为同一年
	 *
	 * @param date
	 * @param d
	 * @return
	 */
	public static boolean isSameYear(Date date, Date d) {
		return isSameYear(createCalendar(date), createCalendar(d));
	}

	/**
	 * 比较两个日期是否为同一年
	 *
	 * @param date
	 * @param l
	 * @return
	 */
	public static boolean isSameYear(long date, long l) {
		return isSameYear(createCalendar(date), createCalendar(l));
	}
}
