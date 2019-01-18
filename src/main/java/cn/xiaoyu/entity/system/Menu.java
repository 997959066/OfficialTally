package cn.xiaoyu.entity.system;

import java.io.Serializable;
/**
 * menu 实体类
*/ 
public class Menu implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 
    private Integer id;
    // 映射名字(功能名称)
    private String resourceName;
    // 访问路径(url)
    private String accessPath;
    // 父类id
    private Integer parentId;
    // 资源标识 识别主子菜单
    private Integer resourceId;
    public void setId(Integer id){
    this.id=id;
    }
    public Integer getId(){
        return id;
    }
    public void setResourceName(String resourceName){
    this.resourceName=resourceName;
    }
    public String getResourceName(){
        return resourceName;
    }
    public void setAccessPath(String accessPath){
    this.accessPath=accessPath;
    }
    public String getAccessPath(){
        return accessPath;
    }
    public void setParentId(Integer parentId){
    this.parentId=parentId;
    }
    public Integer getParentId(){
        return parentId;
    }
    public void setResourceId(Integer resourceId){
    this.resourceId=resourceId;
    }
    public Integer getResourceId(){
        return resourceId;
    }
  
}

