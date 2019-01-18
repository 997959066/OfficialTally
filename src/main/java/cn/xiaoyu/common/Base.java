package cn.xiaoyu.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.xiaoyu.dao.system.MenuMapper;
import cn.xiaoyu.dao.system.RoleMapper;
import cn.xiaoyu.dao.system.UserMapper;
import cn.xiaoyu.dao.tally.ConsumeTypeMapper;
import cn.xiaoyu.dao.tally.IncomeMapper;
import cn.xiaoyu.dao.tally.SummaryMapper;
import cn.xiaoyu.dao.tally.TallyMapper;
import cn.xiaoyu.entity.system.User;
import cn.xiaoyu.service.file.UploadOrDownloadService;
import cn.xiaoyu.service.system.MenuService;
import cn.xiaoyu.service.system.RoleService;
import cn.xiaoyu.service.system.UserService;
import cn.xiaoyu.service.tally.ConsumeTypeService;
import cn.xiaoyu.service.tally.IncomeService;
import cn.xiaoyu.service.tally.SummaryService;
import cn.xiaoyu.service.tally.TallyService;
import cn.xiaoyu.util.DateUtil;

public class Base {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${tally.xiaoyu.key}")
	protected String xiaoyu_key;
	
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	/**存session*/
	public void setSseeion(String key,String value) {
		 HttpSession session = getRequest().getSession();
		 session.setAttribute(key,value);
	}
	/**取session*/
	public HttpSession getSseeion() {
		HttpSession session = getRequest().getSession();
		return session;
	}
	/**清除session*/
	public void removeSession(String key){
		HttpSession session = getRequest().getSession();
		session.removeAttribute(key);
	}
	
	
	public User getUser(){
		HttpSession getSseeion= getSseeion();
		String	text=  (String) getSseeion.getAttribute("user");
		User user = (User)JSON.parseObject(text, User.class);  
		return user;
	}
	
	/**用户token*/
	public String getToken(int userId) {
		String time = DateUtil.getSysDate();
		String token = MD5.md5(userId, time, xiaoyu_key);
		JSONObject  ob =new JSONObject();
		ob.put("g_userId", userId);
		ob.put("g_token", token);
		ob.put("g_time", time);
		return ob.toString();
	}
	

    /**
     * 导出excel时，返回结果
     * @param response
     * @param msg
     */
    public static void out(HttpServletResponse response, String msg){
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write("<script language='javascript'>");
            out.write("alert('"+msg+"');");
            out.write("history.go(-1);");
            out.write("</script>");
            out.close();
            out.flush();
        } catch (IOException e) {

        }
    }

    //UploadOrDownload
    @Autowired
    protected UploadOrDownloadService uploadOrDownloadService;
    //tally  Service
    @Autowired
    protected TallyService tallyService;
    @Autowired
    protected SummaryService summaryService;
    @Autowired
    protected IncomeService incomeService;
    //system  Service
    @Autowired
    protected MenuService menuService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ConsumeTypeService consumeTypeService;
    //tally Mapper
    @Autowired
    protected TallyMapper tallyMapper;
    @Autowired
    protected SummaryMapper summaryMapper;
    @Autowired
    protected IncomeMapper incomeMapper;
    //system Mapper
    @Autowired
    protected MenuMapper menuMapper;
    @Autowired
    protected RoleMapper roleMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected ConsumeTypeMapper consumeTypeMapper;
    
    
	public ResponseMessage responseMessage(MessageCode code, Object data) {
		// 判断是否空串
		if (code.getValue().equals("") && code.getCode() == null) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, data);
		}
		// 解析msg
		try {
			return new ResponseMessage(code, data);
		} catch (Exception e) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR, data);
		}
	}

	/**
	 * 描述 :根据错误消息json串解析得到错误返回信息 2018/01/19
	 */
	public ResponseMessage responseMessage(String msg) {
		// 判断是否空串
		if (msg.equals("")) {
			logger.error(msg);
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR);
		}
		// 解析msg
		try {
			JSONObject jsonObj = JSONObject.parseObject(msg);
			Integer code = jsonObj.getInteger("code");
			String message = jsonObj.getString("msg");
			logger.error(msg);
			return new ResponseMessage(null == code ? MessageCode.UNKNOWN_ERROR.getCode() : code.intValue(), message);
		} catch (Exception e) {
			logger.error(msg);
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR.getCode(), msg);
		}
	}

	public ResponseMessage responseMessage(Integer code, String msg) {
		// 判断是否空串
		if (msg.equals("") && code.toString().equals("")) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR);
		}
		// 解析msg
		try {
			return new ResponseMessage(code, msg);
		} catch (Exception e) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR.getCode(), msg);
		}
	}

	public ResponseMessage responseMessage(MessageCode code) {
		if (code.getValue().equals("") && code.getCode() == null) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR);
		}
		// 解析msg
		try {
			return new ResponseMessage(code);
		} catch (Exception e) {
			return new ResponseMessage(MessageCode.UNKNOWN_ERROR.getCode(), code.getValue());
		}
	}
}
