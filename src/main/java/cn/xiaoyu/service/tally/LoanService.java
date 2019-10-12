package cn.xiaoyu.service.tally;

import cn.xiaoyu.entity.tally.Loan;

import java.util.List;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 13:32
 * @Description:
 */
public interface LoanService {
    //查询借贷记账
    List<Loan> list(Loan loan);
    //增加一笔借贷
    int append(Loan loan);
    //标记结算
    int settlement(int id,int tag);
    //删除
    int delete(int tag);
    //修改一笔借贷
    int update(Loan loan);

}
