package cn.xiaoyu.dao.system;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.xiaoyu.entity.system.Role;

/**
 * role Dao类
*/ 
@Mapper
public interface RoleMapper{
	//增加角色表信息
    int append(Role role);
    //删除角色表role中对应信息
    int delete(Role role);
    //修改角色表role
    int update(Role role);
    //获取校色信息
    Role get(Integer id);
   
    List<Role> list(Role role);
    /**查询用户不在的角色 */
    List<Role> listRoleByUserId(@Param("userId") Integer userId);
	/*** 增加角色功能（操作rolefuntion表） */
	int appendRoleFunction(@Param("roleId") Integer roleId,@Param("functionIdList") List<String> functionIdList);
	
	/*** 根据角色返回当前角色已有功能id（操作rolefuntion表*/
	String functionByRoleId(@Param("roleId") Integer roleId);
	/*** 删除当前角色所有功能（操作rolefuntion表）*/
	int deleteFunctionByRoleId(@Param("roleId") Integer roleId);

}

