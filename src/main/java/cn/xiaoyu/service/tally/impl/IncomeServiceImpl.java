package cn.xiaoyu.service.tally.impl;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.CommonUtils;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.tally.Income;
import cn.xiaoyu.service.tally.IncomeService;
import cn.xiaoyu.util.DateUtil;
/**
 * income Service类
*/ 
@Service
public class IncomeServiceImpl extends Base  implements IncomeService{
    public int append(Income income){
            return incomeMapper.append(income);
    }

    public int delete(int id){
            return incomeMapper.delete( id);
    }

    public int update(Income income){
            return incomeMapper.update(income);
    }
   

	@Override
	public PageBean<Income> incomeDetails(Income income,Integer pageNo,Integer pageSize) {
		CommonUtils.checkNull(new String[] { "pageNo", "pageSize" }, pageNo, pageSize);
		List<Income> result = incomeMapper.incomeDetails(income);
		PageHelper.startPage(pageNo, pageSize);
		PageBean<Income> pageData = new PageBean<Income>(pageNo, pageSize, result.size());
		pageData.setList(incomeMapper.incomeDetails(income));
		return pageData;
	}

	@Override
	public HashMap<String, Object> netIncome(int year,int userId) throws ScriptException {
		HashMap<String, Object> ob=new HashMap<>();
		List<HashMap<String, String>> list=new ArrayList<>();
		String sum="0";
		for (int m = 1; m <= 12; m++){
			Map<String, Date> beMoTime = DateUtil.betweenMonthDateTime(year,m);
			String startTime=time(beMoTime.get("firstDate"));
			String endTime=time(beMoTime.get("lastDate"));
			HashMap<String, Object> map=incomeMapper.monthNetIncome(startTime, endTime,userId);
			HashMap<String, String> hm=new HashMap<>();
			hm.put("year", map.get("year")!=null?map.get("year").toString():"");
			String name=map.get("month")!=null?map.get("month").toString()+"月".toString():"";
			hm.put("name",name);
			String  netIncome=map.get("netIncome")!=null?map.get("netIncome").toString():"0";
			Object result=new ScriptEngineManager().getEngineByName("js").eval(netIncome + "+" + sum);
			sum=new DecimalFormat("######0.00").format(result);
			hm.put("value", netIncome);
			list.add(hm);
		}
		ob.put("list", list);//渲染12月利润
		ob.put("sum",  sum); //利润总和
		return ob;
	}

   public String time(Date time){
	   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(time.getTime());
   }

}

