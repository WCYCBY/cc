/**  
 * @Title: AuthorityEnum.java
 * @Prject: pss-authority
 * @Package: com.pss.config
 * @Description: <功能详细描述>
 * @author: chenbaiyu  
 * @date: 2017年3月3日 上午10:09:54
 * @version: V1.0  
 */
package com.zp.config;

/**
 * 系统错误编码及描述
 * @Title: AuthorityEnum.java
 * @Description: <功能详细描述>
 * @author  pandaijun
 * @date 2017年3月3日上午10:09:54
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum AuthorityErrorCodeEnum {
	SYS_ERROR("ERROR","系统异常,请稍后再试"),
	NULL_OBJ("SYS_0001","对象为空"),
	
	
	
	//-------ROLE---------
	ERROR_QUERY_ROLE("ROLE_001","查询角色列表失败"),
	EROOR_QUERY_ROLEBYID("ROLE_002","查询单条角色失败"),
	ERROR_UPDATE_ROLEBYID("ROLE_003","更新角色失败"),
	ERROR_DELETE_ROLE("ROLE_004","删除信息异常"),
	ERROR_QUERY_UPROLEBYID("ROLE_005","根据id检验角色异常"),
	ERROR_QUERY_ROLENAME("ROLE_006","验证角色名称是否重复异常"),
	ERROR_QUERY_ROLEKEY("ROLE_007","验证角色key是否重复异常"),
	ERROR_ADD_ROLE("ROLE_008","添加角色异常"),
	ERROR_QUERY_PERROLE("ROLE_009","查询角色权限异常"),
	ERROR_ADD_PROROLE("ROLE_0010","保存分配项目异常"),
	ERROR_ADD_PERPROROLE("ROLE_0011","增加角色和权限异常"),
	
	
	//--------CMS----------
	ERROR_QUERY_CMSLIST("CMS_001","查询cms列表失败"),

    //userList-----------------
  	ERROR_QUERY_BYID_USER("USER_0001","根据id查询用户失败"),
  	ERROR_QUERY_LIST_USER("USER_0002","查询用户列表失败"),
  	ERROR_QUERY_ROLES_USER("USER_0003","查询用户角色失败"),
  	ERROR_DELETE_ROLE_USER("USER_0004","删除用户对应角色失败"),
  	ERROR_SAVE_ROLE_USER("USER_0005","保存用户角色失败"),
  	ERROR_ADD_USER("USER_0006","添加用户失败"),
  	ERROR_DELETE_USER("USER_0007","删除用户失败"),
  	ERROR_UPDATE_USER("USER_0008","修改用户失败"),
  	ERROR_QUERY_BYNMAE_USER("USER_0009","根据用户名查询用户失败"),
  	ERROR_USERNAME_ISHAVE("USER_0010","用户名已经存在"),
  	//userList-----------------
    

	//resources type
	AUTHORITY_RESOURCE_TYPE_DIRECTORY("0","资源类型 目录"),
	AUTHORITY_RESOURCE_TYPE_MENU("1"," 资源类型 菜单"),
	AUTHORITY_RESOURCE_TYPE_BUTTON("2"," 资源类型 菜单按钮"),
	//resources type
	
	//department---------------------------------------------
    ERROR_QUERY_DEPARTMENT("DEP_0001","查询工厂失败"),
    ERROR_CHECK_DEPARTMENT("DEP_0002","判断工厂是否有用户使用失败"),
    ERROR_DELETE＿DEPARTMENT("DEP_0003","删除工厂失败"),
    ERROR_ADD＿DEPARTMENT("DEP_0004","新增工厂失败"),
    ERROR_EDIT＿DEPARTMENT("DEP_0005","编辑工厂失败"),
    //department---------------------------------------------
    //------------------------yl----------------
    ERROR_ADD_RESOURCES("RES_0001","新增资源失败"),
    ERROR_DELETE_RESOURCES("RES_0002","删除资源失败"),
    ERROR_UPDATE_RESOURCES("RES_0003","更新资源失败"),
    ERROR_QUERY_RESOURCES("RES_0004","查询资源失败"),
    ERROR_CHECK_RESOURCES("RES_0005","校验资源是否有下级资源失败"),
    ERROR_CHECK_RES_KEY("RES_0006","校验资源key唯一性失败"),
	NOTNULL_KEY("RES_0001","key存在"),
    
    //-----------------------yl-----------------------------
    //project-----------------
    ERROR_ADD_PROJECT("PRO_0004","添加项目失败"),
    ERROR_DELETE_PROJECT("PRO_0003","删除项目失败"),
    ERROR_UPDATE_PROJECT("PRO_0002","更新项目失败"),
    ERROR_QUERY_PROJECT("PRO_0001","查询项目失败");
    //project------------------
	
	/**
	 * 错误编码
	 */
	private String errorCode;
	
	/**
	 * 错误描述
	 */
	private String errorDescribe;
	
	private AuthorityErrorCodeEnum(String errorCode,String errorDescribe){
		this.errorCode = errorCode;
		this.errorDescribe = errorDescribe;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescribe() {
		return errorDescribe;
	}

	public void setErrorDescribe(String errorDescribe) {
		this.errorDescribe = errorDescribe;
	}
	
	public String toString() {
        return "[" + this.errorCode + "]" + this.errorDescribe;
    }
	
}
