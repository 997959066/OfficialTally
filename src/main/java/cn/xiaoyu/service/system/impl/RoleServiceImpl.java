package cn.xiaoyu.service.system.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.CommonUtils;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.system.Role;
import cn.xiaoyu.service.system.RoleService;
import cn.xiaoyu.util.StringUtils;

/**
 * role Service类
 */
@Service
public class RoleServiceImpl extends Base implements RoleService {

	public PageBean<Role> listByPage(Role role, Integer pageNo, Integer pageSize) {
		CommonUtils.checkNull(new String[] { "pageNo", "pageSize" }, pageNo, pageSize);
		List<Role> result = roleMapper.list(role);
		PageHelper.startPage(pageNo, pageSize);
		PageBean<Role> pageData = new PageBean<Role>(pageNo, pageSize, result.size());
		pageData.setList(roleMapper.list(role));
		return pageData;
	}

	public int append(Role role) {
		return roleMapper.append(role);
	}

	@Transactional
	public int delete(Role role) {
	int	returnId=0;
		// 2018.12.22 rolefunction中的关联也要删除
		returnId=roleMapper.deleteFunctionByRoleId(role.getId());
				//删除对应校色role
				returnId=roleMapper.delete(role);
		return returnId;
	}

	public int update(Role role) {
		// 先删除角色对应功能
		roleMapper.deleteFunctionByRoleId(role.getId());
		// 增加对应功能
		List<String> functionIdList = StringUtils.stringList(role.getFunctionName(), ",");
		roleMapper.appendRoleFunction(role.getId(), functionIdList);
		// 修改role表对应信息
		return roleMapper.update(role);
	}

	public Role get(Integer id) {
		Role r = roleMapper.get(id);
		r.setFunctionName(roleMapper.functionByRoleId(id));
		r.setListFunctionByRoleId(menuMapper.list(null));
		return r;
	}

	public List<Role> list(Role role) {
		return roleMapper.list(role);
	}

	@Override
	public List<Role> listRoleByUserId(Integer userId) {
		return roleMapper.listRoleByUserId(userId);
	}

}
