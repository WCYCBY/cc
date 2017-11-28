/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserLoginListEntity
 * @Prject: llmj-authority
 * @Package: com.llmj.client
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
package com.zp.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.entity.UserRoles;

/**
 * @Title:
 * @Description:
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
@Mapper
public interface AuthUserMapper {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    AuthUserEntity queryUserName(String userName);
    /**
     * @Title
     * @Description: <修改密码>
     * @author qrc
     * @date 2017/2/23
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    void  updatePassword(Map<String,Object> map);
    
    /**
     * 根据id查
     * <一句话功能简述>
     * @Title: getById
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月27日 下午2:59:19 
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    AuthUserEntity getById(String userId);
	
    /**
     * 查询所有
     * <一句话功能简述>
     * @Title: query
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月27日 下午2:59:36 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
   	List<AuthUserEntity> query(Map<Object, Object> map);
   	
   	/**
   	 * 修改用户信息
   	 * <一句话功能简述>
   	 * @Title: update
   	 * @Description: <功能详细描述>
   	 * @author caoju
   	 * @date 2017年2月27日 下午2:59:43 
   	 * @param user
   	 * @see [类、类#方法、类#成员]
   	 */
    void updateUser(AuthUserEntity user);
    
    /**
     * 添加用户
     * <一句话功能简述>
     * @Title: addUser
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月27日 下午5:33:31 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    void addUser(AuthUserEntity user);
    
    /**
     * 删除
     * <一句话功能简述>
     * @Title: updateDel
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月28日 上午11:49:39 
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    void updateDel(String userId);
    
    /**
     * 删除对应的角色
     * <一句话功能简述>
     * @Title: delUserRole
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月28日 下午2:12:15 
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    void deleteUserRole(String userId);
    
    /**
     * 查询没被禁用的角色
     * <一句话功能简述>
     * @Title: findRoles
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月28日 下午3:07:19 
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Roles> findRoles();
    
    /**
     * 分配角色
     * <一句话功能简述>
     * @Title: saveUserRole
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月28日 下午4:18:04 
     * @param userRoles
     * @see [类、类#方法、类#成员]
     */
    void saveUserRole(UserRoles userRoles);
    
    /**
     * 
     * <一句话功能简述>
     * @Title: updateUserLevel
     * @Description: <功能详细描述>
     * @author caoju
     * @date 2017年2月28日 下午4:29:06 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    public void updateUserLevel(AuthUserEntity user);
    
}
