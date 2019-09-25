package cn.xiaoyu.util;

import cn.xiaoyu.common.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author xiaoyu.zhang 日期:2018-12-20 增加当前年方法
 */
public class DateUtil {

    /**
     * 获取当前年
     */
    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        return String.valueOf(date.get(Calendar.YEAR));
    }

    /**
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

}
