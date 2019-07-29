package cn.xiaoyu.service.system;
import java.util.List;

import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.system.Role;
/**
* role Service类
*/ 
public interface RoleService{

    int append(Role role);
    int delete(Role role);
    int update(Role role);
    Role get(Integer id);
    List<Role> list(Role  role);
    
    //查询用户不在的角色
    List<Role> listRoleByUserId( Integer userId);
    
     PageBean<Role> listByPage(Role role,Integer pageNo,Integer pageSize);

}

