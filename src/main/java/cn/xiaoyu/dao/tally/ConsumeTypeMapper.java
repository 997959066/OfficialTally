package cn.xiaoyu.dao.tally;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import cn.xiaoyu.entity.tally.ConsumeType;

//2018.12.24增加
@Mapper
@Component
public interface ConsumeTypeMapper{

 
    //查询所有类型
    List<ConsumeType> list();
    
}

