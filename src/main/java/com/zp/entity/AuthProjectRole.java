/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthProjectRole.java
 * @Prject: llmj-authority
 * @Package: com.mj.entity
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年8月30日 下午12:02:24
 * @version: V1.0  
 */
package com.zp.entity;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * @Title: AuthProjectRole.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月30日下午12:02:24
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class AuthProjectRole implements Serializable {

	/**
	 * 角色id
	 */
	private Integer roleId;
	
	/**
	 * 项目id
	 */
	private Integer projectId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "AuthProjectRole [roleId=" + roleId + ", projectId=" + projectId
				+ "]";
	}
	
	
}
