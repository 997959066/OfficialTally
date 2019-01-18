package cn.xiaoyu.service.system.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.CommonUtils;
import cn.xiaoyu.common.MD5;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.system.Role;
import cn.xiaoyu.entity.system.User;
import cn.xiaoyu.service.system.UserService;

/**
 * user Service类
 */
@Service
public class UserServiceImpl extends Base implements UserService {
	
	//2018.12.23 增加MD5加密
	@Transactional
	public int append(User user) {
		int returnId = 0;
		user.setPassWord(MD5.md5(user.getPassWord()+xiaoyu_key));
		returnId = userMapper.append(user);
		if (returnId > 0) {
			returnId = user.getId().intValue();
			userMapper.appendUserRole(user.getRoleId().intValue(), returnId);
		}
		return returnId;
	}
	
	public int delete(User user) {
		userMapper.deleteUserRole(user.getId().intValue(),null);
		return userMapper.delete(user);
	}

	public int update(User user) { 
		userMapper.deleteUserRole(user.getBeforeRoleId().intValue(), user.getId().intValue());
	userMapper.appendUserRole(user.getRoleId().intValue(),user.getId().intValue());
		return userMapper.update(user);
	}

	public User get(User user) {
		User newUser = userMapper.get(user);
		newUser.setPassWord(null);
		List<Role> roleList = roleMapper.listRoleByUserId(user.getId());
		newUser.setRoleList(roleList);
		return newUser;
	}
	public User getUser(User user) {
		return userMapper.getUser(user);
	}

	public PageBean<User> listByPage(User user, Integer pageNo, Integer pageSize) {
		CommonUtils.checkNull(new String[] { "pageNo", "pageSize" }, pageNo, pageSize);
		List<User> result = userMapper.list(user);
		PageHelper.startPage(pageNo, pageSize);
		PageBean<User> pageData = new PageBean<User>(pageNo, pageSize, result.size());
		pageData.setList(userMapper.list(user));
		return pageData;
	}

}
