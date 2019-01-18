package cn.xiaoyu.entity.tally;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.xiaoyu.common.CustomDateSerializer;
import lombok.Getter;
import lombok.Setter;
@Getter   //2018.12.24 添加 get set注解  lombok 
@Setter
public class Summary implements Serializable{
	
	private static final long serialVersionUID = 4896119463330994673L;
	    private Integer id;
	    // 统计日期
	    @Value("${countDate}")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date countDate;
	    // 支出
	    private BigDecimal expense;
	    // 建立时间
	    private String createDate;
	    // 修改时间
	    private String updateDate;
	    
	    private String name;  //参数接收
	    
	    private String value;  //参数接收
	    
	    private Integer userId;
	    
	    public void setCountDate(Date countDate){
	    this.countDate=countDate;
	    }
	    //此处返回 在CustomDateSerializer中转成String
	    @JsonSerialize(using = CustomDateSerializer.class)
	    public Date getCountDate(){
	        return countDate;
	    }
	    
}

