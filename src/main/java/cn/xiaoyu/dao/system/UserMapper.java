package cn.xiaoyu.dao.system;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import cn.xiaoyu.entity.system.User;

/**
 * user Dao类
*/ 
@Mapper
public interface UserMapper{

    int append(User user);
    @Options(useGeneratedKeys = false)
    
    int appendUserRole(@Param("roleId") Integer roleId,@Param("userId") Integer userId);

    int deleteUserRole(@Param("roleId") Integer roleId,@Param("userId") Integer userId);
    int delete(User user);

    int update(User user);

    User get(User user);
    User getUser(User user);

    List<User> list(User user);

}

