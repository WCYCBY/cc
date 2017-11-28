/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResourceRoles.java
 * @Prject: llmj-authority
 * @Package: 
 * @author: yanglu  
 * @date: 2016年8月8日 下午2:25:58
 * @version: V1.0  
 */
package com.zp.entity;

/**
 * <ResourceRoles实体>
 * @Title: Resources.java
 * @Description: <功能详细描述>
 * @author  yanglu
 * @date 2017年2月17日下午1:48:29
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class ResourceRoles implements java.io.Serializable {

	/**
	 * 角色id
	 */
	private Integer roleId;
	
	/**
	 * 资源id
	 */
	private Integer rescId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getRescId() {
		return rescId;
	}
	public void setRescId(Integer rescId) {
		this.rescId = rescId;
	}
	@Override
	public String toString() {
		return "ResourceRoles [roleId=" + roleId + ", rescId=" + rescId + "]";
	}


}