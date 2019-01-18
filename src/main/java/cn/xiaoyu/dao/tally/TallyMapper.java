package cn.xiaoyu.dao.tally;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.xiaoyu.entity.tally.Tally;

/**
 * tally Dao类
*/ 
@Mapper
@Component
public interface TallyMapper{

	//增加
    int append(Tally tally);
    //删除
    int delete(@Param("id")Integer id,@Param("userId")Integer userId);
    //修改
    int update(Tally tally);
    //查询记账列表
    List<Tally> list(Tally tally);
    //一段时间数据
    Tally theTotalAmount(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("used")String used,
    		@Param("userId")Integer userId);
	
    //2018.12.25查询一段时间分类钱数
    List<Tally> categoricalConsumption(@Param("startTime")String startTime,@Param("endTime")String endTime, 
    		@Param("userId")Integer userId
    		,@Param("type")Integer type);
}

