package com.zp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zp.entity.UserLoginListEntity;
import com.zp.mapper.UserLoginListMapper;
import com.zp.service.UserLoginListService;


/**
 * @Title:
 * @Description: <用户登录记录>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
@Service
public class UserLoginListServiceImpl implements UserLoginListService {

    @Autowired
    private UserLoginListMapper userLoginListMapper;

    /**
     * @Title
     * @Description: <功能详细描述>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @Override
    @Transactional
    public void add(UserLoginListEntity userLoginList) throws Exception {
        userLoginListMapper.add(userLoginList);
    }

   
}
