package cn.xiaoyu.entity.tally;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter   //2018.12.24 添加 get set注解  lombok 
@Setter
public class Tally implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    // 建立时间
    private String createDate;
    // 用途
    private String used;
    // 多少钱
    private BigDecimal howMuch;
    
    private Integer userId;
    //----------------------------------
    private String startDate;
    private String endDate;
    
    private Integer month; //接受参数  月数
    private String typeName; //类型名称
    private Integer type; //类型名称
    
}

