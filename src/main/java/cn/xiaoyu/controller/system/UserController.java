package cn.xiaoyu.controller.system;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.system.User;
/**
 * 描述:
 * @author  
 * 日期:2018-08-13
*/ 
@RestController
@RequestMapping("/user")
public class UserController extends Base  {

    

    //查询列表
    @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
    public ResponseMessage listByPage(User  user,Integer pageNo,Integer pageSize){
        try { 
        	PageBean<User> result = userService.listByPage(user,pageNo,pageSize);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //查寻单个信息
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessage get(User user){
        try { 
            User users = userService.get(user);
            return new ResponseMessage(MessageCode.SUCCESS, users);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //增加
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public ResponseMessage addUser(User user){
        try { 
           int  returnId =userService.append(user);
			if(returnId<=0){
				return responseMessage(MessageCode.DB_OPERATION_ROWS_ZERO, "没有成功增加，可能已有该用户");
			}
            return new ResponseMessage(MessageCode.SUCCESS,returnId);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessage updateUser(User user){
        try { 
        	int  returnId = userService.update(user);
			if(returnId<=0){
				return responseMessage(MessageCode.DB_OPERATION_ROWS_ZERO);
			}
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delUser(User user){
        try { 
            userService.delete(user);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e){ 
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

