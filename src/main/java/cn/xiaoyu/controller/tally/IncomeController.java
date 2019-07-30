package cn.xiaoyu.controller.tally;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Income;
import cn.xiaoyu.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 描述:
 *
 * @author xiaoyu.zhang
 * 日期:2018-08-09
 * 日期:2018-12-20 增加详情查询
 */
@RestController
@RequestMapping("/income")
@Api(value = "income", description = "收入")
public class IncomeController extends Base {

    @ApiOperation(value = "查询年收入详情", notes = "查询菜单列表")
    @GetMapping(value = "/incomeDetails")
    public ResponseMessage incomeDetails(Income income, Integer pageNo, Integer pageSize) {
        try {
            if (income.getYear() == null) {
                income.setYear(Integer.valueOf(DateUtil.getSysYear()));
            }
            PageBean<Income> result = incomeService.incomeDetails(income, pageNo, pageSize);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @PostMapping("/append")
    public ResponseMessage addIncome(Income income) {
        try {
            if (income.getUserId() == null) {
                income.setUserId(getUser() != null ? getUser().getId() : 1);
            }
            int i = incomeService.append(income);
            return new ResponseMessage(MessageCode.SUCCESS, i);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @PostMapping("/update")
    public ResponseMessage updateIncome(Income income) {
        try {
            incomeService.update(income);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @PostMapping("/delete")
    public ResponseMessage delIncome(int id) {
        try {
            incomeService.delete(id);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "纯收入", notes = "纯收入")
    @GetMapping(value = "/netIncome")
    public ResponseMessage netIncome(int year) {
        try {
            HashMap<String, Object> result = incomeService.netIncome(year, getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

