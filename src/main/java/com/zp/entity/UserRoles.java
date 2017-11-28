/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserRoles.java
 * @Prject: llmj-authority
 * @Package: 
 * @author: qinrenchuan  
 * @date: 2016年8月8日 下午2:25:58
 * @version: V1.0  
 */
package com.zp.entity;

/**
 * 
 * 用户角色
 * @Title: UserRoles.java
 * @author  qinrenchuan
 * @date 2016年7月29日下午9:38:14
 */
@SuppressWarnings("serial")
public class UserRoles implements java.io.Serializable {

	private Integer roleId;
	
	private Integer userId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", userId=" + userId + "]";
	}

	
}