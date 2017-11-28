/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: Roles.java
 * @Prject: llmj-authority
 * @Package: 
 * @author: qinrenchuan  
 * @date: 2016年8月8日 下午2:25:58
 * @version: V1.0  
 */
package com.zp.entity;

/**
 * 
 * 角色实体
 * @Title: Roles.java
 * @author  qinrenchuan
 * @date 2016年7月29日下午9:45:35
 */
public class Roles implements java.io.Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7382035556474943877L;

	private Integer id;
	
	/**
	 * 是否禁用角色　1　整除，　0　表示禁用
	 */
	private Integer enable;
	
	private String name;
	
	private String roleKey;
	
	private String description;

	public Roles() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", enable=" + enable + ", name=" + name
				+ ", roleKey=" + roleKey + ", description=" + description+ ", resources="  + "]";
	}

	
}