package cn.xiaoyu.service.tally.impl;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.tally.Loan;
import cn.xiaoyu.service.tally.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 13:33
 * @Description:
 */
@Service
public class LoanServiceImpl extends Base implements LoanService {
    @Override
    public List<Loan> list(Loan loan) {
        return loanMapper.list(loan);
    }

    @Override
    public int append(Loan loan) {
        return loanMapper.append(loan);
    }

    @Override
    public int settlement(int id, int tag) {
        return loanMapper.settlement(id, tag);
    }

    @Override
    public int delete(int tag) {
        return loanMapper.delete(tag);
    }

    @Override
    public int update(Loan loan) {
        return loanMapper.update(loan);
    }
}
