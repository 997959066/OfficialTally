package cn.xiaoyu.service.tally.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.tally.Summary;
import cn.xiaoyu.service.tally.SummaryService;
import cn.xiaoyu.util.DateUtil;

/**
 * summary Service类
 */
@Service
public class SummaryServiceImpl extends Base implements SummaryService {

	@Override
	public List<Summary> list(int year,Integer userId) {
		Date date;
		List<Summary> yearSummary=null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(year+"-01-01");
			 yearSummary=summaryMapper.listByCountDate(date,userId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return yearSummary;
	}

	/**
	 * 年，日常消费 一天得消费数
	 */
	@Override
	public int dailyConsumptionYear(Integer year,Integer userId) {
		 Calendar date = Calendar.getInstance();
         year =  date.get(Calendar.YEAR);
		int returnId = 0;
		for (int month = 1; month <= 12; month++) {
			returnId = this.dailyConsumptionMonth(year, month,userId);
		}
		return returnId;

	}

	/**
	 * 年，月 一个月得日常消费 一天得消费数
	 */
	public int dailyConsumptionMonth(Integer year, Integer month,Integer userId) {
		int returnId = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Date> map = DateUtil.betweenMonthDateTime(year, month);
		Date firstDate = null;
		Date lastDate = null;
		for (String key : map.keySet()) {
			Date value = map.get(key);
			firstDate = key.equals("firstDate") ? firstDate = value : firstDate;
			lastDate = key.equals("lastDate") ? lastDate = value : lastDate;
		}
		List<Date> findDates = findDates(firstDate, lastDate);
		for (Date date : findDates) {
			HashMap<String, Object> mapDaily = summaryMapper.theDailySum(sf.format(date), sf.format(date),userId);
			Summary summary = new Summary();
			Summary s = new Summary();
			s.setUserId(userId);
			if (mapDaily.get("theDailyConsumptionSum") != null) {
				if (mapDaily.get("createDate") != null) {
					Date createDate;
					String tcs = mapDaily.get("theDailyConsumptionSum").toString();
					BigDecimal expense = new BigDecimal(tcs);
					summary.setExpense(expense);
					try {
						createDate = sf.parse(mapDaily.get("createDate").toString());
						summary.setCountDate(createDate);
						s.setCountDate(createDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				Summary su = summaryMapper.get(s);
				summary.setUserId(userId);
				if (su != null) {
					summary.setId(su.getId());
					returnId = summaryMapper.update(summary);
				} else {
					returnId = summaryMapper.append(summary);
				}
			}
		}
		return returnId;
	}

	/**
	 * 获取时间端日期，时间端内日期list集合
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

	/**
	 * 月度综合报表（月收入，月支出，月每日平均消费）
	 */
	@Override
	public HashMap<String, List<String>> monthlyComprehensive(Integer year,Integer userId) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();

		List<String> monthStrlist = new ArrayList<String>();
		List<String> moneyStrlist = new ArrayList<String>();
		List<String> spendingStrlist = new ArrayList<String>();
		List<String> averageStrlist = new ArrayList<String>();
		for (int m = 1; m <= 12; m++) {
			Map<String, Date> beMoTime = DateUtil.betweenMonthDateTime(year, m);
			Date time = beMoTime.get("firstDate");
			LocalDate localDate = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			// java.time.LocalDate -> java.sql.Date
			Date newDate = java.sql.Date.valueOf(localDate);
			HashMap<String, Object> mapObject = summaryMapper.monthlyComprehensive(newDate,userId);
			if (mapObject != null) {
				String month = mapObject.get("month") != null ? mapObject.get("month").toString() : "0"; // 月
				String money = mapObject.get("money") != null ? mapObject.get("money").toString() : "0";
				String spending = mapObject.get("spending") != null ? mapObject.get("spending").toString() : "0"; // 支出
				String average = mapObject.get("average") != null ? mapObject.get("average").toString() : "0"; // 平均
				monthStrlist.add(month);
				moneyStrlist.add(money);
				spendingStrlist.add(spending);
				averageStrlist.add(average);
			}
		}
		map.put("month", monthStrlist);
		map.put("money", moneyStrlist);
		map.put("spending", spendingStrlist);
		map.put("average", averageStrlist);
		return map;
	}
	/**
	 *  季度综合报表
	 * （季节总消费， 模糊查询 房租，服装，餐饮 +消费统计）
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Object> seasonComprehensive(int year,Integer userId) {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH); //当前季节
        String j="";
            if (month >= 3 && month <= 5)
            	j="春季";
                else if (month >= 6 && month <= 8)
                	j="夏季";
                else if (month >= 9 && month <= 11)
                	j="秋季";
                else if (month >= 12 && month <= 2)
                	j="冬季";
		HashMap<String, Object> m=new HashMap<>();
		List<Map> mapList=new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
				BigDecimal oneTimeSum =summaryMapper.timeSum(sdf.parse(year+"-03-01 00:00:00"), sdf.parse(year+"-05-31 59:59:59"),userId);
				BigDecimal twoTimeSum =summaryMapper.timeSum(sdf.parse(year+"-06-01 00:00:00"), sdf.parse(year+"-08-31 59:59:59"),userId);
				BigDecimal threeTimeSum =summaryMapper.timeSum(sdf.parse(year+"-09-01 00:00:00"), sdf.parse(year+"-11-31 59:59:59"),userId);
																										//增加到下一年按照中国的纬度
				BigDecimal fourTimeSum =summaryMapper.timeSum(sdf.parse(year-1+"-12-01 00:00:00"), sdf.parse((year)+"-2-28 59:59:59"),userId);
				Map map1=new HashMap();
				Map map2=new HashMap();
				Map map3=new HashMap();
				Map map4=new HashMap(); 
				switch (j) {//配合扇形选择当前季节
				case "春季":
					map1.put("selected", true);
					break;
				case "夏季":
					map2.put("selected", true);
					break;
				case "秋季":
					map3.put("selected", true);
					break;
				case "冬季":
					map4.put("selected", true);
					break;
				}
				map1.put("name", "春季");
				map2.put("name", "夏季");
				map3.put("name", "秋季");
				map4.put("name", "冬季");
				map1.put("value", oneTimeSum!=null?oneTimeSum.toString():"0");
				map2.put("value", twoTimeSum!=null?twoTimeSum.toString():"0");
				map3.put("value", threeTimeSum!=null?threeTimeSum.toString():"0");
				map4.put("value", fourTimeSum!=null?fourTimeSum.toString():"0");
				mapList.add(map1);
				mapList.add(map2);
				mapList.add(map3);
				mapList.add(map4);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        m.put("q", mapList);//季节
		return m;
	}

	@Override
	public HashMap<String, Object> annualWeeklyReport(Integer userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String,Object> map=new HashMap<String, Object>();
		HashMap<String,Date> hashMap=this.getLastTimeInterval();
		List<Summary> sll=new ArrayList<>();
		System.out.println(sdf.format(hashMap.get("startTime")));
		System.out.println(sdf.format(hashMap.get("endTime")));
		List<Summary> sl=summaryMapper.list(hashMap.get("startTime"), hashMap.get("endTime"),userId);

		BigDecimal totalAmount = new BigDecimal(0);
		for (Summary summary : sl) {
			totalAmount = totalAmount.add(summary.getExpense());
			System.out.println(totalAmount);
			Summary su=new Summary(); 
			su.setName(sdf.format(summary.getCountDate()));
			su.setValue(summary.getExpense().toString());
			sll.add(su);
		}
		map.put("s", "上周总消费："+totalAmount);
		map.put("w", sll);
		return map;
	}
	
	//上周的开始时间or结束时间
	   public HashMap<String,Date> getLastTimeInterval() {  
		   HashMap<String,Date> map=new HashMap<String, Date>();
	         Calendar calendar1 = Calendar.getInstance();  
	         Calendar calendar2 = Calendar.getInstance();  
	         int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
	         int offset1 = 1 - dayOfWeek;  
	         int offset2 = 7 - dayOfWeek;  
	         calendar1.add(Calendar.DATE, offset1 - 7);  
	         calendar2.add(Calendar.DATE, offset2 - 7);  
	         map.put("startTime", calendar1.getTime());
	         map.put("endTime", calendar2.getTime());
	         return map;  
	    }
}
