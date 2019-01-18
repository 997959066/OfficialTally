package cn.xiaoyu.service.system;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.system.User;
/**
* user Service类
*/ 
public interface UserService{
	//注册用户 2018.12.23
    int append(User user);
    
    int delete(User user);
    int update(User user);
    User get(User user);
    User getUser(User user);
    PageBean<User> listByPage(User  user,Integer pageNo,Integer pageSize);
}

