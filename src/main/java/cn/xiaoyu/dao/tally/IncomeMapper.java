package cn.xiaoyu.dao.tally;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xiaoyu.entity.tally.Income;

/**
 * income Dao类
*/ 
@Mapper
public interface IncomeMapper{

    int append(Income income);

    int delete(int id);

    int update(Income income);

    //查询年收入详情
    List<Income> incomeDetails(Income income);
    //月纯收入返回 netIncome，month，year
    HashMap<String, Object> monthNetIncome(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("userId")Integer userId);
}

