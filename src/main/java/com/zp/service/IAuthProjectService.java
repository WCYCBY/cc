/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthProjectService.java
 * @Prject: llmj-authority
 * @Package: com.mj.service
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年8月26日 上午10:57:28
 * @version: V1.0  
 */
package com.zp.service;

import java.util.List;

import com.zp.entity.AuthProject;
import com.zp.entity.AuthProjectRole;
import com.zp.entity.query.AuthProjectQuery;

/**
 * <一句话功能简述>
 * @Title: IAuthProjectService.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月26日上午10:57:28
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IAuthProjectService {

	/**
	 * 
	 * 根据条件查询项目列表
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 上午11:15:24 
	 * @param authProject
	 * @param pageView
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectQuery> queryList(AuthProjectQuery authProject,Integer page,Integer pageSize) throws Exception ;

	/**
	 * 新增项目
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午3:59:03 
	 * @param authProject
	 * @see [类、类#方法、类#成员]
	 */
	void add(AuthProject authProject) throws Exception;

	/**
	 * 根据用户Key查询用户
	 * @Title: querySingleUserByName
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午4:30:05 
	 * @param name
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthProjectQuery querySingleUserByKey(String key) throws Exception;

	/**
	 * 
	 * 查询项目列表
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月27日 下午4:16:48 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectQuery> queryAll() throws Exception;

	/**
	 * 根据id查询
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 上午10:33:36 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthProjectQuery queryById(Integer id) throws Exception;

	/**
	 * 修改项目
	 * <一句话功能简述>
	 * @Title: update
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 上午11:14:44 
	 * @param authProject
	 * @see [类、类#方法、类#成员]
	 */
	void update(AuthProject authProject) throws Exception;

	/**
	 *根据id删除，删除后不可恢复 
	 * @Title: del
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 下午2:45:34 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void delById(Integer id) throws Exception;

	/**
	 * 根据角色id查询出项目与角色对应的列表
	 * <一句话功能简述>
	 * @Title: queryProjectRoleByRoleId
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月30日 下午1:51:26 
	 * @param roleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectRole> queryProjectRoleByRoleId(String roleId) throws Exception;

	/**
	 * 根据项目id查询关联的用户角色
	 * <一句话功能简述>
	 * @Title: queryRoleByPro
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年9月7日 上午10:22:30 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Integer> queryRoleByPro(Integer id) throws Exception;

	/**
	 * @Title
	 * @Description: <根据用户id查询该用户拥有权限的项目>
	 * @author qrc
	 * @date 2017/2/27
	 * @param 
	 * @return
	 * @see [类、类#方法、类#成员]
	*/
	List<AuthProjectQuery> queryProListByUserId(String userId) throws Exception;

	/**
	 * 根据角色id查询关联的项目
	 * <一句话功能简述>
	 * @Title: queryRoleByPro
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年9月7日 上午10:22:30 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProject> queryProjectByRoleId(String roleId) throws Exception;
	
}
