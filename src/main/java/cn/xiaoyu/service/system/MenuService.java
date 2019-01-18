package cn.xiaoyu.service.system;
import java.util.List;


import cn.xiaoyu.entity.system.Menu;
/**
* menu Service类
*/ 
public interface MenuService{

    int append(Menu menu);
    int delete(Menu menu);
    int update(Menu menu);
    Menu selectById(Integer id);
    List<Menu> list(Menu  menu);
    List<Menu> listFunction(int  userId);
}

