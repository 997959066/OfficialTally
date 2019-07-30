package cn.xiaoyu.controller.tally;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.DefaultException;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Tally;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 描述:记账信息
 * 日期:2018-06-02
*/ 
@Api(value = "tally", description = "记账信息")
@RestController
@RequestMapping("/tally")
public class TallyController extends Base {

	     @ApiOperation(value = "查询列表记账分页", notes = "分页")
		 @GetMapping(value = "/listByPage")
	     public ResponseMessage listByPage(Tally tally,Integer pageNo,Integer pageSize){
	     	try { 
	     		//获取对象
	     		tally.setUserId(getUser().getId());
	     		return new ResponseMessage(MessageCode.SUCCESS, tallyService.listByPage(tally,pageNo,pageSize));
	     	} catch (Exception e){ 
	     		return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
	     	}
	     }
	

    @ApiOperation(value = "查询列表记账", notes = "查询列表记账")
	@GetMapping(value = "/list")
    public ResponseMessage list(Tally tally){
        try { 
            List<Tally> result = tallyService.list(tally);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
    

    //增加
    @ApiOperation(value = "增加一笔记账", notes = "增加一笔记账")
	@PostMapping("/append")
    public ResponseMessage addTally(Tally tally){
        try { 
        	tally.setUserId(getUser().getId());
        	tallyService.append(tally);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @ApiOperation(value = "修改记账", notes = "修改记账")
	@PostMapping("/update")
    public ResponseMessage updateTally(Tally tally){
        try { 
        	tally.setUserId(getUser().getId());
        	tallyService.update(tally);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @ApiOperation(value = "删除记账", notes = "删除记账")
	@PostMapping("/delete")
    public ResponseMessage delTally(Integer id){
        try { 
        	tallyService.delete(id,getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "一段时间消费总钱数", notes = "一段时间消费总钱数")
	@GetMapping(value = "/theTotalAmount")
    public ResponseMessage theTotalAmount(String startTime,String endTime,String used){
        try { 
        	Tally amount =tallyService.theTotalAmount(startTime,endTime,used,getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS,amount);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
    @ApiOperation(value = "每月消费总钱数", notes = "每月消费总钱数")
	@GetMapping(value = "/theMonthlyConsumption")
    public ResponseMessage theMonthlyConsumption(int year){
    	try { 
    		List<HashMap<String, Object>> theMonthlyConsumption =tallyService.theMonthlyConsumption(getUser().getId(),year);
    		return new ResponseMessage(MessageCode.SUCCESS,theMonthlyConsumption);
    	} catch (Exception e){ 
    		return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
    	}
    }
     
    @ApiOperation(value = "全部信息导出Execl", notes = "导出明细消费")
	@GetMapping(value = "/yearAllExport")
    public void allExport(HttpServletResponse response){
    	try { 
    		tallyService.yearAllExport(getUser().getId(),response);
    	} catch (DefaultException e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("导出动态基础信息Execl失败", e.getMessage(), e);
    		}
    		out(response, e.getMessage());
    	} catch (Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("导出动态基础信息Execl失败", e.getMessage(), e);
    		}
    		out(response, "导出动态基础信息Execl失败！");
    	}
    }
    
    
	@ApiOperation(value = "导入消费Execl", notes = "导入消费Execl")
	@PostMapping("/importData")
	public ResponseMessage importData(HttpServletRequest request)
			throws IOException, ServletException {
		try {
				return responseMessage(MessageCode.SUCCESS,tallyService.importData(getUser().getId(),request));
		} catch (DefaultException e) {
            return responseMessage(e.getMessage());
		} catch (Exception e) {
			 return responseMessage(MessageCode.UNKNOWN_ERROR.getCode(),"导入Execl数据失败!");
		}
	}
    
	
	   @ApiOperation(value = "每月消费分类", notes = "消费分类")
	   @GetMapping(value = "/categoricalConsumption")
	    public ResponseMessage categoricalConsumption(int year,int month){
	    	try { 
	    		HashMap<String,Object> categoricalConsumption =tallyService.categoricalConsumption( year, month);
	    		return new ResponseMessage(MessageCode.SUCCESS,categoricalConsumption);
	    	} catch (Exception e){ 
	    		return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
	    	}
	    }
	   @ApiOperation(value = "年分类消费统计", notes = "年分类消费统计")
	   @GetMapping(value = "/yearCateInfo")
	   public ResponseMessage yearCateInfo(int year){
		   try { 
			   HashMap<String,Object> yearCateInfo =tallyService.yearCateInfo( year);
			   return new ResponseMessage(MessageCode.SUCCESS,yearCateInfo);
		   } catch (Exception e){ 
			   return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		   }
	   }
}

