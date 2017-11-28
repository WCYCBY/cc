package com.zp.service;

import java.util.List;

import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.entity.UserRoles;

/**
 * @Title:
 * @Description: <登录入口>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
public interface AuthUserService {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     * @throws Exception
     */
    AuthUserEntity queryUserName(String userName)throws Exception;
  

    /**
     * @Title
     * @Description: <修改密码>
     * @author qrc
     * @date 2017/2/23
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    void updatePassword(String userId, String passwd);
    
    

  	/**
  	 * 查询集合
  	 * <一句话功能简述>
  	 * @Title: query
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月17日 下午3:09:54 
  	 * @param user
  	 * @return
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	List<AuthUserEntity> query(int page,int pageSize,AuthUserEntity user) throws Exception;
  	
  	/**
  	 * 
  	 * <一句话功能简述>
  	 * @Title: getById
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月17日 下午2:51:44 
  	 * @param id
  	 * @return
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public AuthUserEntity getById(String id) throws Exception;
  	
  	/**
  	 * 查询所有工厂
  	 * <一句话功能简述>
  	 * @Title: queryDeptList
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月27日 下午2:22:53 
  	 * @param authDepartment
  	 * @return
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	List<AuthDepartment> queryDeptList(AuthDepartment authDepartment) throws Exception;
  	
  	/**
  	 * 修改用户信息
  	 * <一句话功能简述>
  	 * @Title: updateUser
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月27日 下午2:57:58 
  	 * @param user
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public void updateUser(AuthUserEntity user) throws Exception;
  	
  	/**
  	 * 添加用户
  	 * <一句话功能简述>
  	 * @Title: add
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月27日 下午5:31:31 
  	 * @param user
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public void addUser(AuthUserEntity user) throws Exception;
  	
  	/**
  	 * 删除
  	 * <一句话功能简述>
  	 * @Title: updateDel
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月28日 上午11:50:08 
  	 * @param userId
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public void updateDel(String userId) throws Exception;
  	
  	/**
  	 * 删除对应的角色
  	 * <一句话功能简述>
  	 * @Title: delUserRole
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月28日 下午2:11:02 
  	 * @param userId
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public void delUserRole(String userId) throws Exception;
  	
  	/**
  	 * 查询没被禁用的角色
  	 * <一句话功能简述>
  	 * @Title: findRoles
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月28日 下午3:06:11 
  	 * @return
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public List<Roles> findRoles() throws Exception;
  	
  	/**
  	 * 分配角色
  	 * <一句话功能简述>
  	 * @Title: saveUserRole
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年2月28日 下午4:11:03 
  	 * @param userRoles
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public void saveUserRole(UserRoles userRoles) throws Exception;
  	/**
  	 * 校验用户名是否存在
  	 * <一句话功能简述>
  	 * @Title: querySingleUser
  	 * @Description: <功能详细描述>
  	 * @author caoju
  	 * @date 2017年3月2日 下午3:53:41 
  	 * @param userName
  	 * @return
  	 * @throws Exception 
  	 * @see [类、类#方法、类#成员]
  	 */
  	public AuthUserEntity querySingleUser(String userName) throws Exception;
}
