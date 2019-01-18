package cn.xiaoyu.entity.tally;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter   //2018.12.24 添加 get set注解  lombok 
@Setter
public class ConsumeType implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String typeName;  //类型名字
    
}

