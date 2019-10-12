package cn.xiaoyu.controller.system;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.common.ResponseMessage;
import cn.xiaoyu.entity.system.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 *
 * @author 日期:2018-08-12
 */
@RestController
@RequestMapping("/menu")
@Api(value = "menu", description = "菜单")
public class MenuController extends Base {


    @ApiOperation(value = "根据用户查询功能列表", notes = "查询功能列表")
    @GetMapping(value = "/listFunction")
    public ResponseMessage listFunction(int userId) {
        try {
            List<Menu> result = menuService.listFunction(userId);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "全功能列表", notes = "查询功能列表")
    @GetMapping(value = "/list")
    public ResponseMessage list(Menu menu) {
        try {
            List<Menu> result = menuService.list(menu);
            return new ResponseMessage(MessageCode.SUCCESS, result);
        } catch (Exception e) {
            return new ResponseMessage(MessageCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

}

