package cn.xiaoyu.dao.tally;

import cn.xiaoyu.entity.tally.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 13:50
 * @Description:
 */
@Mapper
public interface LoanMapper {

    //查询借贷记账
    List<Loan> list(Loan loan);
    //增加一笔借贷
    int append(Loan loan);
    //标记结算
    int settlement(@Param("id")int id,@Param("settlement") int settlement);
    //删除
    int delete(@Param("id")int id);
    //修改一笔借贷
    int update(Loan loan);
}
