package cn.xiaoyu.entity.tally;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: JMSH9440
 * @Date: 2019/10/12 13:34
 * @Description:
 */
@Getter
@Setter
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Integer id;
    // 收入
    private BigDecimal money;
    // 名字
    private String name;
    //备忘记录
    private String record;
    //方式  0 借出  1 借进'
    private Integer mode;
    //结算  0 未结算  1 已结算'
    private Integer settlement;
    //用户
    private Integer userId;
    // 建立时间
    private String createDate;
    // 修改时间
    private String updateDate;

}
