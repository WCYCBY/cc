/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: Resources.java
 * @Prject: llmj-authority
 * @Package: com.llmj.entity
 * @Description: <功能详细描述>
 * @author:yanglu 
 * @date: 2017年2月17日 下午1:48:29
 * @version: V1.0  
 */
package com.zp.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * <Resource实体>
 * 
 * @Title: Resources.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年2月17日下午1:48:29
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
public class Resources implements java.io.Serializable {

	private Integer id;
	private String name;

	/**
	 * 父类Id
	 */
	private Integer parentId;

	private String parentName;

	/**
	 * 这个权限KEY是唯一的
	 */
	private String resKey;

	/**
	 * URL地址，资源访问路径
	 */
	private String resUrl;

	private Integer level;

	/**
	 * 权限类型，0．表示目录 1，表示菜单．2、3、4等表示按扭．．在spring security3安全权限中，涉及精确到按扭控制
	 */
	private String type;

	private String description;

	/**
	 * 所属的项目ID
	 */
	private Integer proId;

	/**
	 * 所属项目名称
	 */
	private String projectName;

	private Set<Resources> childs = new HashSet<Resources>(0);

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Resources() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Set<Resources> getChilds() {
		return childs;
	}

	public void setChilds(Set<Resources> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parentName=" + parentName
				+ ", resKey=" + resKey + ", resUrl=" + resUrl + ", level=" + level + ", type=" + type + ", description="
				+ description + ", proId=" + proId + ", projectName=" + projectName + ", childs=" + childs + "]";
	}

}