package cn.xiaoyu.service.tally;

import java.util.HashMap;
import java.util.List;

import cn.xiaoyu.entity.tally.Summary;

/**
 * summary Service类
 */
public interface SummaryService {

	// 消费统计
	public int dailyConsumptionYear(Integer year,Integer userId);

	List<Summary> list(int year,Integer userId);

	/**
	 * 月度综合报表（月收入，月支出，月每日平均消费）
	 */
	public HashMap<String, List<String>> monthlyComprehensive(Integer year,Integer userId);

	/**
	 * 季度综合报表 （季节总消费， 模糊查询 房租，服装，餐饮 +消费统计）
	 */
	public HashMap<String,Object> seasonComprehensive(int year,Integer userId);
	
	/**
	 * 周综合报表  （暂时只写上周消费）
	 */
	HashMap<String,Object>   annualWeeklyReport(Integer userId);
}
