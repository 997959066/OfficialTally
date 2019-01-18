package cn.xiaoyu.controller.system;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.system.Role;
/**
 * 描述:
 * @author 
 * 日期:2018-08-13
*/ 
@RestController
@RequestMapping("/role")
public class RoleController extends Base {

  

    @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
    public ResponseMessage listByPage(Role  role,Integer pageNo,Integer pageSize){
        try { 
        	PageBean<Role> result = roleService.listByPage(role,pageNo,pageSize);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //查询列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseMessage list(Role role){
        try { 
            List<Role> result = roleService.list(role);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
    //根据id查询
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessage get(Integer id){
        try { 
            Role role = roleService.get(id);
            return new ResponseMessage(MessageCode.SUCCESS, role);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //增加
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public ResponseMessage addRole(Role role){
        try { 
            roleService.append(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessage updateRole(Role role){
        try { 
            roleService.update(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delRole(Role role){
        try { 
            roleService.delete(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

