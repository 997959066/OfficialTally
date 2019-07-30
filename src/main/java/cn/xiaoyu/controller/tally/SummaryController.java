package cn.xiaoyu.controller.tally;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Summary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 *
 * @author 日期:2018-08-07
 * 日期:2018-12-20 删掉没用接口
 */
@Api(value = "summary", description = "汇总信息")
@RestController
@RequestMapping("/summary")
public class SummaryController extends Base {


    @ApiOperation(value = "年度报表每日消费总和数据", notes = "年度报表每日消费总和数据")
    @GetMapping(value = "/list")
    public ResponseMessage list(int year) {
        try {
            List<Summary> list = summaryService.list(year, getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, list);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "季节综合报表", notes = "（季节总消费， 模糊查询 房租，服装，餐饮 +消费统计）")
    @GetMapping(value = "/seasonComprehensive")
    public ResponseMessage seasonComprehensive(int year) {
        try {
            HashMap<String, Object> list = summaryService.seasonComprehensive(year, getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, list);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "月度综合报表", notes = "（月收入，月支出，月平均消费）")
    @GetMapping(value = "/monthlyComprehensive")
    public ResponseMessage monthlyComprehensive(int year) {
        try {
            HashMap<String, List<String>> returnMap = summaryService.monthlyComprehensive(year, getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, returnMap);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "周综合报表", notes = "（月收入，月支出，月平均消费）")
    @GetMapping(value = "/annualWeeklyReport")
    public ResponseMessage annualWeeklyReport() {
        try {
            HashMap<String, Object> returnMap = summaryService.annualWeeklyReport(getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, returnMap);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

