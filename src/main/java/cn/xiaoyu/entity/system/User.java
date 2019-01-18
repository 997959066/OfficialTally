package cn.xiaoyu.entity.system;

import java.io.Serializable;
import java.util.List;
/**
 * user 实体类
*/ 
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 
    private Integer id;
    // 用户名
    private String userName;
    // 建立日期
    private String createDate;
    // 建立者
    private Integer createUser;
    // 更新时间
    private String updateDate;
    // 更新者
    private Integer updateUser;
    // 禁用标志
    private Integer disabledFlag;
    // 禁用时间
    private String disabledDate;
    // 
    private String faceImage;
    // 登录账号
    private String loginName;
    // 
    private String passWord;
    // 手机号
    private String phone;
    
    private String token; //传输
    
    private String roleName;//传输
    
    private Integer roleId;//传输
    
    private List<Role> roleList; //传输
    
    private Integer beforeRoleId;  //传输
    
    public Integer getBeforeRoleId() {
		return beforeRoleId;
	}
	public void setBeforeRoleId(Integer beforeRoleId) {
		this.beforeRoleId = beforeRoleId;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setId(Integer id){
    this.id=id;
    }
    public Integer getId(){
        return id;
    }
    public void setUserName(String userName){
    this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setCreateDate(String createDate){
    this.createDate=createDate;
    }
    public String getCreateDate(){
        return createDate;
    }
    public void setCreateUser(Integer createUser){
    this.createUser=createUser;
    }
    public Integer getCreateUser(){
        return createUser;
    }
    public void setUpdateDate(String updateDate){
    this.updateDate=updateDate;
    }
    public String getUpdateDate(){
        return updateDate;
    }
    public void setUpdateUser(Integer updateUser){
    this.updateUser=updateUser;
    }
    public Integer getUpdateUser(){
        return updateUser;
    }
    public void setDisabledFlag(Integer disabledFlag){
    this.disabledFlag=disabledFlag;
    }
    public Integer getDisabledFlag(){
        return disabledFlag;
    }
    public void setDisabledDate(String disabledDate){
    this.disabledDate=disabledDate;
    }
    public String getDisabledDate(){
        return disabledDate;
    }
    public void setFaceImage(String faceImage){
    this.faceImage=faceImage;
    }
    public String getFaceImage(){
        return faceImage;
    }
    public void setLoginName(String loginName){
    this.loginName=loginName;
    }
    public String getLoginName(){
        return loginName;
    }
    public void setPassWord(String passWord){
    this.passWord=passWord;
    }
    public String getPassWord(){
        return passWord;
    }
    public void setPhone(String phone){
    this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }
}

