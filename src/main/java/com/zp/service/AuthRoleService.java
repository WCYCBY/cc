package com.zp.service;

import java.util.List;

import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;

public interface AuthRoleService {
	
	/**
	 * 
	 * 分页查询用户角色
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午10:45:05 
	 * @param role
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	List<Roles> query(Roles role) throws Exception;
	
	/**
	 * 
	 * 添加
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 下午4:44:12 
	 * @param role
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	void add(Roles role) throws Exception;
	
	
	/**
	 * 
	 * 通过id查找角色
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 下午4:44:24 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	Roles getById (String roleId) throws Exception;
	
	
	/**
	 * 
	 * 更新修改的信息
	 * @Title: modify
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:55:37 
	 * @param role
	 * @see [类、类#方法、类#成员]
	 */
	void modify(Roles role) throws Exception;
	
	/**
	 * 
	 * 根据id删除
	 * @Title: delete
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 下午12:00:19 
	 * @param roleId
	 * @see [类、类#方法、类#成员]
	 */
	void delete(String roleId) throws Exception;

	/**
	 * 
	 * <一句话功能简述>
	 * @Title: queryUserRolesByRoleId
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 下午4:44:44 
	 * @param rolesId
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthUserEntity> queryUserRolesByRoleId(String rolesId) throws Exception;
	
	/**
	 * 
	 * 验证角色名
	 * @Title: querySingleRolesByName
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 下午4:44:51 
	 * @param rolesName
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	Roles querySingleRolesByName(String rolesName) throws Exception;
	
	
	/**
	 * 
	 * 验证角色key
	 * @Title: querySingleRolesByKey
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 下午4:45:35 
	 * @param rolesKey
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	Roles querySingleRolesByKey(String rolesKey) throws Exception;
	
}
