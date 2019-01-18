package cn.xiaoyu.entity.tally;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
 
@Getter   //2018.12.24 添加 get set注解  lombok 
@Setter
public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	// 
    private Integer id;
    // 年
    private Integer year;
    // 月
    private Integer month;
    // 收入
    private BigDecimal money;
    // 来源
    private String source;
    // 建立时间
    private String createDate;
    // 修改时间
    private String updateDate;
    
    private  Integer userId;
    
}

