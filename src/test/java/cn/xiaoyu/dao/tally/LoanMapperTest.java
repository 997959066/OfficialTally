package cn.xiaoyu.dao.tally;

import cn.xiaoyu.Startup;
import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.tally.Loan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 14:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class LoanMapperTest extends Base {

    @Test
    public void list() {
        Loan loan = new Loan();
//        loan.setName("测试名字");
//        loan.setMode(1);
//        loan.setSettlement(1);
        List<Loan> list = loanMapper.list(loan);
        System.out.println(list);
    }

    @Test
    public void append() {
        Loan loan = new Loan();
        loan.setName("测试名字");
        loan.setMoney(new BigDecimal(200));
        loan.setMode(1);
        loan.setRecord("测试备注");
        loan.setSettlement(1);
        loan.setUserId(1);
        int isOk = loanMapper.append(loan);
        System.out.println(isOk);

    }

    @Test
    public void settlement() {
        int isOk = loanMapper.settlement(19, 0);
        System.out.println(isOk);
    }

    @Test
    public void delete() {
        int isOk = loanMapper.delete(20);
        System.out.println(isOk);
    }

    @Test
    public void update() {
        Loan loan = new Loan();
        loan.setId(19);
        loan.setName("测试名字update");
        loan.setMoney(new BigDecimal(2900));
        loan.setMode(0);
        loan.setRecord("测试备注update");
        loan.setSettlement(0);
        loan.setUserId(2);
        int isOk = loanMapper.update(loan);
        System.out.println(isOk);
    }
}