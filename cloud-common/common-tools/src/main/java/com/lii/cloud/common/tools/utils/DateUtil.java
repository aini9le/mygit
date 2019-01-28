package com.lii.cloud.common.tools.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/**
	 * Date Style
	 */
	public static final String DATESTYLE = "yyyyMMddHHmmss";

	/**
	 * Date Style for Extention
	 */
	public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";

	/**
	 * Date Style for Extention
	 */
	public static final String DATESTYLE_ = "yyyy-MM-dd";

	/**
	 * Date Style for Year & Month
	 */
	public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";

	/**
	 * Date Style for Short
	 */
	public static final String DATESTYLE_SHORT = "yyyyMMdd";

	/**
	 * Date Style for Extention
	 */
	public static final String DATESTYLE_SHORT_EX = "yyyy/MM/dd";

	/**
	 * Date Style for Year & Month Extention
	 */
	public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";

	// static long now = System.currentTimeMillis();
	// public static Date CurrTime = new Date(now);

	/**
	 * 日期转化为字符串
	 * 
	 * @param date
	 *            时间
	 * @return yyyy-MM-dd HH:mm:ss 格式化的时间字符串
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期转化为字符串
	 * 
	 * @param date
	 *            时间
	 * @return yyyy-MM-dd 格式化的时间字符串
	 */
	public static String dateToStringShort(Date date) {
		if (date == null)
			return "";
		return FormatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 计算两个日期差（毫秒）
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 相差秒数
	 */
	public static long diffTwoDate(Date date1, Date date2) {
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		return (l1 - l2) / 1000;
	}

	public static long diffTwoString(String date1, String date2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		return diffTwoDate(d1, d2);
	}

	/**
	 * 计算两个日期差（天）
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 相差天数
	 */
	public static int diffTwoDateDay(Date date1, Date date2) {
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		int diff = Integer.parseInt("" + (l1 - l2) / 3600 / 24 / 1000);
		return diff;
	}

	/**
	 * 对日期进行格式化
	 * 
	 * @param date
	 *            日期
	 * @param sf
	 *            日期格式
	 * @return 字符串
	 */
	public static String FormatDate(Date date, String sf) {
		if (date == null)
			return "";
		SimpleDateFormat dateformat = new SimpleDateFormat(sf);
		return dateformat.format(date);
	}

	/**
	 * 取得当前系统日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getCurrDate() {
		Date date_time = new Date();
		return FormatDate(date_time, "yyyy-MM-dd");
	}

	// 取系统时间时一定要用这个方法，否则日期可能不动
	public static Date getCurrDateTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 取得当前系统时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrTime() {
		Date date_time = new Date();
		return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static String getCurrTimeSss() {
		return FormatDate(new Date(), DATESTYLE_DETAIL);
	}

	/**
	 * 10位时间转为8时间
	 * 
	 * @param str
	 * @return
	 */
	public static String getDate10to8(String str) {
		String y = str.substring(0, 4);
		String m = str.substring(5, 7);
		String d = str.substring(8, 10);
		return y + m + d;
	}

	/**
	 * 8位时间转为10时间
	 * 
	 * @param str
	 * @return
	 */
	public static String getDate8to10(String str) {
		String y = str.substring(0, 4);
		String m = str.substring(4, 6);
		String d = str.substring(6, 8);
		return y + "-" + m + "-" + d;
	}

	/**
	 * 取得日期的天份
	 * 
	 * @param date
	 *            日期
	 * @return dd 天字符串
	 */
	public static String getDay(Date date) {
		return FormatDate(date, "dd");
	}

	/**
	 * 取得日期的小时
	 * 
	 * @param date
	 *            日期
	 * @return hh 小时字符串
	 */
	public static String getHour(Date date) {
		return FormatDate(date, "HH");
	}

	/**
	 * 取得日期的分钟
	 * 
	 * @param date
	 *            时间
	 * @return mm 分钟字符串
	 */
	public static String getMinute(Date date) {
		return FormatDate(date, "mm");
	}

	/**
	 * 取得日期的月份
	 * 
	 * @param date
	 *            日期
	 * @return mm 月份字符串
	 */
	public static String getMonth(Date date) {
		return FormatDate(date, "MM");
	}

	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 取得时间的秒
	 * 
	 * @param date
	 *            时间
	 * @return ss 秒字符串
	 */
	public static String getSecond(Date date) {
		return FormatDate(date, "ss");
	}

	/**
	 * 根据年、月取得月末的日期
	 * 
	 * @param year
	 *            年
	 * @parm month 月
	 * @return time 返回日期格式"yyyy-mm-dd"
	 */
	public static String getTime(String year, String month) {
		String time = "";
		int len = 31;
		int iYear = Integer.parseInt(year);
		int iMonth = Integer.parseInt(month);
		if (iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11)
			len = 30;
		if (iMonth == 2) {
			len = 28;
			if ((iYear % 4 == 0 && iYear % 100 == 0 && iYear % 400 == 0) || (iYear % 4 == 0 && iYear % 100 != 0)) {
				len = 29;
			}
		}
		time = year + "-" + month + "-" + String.valueOf(len);
		return time;
	}

	/**
	 * 取得日期的年份
	 * 
	 * @param date
	 *            日期
	 * @return yyyy 年份字符串
	 */
	public static String getYear(Date date) {
		return FormatDate(date, "yyyy");
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 *            测试参数
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// System.out.println(DateUtil.getCurrTime());
		// System.out.println(getLateMin(10));
		// System.out.println(diffTwoString(DateUtil.getCurrTime(),getLateMin(10)));
		System.out.println(new Date().getTime());

	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param dateString
	 *            yyyy-MM-dd HH:mm:ss
	 * @return 日期
	 */
	public static Date stringToDate(String dateString) {
		if (dateString == null || dateString.trim().length() == 0)
			return null;
		String datestr = dateString.trim();

		String sf = "yyyy-MM-dd HH:mm:ss";
		Date dt = stringToDate(datestr, sf);
		if (dt == null)
			dt = stringToDate(datestr, "yyyy-MM-dd");
		if (dt == null)
			dt = stringToDate(datestr, "yyyyMMdd");
		return dt;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param dateString
	 *            日期格式字符串
	 * @param sf
	 *            日期格式化定义
	 * @return 转换后的日期
	 */
	public static Date stringToDate(String dateString, String sf) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		Date dt = sdf.parse(dateString, pos);
		return dt;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param dateString
	 *            yyyy-MM-dd
	 * @return 日期
	 */
	public static Date stringToDateShort(String dateString) {
		String sf = "yyyy-MM-dd";
		Date dt = stringToDate(dateString, sf);
		return dt;
	}

	public DateUtil() {
	}

	/**
	 * 取得日以上粒度起始时间
	 * 
	 * @param granularity
	 *            粒度
	 * @param statisticDate
	 *            结束时间
	 * @return 起始时间
	 */
	public String getBeginDate(String granularity, String statisticDate) {
		String beginDate = "";
		Date date = DateUtil.stringToDateShort(statisticDate);
		Date beginDateTemp = null;
		if (granularity.equals("1")) {// 日
			beginDateTemp = date;
		}
		if (granularity.equals("2")) {// 周
			beginDateTemp = this.getWeekBegin(date);
		}
		if (granularity.equals("3")) {// 旬
			beginDateTemp = this.getPeriodBegin(date);
		} else if (granularity.equals("4")) {// 月
			beginDateTemp = this.getMonthBegin(date);
		} else if (granularity.equals("5")) {// 季
			beginDateTemp = this.getSeasonBegin(date);
		} else if (granularity.equals("6")) {// 半年
			beginDateTemp = this.getHalfYearBegin(date);
		} else if (granularity.equals("7")) {// 年
			beginDateTemp = this.getYearBegin(date);
		}
		beginDate = DateUtil.dateToStringShort(beginDateTemp);
		return beginDate;
	}

	/**
	 *
	 * @param currentTime
	 *            计算日期
	 * @param type
	 *            偏移的类别
	 * @param iQuantity
	 *            偏移数量
	 * @return 偏移后的时间串
	 */
	public String getDateChangeALL(String currentTime, String type, int iQuantity) {
		Date curr = null;
		String newtype = "";
		if (currentTime.length() == 10) {
			curr = DateUtil.stringToDateShort(currentTime);
		}
		if (currentTime.length() > 10) {
			curr = DateUtil.stringToDate(currentTime);
		}

		// 日
		if (type.equals("1")) {
			newtype = "d";
		}
		// 周，按照7天计算
		else if (type.equals("2")) {
			iQuantity = iQuantity * 7;
			newtype = "d";
		}
		// 旬，按照10天计算
		else if (type.equals("3")) {
			iQuantity = iQuantity * 10;
			newtype = "d";
		}
		// 月
		else if (type.equals("4")) {
			newtype = "m";
		}
		// 旬，按照3个月计算
		else if (type.equals("5")) {
			iQuantity = iQuantity * 3;
			newtype = "m";
		}
		// 半年，按照六个月计算
		else if (type.equals("6")) {
			iQuantity = iQuantity * 6;
			newtype = "m";
		}
		// 年
		else if (type.equals("7")) {
			newtype = "y";
		} else {
			newtype = "d";
		}

		Date change = this.getDateChangeTime(curr, newtype, iQuantity);

		// if(!type.equals("d")){
		// change = this.getMonthEnd(change);
		// }

		return DateUtil.dateToStringShort(change);
	}

	/**
	 *
	 * @param currentTime
	 *            计算的日期
	 * @param type
	 *            偏移的类别
	 * @param iQuantity
	 *            偏移数量
	 * @return 偏移后的时间
	 */
	public Date getDateChangeTime(Date currentTime, String type, int iQuantity) {
		int year = Integer.parseInt(DateUtil.FormatDate(currentTime, "yyyy"));
		int month = Integer.parseInt(DateUtil.FormatDate(currentTime, "MM"));
		// 月份修正
		month = month - 1;
		int day = Integer.parseInt(DateUtil.FormatDate(currentTime, "dd"));
		int hour = Integer.parseInt(DateUtil.FormatDate(currentTime, "HH"));
		int mi = Integer.parseInt(DateUtil.FormatDate(currentTime, "mm"));
		int ss = Integer.parseInt(DateUtil.FormatDate(currentTime, "ss"));
		GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, mi, ss);
		// 月份修正
		// gc.add(GregorianCalendar.MONTH, -1);
		if (type.equalsIgnoreCase("y")) {
			gc.add(GregorianCalendar.YEAR, iQuantity);
		} else if (type.equalsIgnoreCase("m")) {
			gc.add(GregorianCalendar.MONTH, iQuantity);
		} else if (type.equalsIgnoreCase("d")) {
			gc.add(GregorianCalendar.DATE, iQuantity);
		} else if (type.equalsIgnoreCase("h")) {
			gc.add(GregorianCalendar.HOUR, iQuantity);
		} else if (type.equalsIgnoreCase("mi")) {
			gc.add(GregorianCalendar.MINUTE, iQuantity);
		} else if (type.equalsIgnoreCase("s")) {
			gc.add(GregorianCalendar.SECOND, iQuantity);
		}
		return gc.getTime();
	}

	/**
	 *
	 * @param currentTime
	 *            计算的日期
	 * @param type
	 *            偏移的类别
	 * @param iQuantity
	 *            偏移数量
	 * @return 偏移后的时间串
	 */
	public String getDateChangeTime(String currentTime, String type, int iQuantity) {
		Date curr = DateUtil.stringToDate(currentTime);
		curr = this.getDateChangeTime(curr, type, iQuantity);
		return DateUtil.dateToString(curr);
	}

	/**
	 * 取得日以上粒度起始时间
	 * 
	 * @param granularity
	 *            粒度
	 * @param statisticDate
	 *            结束时间
	 * @return 起始时间
	 */
	public String getEndDate(String granularity, String statisticDate) {
		String beginDate = "";
		Date date = DateUtil.stringToDateShort(statisticDate);
		Date beginDateTemp = null;

		if (granularity.equals("1")) {// 日
			beginDateTemp = date;
		}
		if (granularity.equals("2")) {// 周
			beginDateTemp = this.getWeekEnd(date);
		}
		if (granularity.equals("3")) {// 旬
			beginDateTemp = this.getPeriodEnd(date);
		} else if (granularity.equals("4")) {// 月
			beginDateTemp = this.getMonthEnd(date);
		} else if (granularity.equals("5")) {// 季
			beginDateTemp = this.getSeasonEnd(date);
		} else if (granularity.equals("6")) {// 半年
			beginDateTemp = this.getHalfYearEnd(date);
		} else if (granularity.equals("7")) {// 年
			beginDateTemp = this.getYearEnd(date);
		}

		beginDate = DateUtil.dateToStringShort(beginDateTemp);
		return beginDate;
	}

	/**
	 * 取半年初
	 * 
	 * @param date
	 * @return
	 */
	public Date getHalfYearBegin(Date date) {
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-";
		if (month <= 6) {
			newDateStr += "01-01";
		} else {
			newDateStr += "07-01";
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取半年末
	 * 
	 * @param date
	 * @return
	 */
	public Date getHalfYearEnd(Date date) {
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-";
		if (month <= 6) {
			newDateStr += "06-30";
		} else {
			newDateStr += "12-31";
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取月初
	 * 
	 * @param date
	 * @return
	 */
	public Date getMonthBegin(Date date) {
		String newDateStr = DateUtil.FormatDate(date, "yyyy-MM") + "-01";
		// FormatDate(date, "yyyy-MM-dd");
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取月末时间
	 * 
	 * @param date
	 *            日期
	 * @return date
	 */
	public static Date getMonthEnd(Date date) {
		int year = Integer.parseInt(DateUtil.FormatDate(date, "yyyy"));
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		int day = Integer.parseInt(DateUtil.FormatDate(date, "dd"));

		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
		int monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-" + DateUtil.FormatDate(date, "MM") + "-";
		if (monthLength < 10)
			newDateStr += "0" + monthLength;
		else
			newDateStr += "" + monthLength;
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取旬初
	 * 
	 * @param date
	 * @return
	 */
	public Date getPeriodBegin(Date date) {
		int days = Integer.parseInt(DateUtil.FormatDate(date, "dd"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy-MM") + "-";
		if (days <= 10) {
			newDateStr += "01";
		} else if (days <= 20) {
			newDateStr += "11";
		} else {
			newDateStr += "21";
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取旬末
	 * 
	 * @param date
	 * @return
	 */
	public Date getPeriodEnd(Date date) {
		int days = Integer.parseInt(DateUtil.FormatDate(date, "dd"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy-MM") + "-";
		if (days <= 10) {
			newDateStr += "10";
		} else if (days <= 20) {
			newDateStr += "20";
		} else {
			newDateStr = DateUtil.FormatDate(this.getMonthEnd(date), "yyyy-MM-dd");
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取季初
	 * 
	 * @param date
	 * @return
	 */
	public Date getSeasonBegin(Date date) {
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-";
		if (month >= 1 && month <= 3) {
			newDateStr += "01-01";
		} else if (month >= 4 && month <= 6) {
			newDateStr += "04-01";
		} else if (month >= 7 && month <= 9) {
			newDateStr += "07-01";
		} else if (month >= 10 && month <= 12) {
			newDateStr += "10-01";
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取季度末时间
	 * 
	 * @param date
	 *            日期
	 * @return date
	 */
	public Date getSeasonEnd(Date date) {
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-";
		if (month >= 1 && month <= 3) {
			newDateStr += "03-31";
		} else if (month >= 4 && month <= 6) {
			newDateStr += "06-30";
		} else if (month >= 7 && month <= 9) {
			newDateStr += "09-30";
		} else if (month >= 10 && month <= 12) {
			newDateStr += "12-31";
		}
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 取得时间描述 日 yyyy－mm－dd 月 yyyy年mm月 季 yyyy年第×季度 年 yyyy年
	 * 
	 * @param granularity
	 *            粒度
	 * @param statisticDate
	 *            时间
	 * @return 时间对应粒度的描述
	 */
	public String getTimedes(String granularity, String statisticDate) {
		String timedes = "";
		Date date = DateUtil.stringToDateShort(statisticDate);
		String year = "", month = "01", day = "01";
		year = DateUtil.getYear(date);
		month = DateUtil.getMonth(date);
		day = DateUtil.getDay(date);
		if (granularity.equals("1")) {// 日
			timedes = statisticDate;
		} else if (granularity.equals("4")) {// 月
			timedes = year + "年" + month + "月";

		} else if (granularity.equals("8")) {// 季
			String quarter = month + "-" + day;
			if (quarter.equals("03-31")) {
				timedes = year + "年 第1季度";
			} else if (quarter.equals("06-30")) {
				timedes = year + "年 第2季度";
			} else if (quarter.equals("09-30")) {
				timedes = year + "年 第3季度";
			} else if (quarter.equals("12-31")) {
				timedes = year + "年 第4季度";
			}
		} else if (granularity.equals("32")) {// 年
			timedes = year + "年";
		}
		return timedes;
	}

	/**
	 * 取周初
	 * 
	 * @param date
	 * @return
	 */
	public Date getWeekBegin(Date date) {

		int year = Integer.parseInt(DateUtil.FormatDate(date, "yyyy"));
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		// 月份修正
		month = month - 1;
		int day = Integer.parseInt(DateUtil.FormatDate(date, "dd"));

		GregorianCalendar gc = new GregorianCalendar(year, month, day);

		int week = GregorianCalendar.DAY_OF_WEEK - 1;

		if (week == 0) {
			week = 7;
		}

		gc.add(GregorianCalendar.DATE, 0 - week + 1);

		return gc.getTime();
	}

	/**
	 * 取周末
	 * 
	 * @param date
	 * @return
	 */
	public Date getWeekEnd(Date date) {

		int year = Integer.parseInt(DateUtil.FormatDate(date, "yyyy"));
		int month = Integer.parseInt(DateUtil.FormatDate(date, "MM"));
		// 月份修正
		month = month - 1;
		int day = Integer.parseInt(DateUtil.FormatDate(date, "dd"));

		GregorianCalendar gc = new GregorianCalendar(year, month, day);

		int week = GregorianCalendar.DAY_OF_WEEK - 1;

		if (week == 0) {
			week = 7;
		}
		gc.add(GregorianCalendar.DATE, 7 - week);

		return gc.getTime();
	}

	/**
	 * 取得年初
	 * 
	 * @param date
	 * @return
	 */
	public Date getYearBegin(Date date) {
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-01-01";
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 是否为年末
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	public Date getYearEnd(Date date) {
		String newDateStr = DateUtil.FormatDate(date, "yyyy") + "-12-31";
		return DateUtil.stringToDateShort(newDateStr);
	}

	/**
	 * 是否为旬末
	 * 
	 * @param date
	 *            时间
	 * @return 是或否
	 */
	public boolean IsXperiodEnd(Date date) {

		boolean flag = false;

		String day = DateUtil.getDay(date);

		if (day.equalsIgnoreCase("10")) {
			flag = true;
		} else if (day.equalsIgnoreCase("20")) {
			flag = true;
		}
		// 月末不算旬末
		// else if( this.getMonthEnd(date).compareTo(date)==0 ){
		// flag = true;
		// }

		return flag;
	}

	/**
	 * 日期加N天
	 * 
	 * @param Sring
	 *            时间
	 * @return 加后的日期
	 */
	public static String addDay(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期加N天
	 * 
	 * @param Sring
	 *            时间
	 * @return 加后的日期
	 */
	public static String addDay2(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期加N天
	 * 
	 * @param Sring
	 *            时间
	 * @return 加后的日期
	 */
	public static String delDay(int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			// cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, -n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 往后延迟几年
	 * 
	 * @param n
	 * @return
	 */
	public static String delYear(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.YEAR, n);// 增加一天

		return sdf.format(cd.getTime());
	}

	/*
	 * 获取前N分钟时间
	 */
	public static String getLateMin(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -n);
		return sdf.format(c.getTime());
	}

//	/**
//	 * 获取日期所在月最后一天的日期
//	 * @param d
//	 * @return
//	 */
//	public static String getMaxMonthDate(Date d) {
//		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(d);
//		// calendar.add(Calendar.MONTH, -1);
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//		return dft.format(calendar.getTime());
//	}
	
	/**
	 * 获取两个日期相差月
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int diffTwoDateMth(Date beginDate,Date endDate) {
		int rt = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
			Calendar bef = Calendar.getInstance();
			Calendar aft = Calendar.getInstance();
			bef.setTime(sdf.parse(sdf.format(beginDate)));
			aft.setTime(sdf.parse(sdf.format(endDate)));
			int mth = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
			int ymth = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
			rt = mth+ymth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	/**
	 * 得到指定月的天数
	 * @param year
	 * @param month
	 * @return
	 */
    public static int getMonthLastDay(int year,int month){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR,year);
		a.set(Calendar.MONTH,month-1);
		a.set(Calendar.DATE,1);
		//把日期设置为当月第一天  
		a.roll(Calendar.DATE,-1);
		//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}


    /**
     * 获取两个年月之前的每个年月数
	 * @param minDate
	 *            最小时间 2015-01
	 * @param maxDate
	 *            最大时间 2015-10
	 * @return 日期集合 格式为 年-月
	 * 
	 * @throws Exception
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}
	
	/**	 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 *  @param date	 
	 *  @return	 */
	public static int getSeason(Date date) { 
		int season = 0; 	
		Calendar c = Calendar.getInstance();
		c.setTime(date);	
		int month = c.get(Calendar.MONTH);	
		switch (month) {	
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:		
		season = 1;	
		break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
		season = 2;			
		break;		
		case Calendar.JULY:		
		case Calendar.AUGUST:		
		case Calendar.SEPTEMBER:			
		season = 3;			
	    break;		
	    case Calendar.OCTOBER:		
	    case Calendar.NOVEMBER:		
	    case Calendar.DECEMBER:			
	    season = 4;			
	    break;		
	    default:			
	    break;		
	    }		
		return season;	
	}

	
}
