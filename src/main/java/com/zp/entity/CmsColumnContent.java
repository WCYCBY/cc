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

/**
 * <cms栏目图片实体>
 * 
 * @Title: CmsColumnContent.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日上午11:34:11
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CmsColumnContent implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4413053571390276683L;

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 栏目id（和图片表进行关联）
	 */
	private Integer columnId;

	/**
	 * 图片地址
	 */
	private String picUrl;
	/**
	 * 图片名称
	 */
	private String picName;
	/**
	 * 图片排序索引
	 */
	private Integer picOrderIndex;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 图片链接http地址
	 */
	private String picLinkHttp;

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
	 * 版本号
	 */
	private Integer version;

	/**
	 * 菜单栏目Code(关联菜单栏目表Code),值是栏目id
	 */
	private Integer menucolumncode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Integer getPicOrderIndex() {
		return picOrderIndex;
	}

	public void setPicOrderIndex(Integer picOrderIndex) {
		this.picOrderIndex = picOrderIndex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPicLinkHttp() {
		return picLinkHttp;
	}

	public void setPicLinkHttp(String picLinkHttp) {
		this.picLinkHttp = picLinkHttp;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getMenucolumncode() {
		return menucolumncode;
	}

	public void setMenucolumncode(Integer menucolumncode) {
		this.menucolumncode = menucolumncode;
	}

	@Override
	public String toString() {
		return "CmsColumnContent [id=" + id + ", columnId=" + columnId + ", picUrl=" + picUrl + ", picName=" + picName
				+ ", picOrderIndex=" + picOrderIndex + ", remark=" + remark + ", picLinkHttp=" + picLinkHttp
				+ ", creater=" + creater + ", createTime=" + createTime + ", updater=" + updater + ", updateTime="
				+ updateTime + ", version=" + version + ", menucolumncode=" + menucolumncode + "]";
	}

}
