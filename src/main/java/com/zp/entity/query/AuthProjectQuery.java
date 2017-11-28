/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthProjectQuery.java
 * @Prject: pss-authority
 * @Package: com.mj.entity.query
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年8月26日 上午11:00:46
 * @version: V1.0  
 */
package com.zp.entity.query;

import com.zp.entity.AuthProject;

/**
 * <一句话功能简述>
 * @Title: AuthProjectQuery.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月26日上午11:00:46
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthProjectQuery extends AuthProject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5509845406839410284L;
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	
	private boolean checkFlag;
	

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	

}
