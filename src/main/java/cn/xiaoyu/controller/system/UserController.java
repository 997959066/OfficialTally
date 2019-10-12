package cn.xiaoyu.controller.system;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.system.User;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author 日期:2018-08-13
 */
@RestController
@RequestMapping("/user")
public class UserController extends Base {


    //查询列表
    @GetMapping(value = "/listByPage")
    public ResponseMessage listByPage(User user, Integer pageNo, Integer pageSize) {
        try {
            PageBean<User> result = userService.listByPage(user, pageNo, pageSize);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //查寻单个信息
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessage get(User user) {
        try {
            User users = userService.get(user);
            return new ResponseMessage(MessageCode.SUCCESS, users);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //增加
    @PostMapping(value = "/append")
    public ResponseMessage addUser(User user) {
        try {
            int returnId = userService.append(user);
            if (returnId <= 0) {
                return responseMessage(MessageCode.DB_OPERATION_ROWS_ZERO, "没有成功增加，可能已有该用户");
            }
            return new ResponseMessage(MessageCode.SUCCESS, returnId);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //修改
    @PostMapping(value = "/update")
    public ResponseMessage updateUser(User user) {
        try {
            int returnId = userService.update(user);
            if (returnId <= 0) {
                return responseMessage(MessageCode.DB_OPERATION_ROWS_ZERO);
            }
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    //删除
    @PostMapping(value = "/delete")
    public ResponseMessage delUser(User user) {
        try {
            userService.delete(user);
            return new ResponseMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

