package cn.xiaoyu.controller.tally;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 日期:2018-12-24
*/ 
@Api(value = "consumeType", description = "分类下拉")
@RestController
@RequestMapping("/consumeType")
public class ConsumeTypeController extends Base {

	     @ApiOperation(value = "查询下拉", notes = "查询下拉")
		 @GetMapping(value = "/list")
	     public ResponseMessage list(){
	     	try { 
	     		return new ResponseMessage(MessageCode.SUCCESS,consumeTypeService.list());
	     	} catch (Exception e){ 
	     		return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
	     	}
	     }
   
}

