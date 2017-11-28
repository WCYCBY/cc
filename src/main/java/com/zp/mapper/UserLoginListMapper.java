/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserLoginListEntity
 * @Prject: pss-authority
 * @Package: com.pss.client
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
package com.zp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.UserLoginListEntity;

import java.util.List;


/**
 * @Title:
 * @Description:
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
@Mapper
public interface UserLoginListMapper {

    /**
     * 插入用户登录记录
     * @param userLoginList
     * @throws Exception
     */
    void add(UserLoginListEntity userLoginList);
    
    /**
     * 
     * <一句话功能简述>
     * @Title: queryList
     * @Description: <功能详细描述>
     * @author LiuYu
     * @date 2017年2月17日 上午11:39:12 
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserLoginListEntity> queryList();
}
