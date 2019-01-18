package cn.xiaoyu.entity.system;

import java.io.Serializable;
import java.util.List;
/**
 * role 实体类
*/ 
public class Role implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 
    private Integer id;
    // 角色名称
    private String roleName;
    // 建立时间
    private String createDate;
    // 建立者
    private Integer createUser;
    // 更新时间
    private String updateDate;
    // 更新者
    private Integer updateUser;
    
    private String functionName;  //传输
    
    List<Menu> listFunctionByRoleId;
    
    
    public List<Menu> getListFunctionByRoleId() {
		return listFunctionByRoleId;
	}
	public void setListFunctionByRoleId(List<Menu> listFunctionByRoleId) {
		this.listFunctionByRoleId = listFunctionByRoleId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public void setId(Integer id){
    this.id=id;
    }
    public Integer getId(){
        return id;
    }
    public void setRoleName(String roleName){
    this.roleName=roleName;
    }
    public String getRoleName(){
        return roleName;
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
}

