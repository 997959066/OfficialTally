package cn.xiaoyu.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MD5;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.system.User;
import io.swagger.annotations.ApiOperation;

/**
 * 描述:
 * 
 * @author 日期:2018-08-13
 */
@RestController
@RequestMapping("/login")
public class LoginController extends Base {

	@ApiOperation(value = "登陆验证", notes = "登陆验证")
	@PostMapping("/login")
	public ResponseMessage login(User user) {
		try {
			User userLoginName = new User();
			userLoginName.setLoginName(user.getLoginName());
			User get1 = userService.getUser(userLoginName);
			if (get1 != null) {
				if (get1.getPassWord().equals(MD5.md5(user.getPassWord()+xiaoyu_key))) {
					get1.setPassWord(null);
					// 返回token
					String token = getToken(get1.getId());
					get1.setToken(token);
					//加入sesseion
					SerializerFeature[] featureArr = { SerializerFeature.WriteClassName };  
			        String text = JSON.toJSONString(get1, featureArr);  
			        setSseeion("user", text);
					return new ResponseMessage(MessageCode.SUCCESS, get1);
				} else {
					return new ResponseMessage(MessageCode.ERROR);
				}
			} else {
				return new ResponseMessage(MessageCode.ERROR);
			}
		} catch (Exception e) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	@ApiOperation(value = "退出系统", notes = "退出系统")
	@PostMapping("/out")
	public ResponseMessage out() {
		try {
			removeSession("user");
			return new ResponseMessage(MessageCode.SUCCESS);
		} catch (Exception e) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	  //注册页面增加用户
	@PostMapping("/registeredUser")
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
 
  
}
