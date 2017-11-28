/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthorityException.java
 * @Prject: pss-authority
 * @Package: com.pss.config
 * @Description: <功能详细描述>
 * @author: chenbaiyu  
 * @date: 2017年3月3日 上午10:33:21
 * @version: V1.0  
 */
package com.zp.config;

/**
 * 系统自定义异常
 * @Title: AuthorityException.java
 * @Description: <功能详细描述>
 * @author  chenbaiyu
 * @date 2017年3月3日上午10:33:21
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthorityException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 487864198823654390L;
	
	
	public AuthorityException(Object object){
		super(object.toString());
	}

	
	public static void main(String args[]) throws Exception {
        Object user = null;
        if(user == null){
            throw new AuthorityException(AuthorityErrorCodeEnum.NULL_OBJ);
        }
    }
}
