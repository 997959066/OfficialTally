package cn.xiaoyu.dao.system;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xiaoyu.entity.system.Menu;

/**
 * menu Dao类
*/ 
@Mapper
public interface MenuMapper{

    int append(Menu menu);

    int delete(Menu menu);

    int update(Menu menu);

    Menu selectByID(Integer id);
    /***返回系统所有功能列表*/
    List<Menu> list(Menu menu);
    
    //    <!-- 用户ID 查询菜单 -->
    List<Menu> listFunction(@Param("userId")int  userId);

}

