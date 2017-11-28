/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: LoginController
 * @Prject: llmj-authority
 * @Package: com.llmj.client
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
package com.zp.service;

import com.zp.entity.UserLoginListEntity;


/**
 * @Title:
 * @Description: <用户登录记录>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
public interface UserLoginListService {


    /**
     * @Title
     * @Description: <插入用户登录记录>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    void add(UserLoginListEntity userLoginList)throws Exception;

}
