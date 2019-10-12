package cn.xiaoyu.controller.tally;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.tally.Loan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 13:25
 * @Description:
 */
@RestController
@RequestMapping("/loan")
@Api(value = "loan", description = "借贷")
public class LoanController extends Base {

    @ApiOperation(value = "查询借贷列表", notes = "查询借贷列表")
    @GetMapping(value = "/list")
    public ResponseMessage list(Loan loan) {
        try {
            List<Loan> result = loanService.list(loan);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }


    //增加
    @ApiOperation(value = "增加一笔记账", notes = "增加一笔记账")
    @PostMapping("/append")
    public ResponseMessage append(Loan loan) {
        try {
            loan.setUserId(getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, loanService.append(loan));
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("/update")
    public ResponseMessage update(Loan loan) {
        try {
            loan.setUserId(getUser().getId());
            return new ResponseMessage(MessageCode.SUCCESS, loanService.update(loan));
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "标记结算", notes = "标记结算")
    @PostMapping("/settlement")
    public ResponseMessage settlement(int id, int tag) {
        try {
            return new ResponseMessage(MessageCode.SUCCESS, loanService.settlement(id, tag));
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @ApiOperation(value = "删除借款", notes = "删除借款")
    @PostMapping("/delete")
    public ResponseMessage delete(Integer id) {
        try {
            loanService.delete(id);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
}
