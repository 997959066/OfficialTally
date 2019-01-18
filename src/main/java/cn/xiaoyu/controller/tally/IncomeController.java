package cn.xiaoyu.controller.tally;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Income;
import cn.xiaoyu.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 描述:
 * @author  xiaoyu.zhang
 * 日期:2018-08-09
 * 日期:2018-12-20 增加详情查询
*/ 
@RestController
@RequestMapping("/income")
@Api(value = "income", description = "收入")
public class IncomeController extends Base {

	@ApiOperation(value = "查询年收入详情", notes = "查询菜单列表")
	@RequestMapping(value = "/incomeDetails", method = RequestMethod.GET)
	public ResponseMessage incomeDetails(Income income,Integer pageNo,Integer pageSize){
		try { 
			if(income.getYear() == null){
				income.setYear(Integer.valueOf(DateUtil.getSysYear()));
			}
			PageBean<Income> result = incomeService.incomeDetails(income,pageNo,pageSize);
			return new ResponseMessage(MessageCode.SUCCESS, result);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	 
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public ResponseMessage addIncome(Income income){
        try { 
        	if(income.getUserId() == null){
        		income.setUserId(getUser()!= null?getUser().getId(): 1);
        	}
        	int i=incomeService.append(income);
            return new ResponseMessage(MessageCode.SUCCESS,i);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessage updateIncome(Income income){
        try { 
            incomeService.update(income);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delIncome(int id){
        try { 
            incomeService.delete(id);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
    
	@ApiOperation(value = "纯收入", notes = "纯收入")
	@RequestMapping(value = "/netIncome", method = RequestMethod.GET)
	public ResponseMessage netIncome(int year){
		try {
			 HashMap<String, Object>  result = incomeService.netIncome( year,getUser().getId());
			return new ResponseMessage(MessageCode.SUCCESS, result);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

}

