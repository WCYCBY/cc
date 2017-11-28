/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthDepartment.java
 * @Prject: pss-authority
 * @Package: com.pss.entity
 * @Description: <功能详细描述>
 * @author: songjunyi  
 * @date: 2017年2月17日 下午1:48:29
 * @version: V1.0  
 */
package com.zp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <一句话功能简述>
 * @Title: AuthDepartment.java
 * @Description: <功能详细描述>
 * @author  songjunyi
 * @date 2017年2月17日下午1:48:29
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class AuthDepartment implements Serializable{
	
	/**
    * 工厂id
    */
	private Integer id;
	
	/**
	 * 工厂名称
	 */
	private String name;
	
	/**
	 * 创建人
	 */
	private String creater;

	/**
	 * 创建时间
	 */
	private Date creatertime;
	
	/**
	 * 修改人
	 */
	private String alterName;
	
	/**
	 * 修改时间
	 */
	private Date altertime;
	
	/**
	 * 描述
	 */
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreatertime() {
		return creatertime;
	}

	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}

	public String getAlterName() {
		return alterName;
	}

	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}

	public Date getAltertime() {
		return altertime;
	}

	public void setAltertime(Date altertime) {
		this.altertime = altertime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AuthDepartment [id=" + id + ", name=" + name + ", creater=" + creater + ", creatertime=" + creatertime
				+ ", alterName=" + alterName + ", altertime=" + altertime + ", description=" + description + "]";
	}


}
