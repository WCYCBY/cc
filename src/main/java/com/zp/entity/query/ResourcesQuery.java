/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResourcesQuery.java
 * @Prject: pss-authority-boot
 * @Package: com.pss.entity.query
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年2月17日 下午5:27:06
 * @version: V1.0  
 */
package com.zp.entity.query;

import java.util.HashSet;
import java.util.Set;

import com.zp.entity.Resources;
import com.zp.entity.Roles;

/**
 * <ResourcesQuery实体>
 * 
 * @Title: ResourcesQuery.java
 * @Description: <继承Resources实体，新增业务属性>
 * @author yanglu
 * @date 2017年2月17日下午5:27:06
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResourcesQuery extends Resources {



	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色集合
	 */
	private Set<Roles> roles = new HashSet<Roles>(0);

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ResourcesQuery [roles=" + roles + "]";
	}

}
