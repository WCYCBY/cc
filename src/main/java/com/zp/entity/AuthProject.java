/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthProject.java
 * @Prject: llmj-authority
 * @Package: com.mj.entity
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年8月26日 上午10:40:20
 * @version: V1.0  
 */
package com.zp.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 项目实体
 * @Title: AuthProject.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月26日上午10:40:20
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthProject implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7667555858896087059L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 项目名
	 */
	private String name;
	/**
	 * 项目key值，唯一约束（pro_key）
	 */
	private String uniqueKey;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 创建人
	 */
	private Integer createUser;
	/**
	 * 是否删除，默认为0表示正常，1表示已删除
	 */
	private Integer isDel=0;
	/**
	 * 域名
	 */
	private String url;
	/**
	 * 修改时间
	 */
	private Timestamp updateTime;
	/**
	 * 修改人
	 */
	private Integer updateUser;
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 项目排序编号
	 */
	private Integer orderNumber;
	
	
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "AuthProject [id=" + id + ", name=" + name + ", uniqueKey=" + uniqueKey + ", createTime=" + createTime
				+ ", description=" + description + ", createUser=" + createUser + ", isDel=" + isDel + ", url=" + url
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", icon=" + icon + ", orderNumber="
				+ orderNumber + "]";
	}
	
	
	
	
}
