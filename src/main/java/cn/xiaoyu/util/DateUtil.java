package cn.xiaoyu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xiaoyu.common.Constants;

/**
 * 描述:
 * 
 * @author xiaoyu.zhang 日期:2018-12-20 增加当前年方法
 */
public class DateUtil {

	/** * 获取当前年 */
	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	/**
	 *
	 * Description: 添加方法功能描述 比较时间
	 * 
	 * @param source
	 * @param current
	 * @return Boolean
	 * @throws ParseException
	 */
	public static Boolean compareTimeOut(String source, String current) throws ParseException {
		Calendar cal = (Calendar) Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_FORMAT_PATTEN);
		cal.setTime(sdf.parse(source));
		cal.add(Calendar.MINUTE, 5);
		return cal.getTime().before(sdf.parse(current));
	}

	/**
	 * 获取系统时间
	 */
	public static String getSysDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 当前年：输入月数返回月开始时间和结束时间 Map<String, String>
	 */
	public static Map<String, String> betweenMonthTime(Integer year, Integer month) {
		Map<String, String> map = new HashMap<>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		map.put("startTime", sf.format(firstDate.getTime()));
		map.put("endTime", sf.format(lastDate.getTime()));
		return map;
	}

	/**
	 * 输入年和月 返回月开始时间和结束时间
	 * 
	 */
	public static Map<String, Date> betweenMonthDateTime(Integer year, Integer month) {
		Map<String, Date> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		map.put("firstDate", firstDate);
		map.put("lastDate", lastDate);
		return map;
	}

	/**
	 * 获取当前年 第一天后最后一天
	 * 
	 */
	public static HashMap<String, String> yearFirstAndLastDay() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, String> map = new HashMap<String, String>();
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.DAY_OF_YEAR, 1);
		// 第一天
		String firstDay = sf.format(c.getTime());
		map.put("firstDay", firstDay);
		c.add(Calendar.YEAR, 1);
		c.set(Calendar.DAY_OF_YEAR, -1);
		// 最后一天
		String lastDay = sf.format(c.getTime());
		map.put("lastDay", lastDay);
		return map;
	}

	/**
	 * 获取时间端日期
	 * 
	 */
	public static List<Date> findDates(Date firstDate, Date lastDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(firstDate);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(firstDate);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(lastDate);
		// 测试此日期是否在指定日期之后
		while (lastDate.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

}
