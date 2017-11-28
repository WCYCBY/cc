/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CmsColumn.java
 * @Prject: zp-authority_boot
 * @Package: com.zp.entity
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年3月6日 上午11:34:11
 * @version: V1.0  
 */
package com.zp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <cms栏目实体>
 * 
 * @Title: CmsColumn.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日上午11:34:11
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CmsColumn implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5377042320658138327L;
	/**
	 * 栏目id
	 */
	private Integer id;
	/***
	 * 栏目名称
	 */
	private String name;
	/**
	 * 创建者
	 */
	private Integer creater;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新者
	 */
	private Integer updater;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 栏目状态
	 */
	private Integer status;

	/**
	 * 模板
	 */
	private String ftl;
	/**
	 * 栏目类型。1.图片2.新闻3.文字
	 */
	private Integer type;

	/**
	 * 是否发布 1发布0未发布
	 */
	private Integer isrelease;
	/**
	 * 图片数量上限
	 */
	private Integer upperLimitPic;
	/**
	 * 图片数量下限
	 */
	private Integer lowerLimitPic;
	/**
	 * 内容数量上限
	 */
	private Integer upperLimitText;
	/**
	 * 内容数量下限
	 */
	private Integer lowerLimitText;
	/**
	 * 栏目说明
	 */
	private String note;
	/**
	 * 版本号
	 */
	private Integer version;
	/**
	 * 版本号
	 */
	private String ftlForContent;

	private List<CmsColumnContent> columnContents;

	
	public String getFtlForContent() {
		return ftlForContent;
	}

	public void setFtlForContent(String ftlForContent) {
		this.ftlForContent = ftlForContent;
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

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFtl() {
		return ftl;
	}

	public void setFtl(String ftl) {
		this.ftl = ftl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsrelease() {
		return isrelease;
	}

	public void setIsrelease(Integer isrelease) {
		this.isrelease = isrelease;
	}

	public Integer getUpperLimitPic() {
		return upperLimitPic;
	}

	public void setUpperLimitPic(Integer upperLimitPic) {
		this.upperLimitPic = upperLimitPic;
	}

	public Integer getLowerLimitPic() {
		return lowerLimitPic;
	}

	public void setLowerLimitPic(Integer lowerLimitPic) {
		this.lowerLimitPic = lowerLimitPic;
	}

	public Integer getUpperLimitText() {
		return upperLimitText;
	}

	public void setUpperLimitText(Integer upperLimitText) {
		this.upperLimitText = upperLimitText;
	}

	public Integer getLowerLimitText() {
		return lowerLimitText;
	}

	public void setLowerLimitText(Integer lowerLimitText) {
		this.lowerLimitText = lowerLimitText;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<CmsColumnContent> getColumnContents() {
		return columnContents;
	}

	public void setColumnContents(List<CmsColumnContent> columnContents) {
		this.columnContents = columnContents;
	}

	@Override
	public String toString() {
		return "CmsColumn [id=" + id + ", name=" + name + ", creater=" + creater + ", createTime=" + createTime
				+ ", updater=" + updater + ", updateTime=" + updateTime + ", status=" + status + ", ftl=" + ftl
				+ ", type=" + type + ", isrelease=" + isrelease + ", upperLimitPic=" + upperLimitPic
				+ ", lowerLimitPic=" + lowerLimitPic + ", upperLimitText=" + upperLimitText + ", lowerLimitText="
				+ lowerLimitText + ", note=" + note + ", version=" + version + ", columnContents=" + columnContents
				+ "]";
	}

}
