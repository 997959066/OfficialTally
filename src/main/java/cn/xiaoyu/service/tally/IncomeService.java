package cn.xiaoyu.service.tally;
import java.util.HashMap;
import javax.script.ScriptException;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.tally.Income;
/**
* income Service类
*/ 
public interface IncomeService{

	//增加收入
    int append(Income income);
    int delete(int id );
    int update(Income income);
    //查询年收入详情
    PageBean<Income> incomeDetails(Income income,Integer pageNo,Integer pageSize);
    //纯收入
    HashMap<String, Object> netIncome(int year,int userId)throws ScriptException ;
}

