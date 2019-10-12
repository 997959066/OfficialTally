package cn.xiaoyu.controller.system;
import java.util.List;

import org.springframework.web.bind.annotation.*;

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

  


    @GetMapping(value = "/listByPage")
    public ResponseMessage listByPage(Role  role,Integer pageNo,Integer pageSize){
        try { 
        	PageBean<Role> result = roleService.listByPage(role,pageNo,pageSize);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //查询列表
    @GetMapping(value = "/list")
    public ResponseMessage list(Role role){
        try { 
            List<Role> result = roleService.list(role);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
    //根据id查询
    @GetMapping(value = "/get")
    public ResponseMessage get(Integer id){
        try { 
            Role role = roleService.get(id);
            return new ResponseMessage(MessageCode.SUCCESS, role);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //增加
    @PostMapping(value = "/append")
    public ResponseMessage addRole(Role role){
        try { 
            roleService.append(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @PostMapping(value = "/update")
    public ResponseMessage updateRole(Role role){
        try { 
            roleService.update(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @PostMapping(value = "/delete")
    public ResponseMessage delRole(Role role){
        try { 
            roleService.delete(role);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

