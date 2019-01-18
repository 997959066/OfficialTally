package cn.xiaoyu.controller.tally;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Summary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 描述:
 * @author 
 * 日期:2018-08-07
 * 日期:2018-12-20 删掉没用接口
*/ 
@Api(value = "summary", description = "汇总信息")
@RestController
@RequestMapping("/summary")
public class SummaryController extends Base {

	
	@ApiOperation(value = "年度报表每日消费总和数据", notes = "年度报表每日消费总和数据")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseMessage list(int year){
		try { 
			List<Summary> list=summaryService.list(year,getUser().getId());
			return new ResponseMessage(MessageCode.SUCCESS,list);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	@ApiOperation(value = "季节综合报表", notes = "（季节总消费， 模糊查询 房租，服装，餐饮 +消费统计）")
	@RequestMapping(value = "/seasonComprehensive", method = RequestMethod.GET)
	public ResponseMessage seasonComprehensive(int year){
		try { 
			HashMap<String,Object> list= summaryService.seasonComprehensive(year,getUser().getId());
			return new ResponseMessage(MessageCode.SUCCESS,list);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "月度综合报表", notes = "（月收入，月支出，月平均消费）")
	@RequestMapping(value = "/monthlyComprehensive", method = RequestMethod.GET)
	public ResponseMessage monthlyComprehensive(int year){
		try { 
			HashMap<String,List<String>> returnMap= summaryService.monthlyComprehensive(year,getUser().getId());
			return new ResponseMessage(MessageCode.SUCCESS,returnMap);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	@ApiOperation(value = "周综合报表", notes = "（月收入，月支出，月平均消费）")
	@RequestMapping(value = "/annualWeeklyReport", method = RequestMethod.GET)
	public ResponseMessage annualWeeklyReport(){
		try { 
			HashMap<String,Object> returnMap= summaryService.annualWeeklyReport(getUser().getId());
			return new ResponseMessage(MessageCode.SUCCESS,returnMap);
		} catch (Exception e){ 
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
    
}

