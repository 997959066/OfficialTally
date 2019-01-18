package cn.xiaoyu.dao.tally;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xiaoyu.entity.tally.Summary;

/**
 * summary Dao类
*/ 
@Mapper
public interface SummaryMapper{

	//增加
    int append(Summary summary);

    //没有引用
    int delete(Summary summary);

    int update(Summary summary);

    //时间段 内的消费总钱数
    HashMap<String, Object> theDailySum(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("userId")Integer userId);
    
    Summary get(Summary summary);
    /**
     * 月度综合报表（月支出，月每日平均消费）
     * 
     * time每月第一天
     * */
    HashMap<String, Object> monthlyComprehensive(@Param("time") Date time,@Param("userId")Integer userId);
    /**
     * 按年查询每日数据报表统计
     * */
    List<Summary> listByCountDate(@Param("time") Date time,@Param("userId")Integer userId);
	/**
	  *一段时间消费
	 */
    BigDecimal timeSum (@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("userId")Integer userId);
    
    //时间端 数据集合
    List<Summary> list(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("userId")Integer userId);
}

