package cn.xiaoyu.service.system.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.system.Menu;
import cn.xiaoyu.service.system.MenuService;
/**
 * menu Service类
*/ 
@Service
public class MenuServiceImpl extends Base  implements MenuService{
    public int append(Menu menu){
            return menuMapper.append(menu);
    }

    public int delete(Menu menu){
            return menuMapper.delete(menu);
    }

    public int update(Menu menu){
            return menuMapper.update(menu);
    }

    public Menu selectById(Integer id){
            return menuMapper.selectByID(id);
    }

    public List<Menu> list(Menu menu){
            return menuMapper.list(menu);
    }

	@Override
	public List<Menu> listFunction(int userId) {
		return menuMapper.listFunction(userId);
	}

    

}

