/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: IResourcesService.java
 * @Prject: llmj-authority-boot
 * @Package: com.llmj.service
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年2月17日 下午1:30:43
 * @version: V1.0  
 */
package com.zp.service;

import java.util.List;
import java.util.Map;

import com.zp.config.AuthorityException;
import com.zp.entity.Resources;
import com.zp.entity.query.ResourcesQuery;

/**
 * <资源接口>
 * 
 * @Title: IResourcesService.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年2月17日下午1:30:43
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IResourcesService {
	/**
	 * 
	 * <查询列表>
	 * 
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:53:24
	 * @param resources
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> queryList(Resources resources, Integer page, Integer pageSize) throws Exception;

	/**
	 * 
	 * <查询所有资源>
	 * 
	 * @Title: findAll
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:53:36
	 * @return
	 * @throws AuthorityException
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> findAll() throws AuthorityException;

	/**
	 * 
	 * <根据资源id删除单个资源，以及下面的子节点>
	 * 
	 * @Title: delete
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:53:50
	 * @param id
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public boolean delete(String id) throws Exception;

	/**
	 * 
	 * <批量删除资源数据>
	 * 
	 * @Title: deleteByIds
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:54:04
	 * @param ids
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> deleteByIds(String ids[]) throws Exception;

	/**
	 * 
	 * <根据roleId删除角色资源关联关系>
	 * 
	 * @Title: deleteRoleRescours
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2017年3月3日 下午4:56:30
	 * @param roleId
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */

	void deleteRoleRescours(String roleId) throws Exception;

	/**
	 * 
	 * < 给角色分配项目权限>
	 * 
	 * @Title: saveRoleProject
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2017年3月3日 下午4:56:58
	 * @param roleId
	 * @param list
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public void saveRoleProject(String roleId, List<String> list) throws Exception;

	/**
	 * 
	 * <根据资源id查询资源>
	 * 
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:54:18
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Resources getById(String id);

	/**
	 * 
	 * <根据项目id、资源类型加载资源>
	 * 
	 * @Title: queryByProId
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:54:29
	 * @param proId
	 * @param type
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> queryByProId(Integer proId, String type) throws Exception;

	/**
	 * 
	 * < 根据资源id查询下级资源>
	 * 
	 * @Title: queryChildRes
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:54:40
	 * @param id
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public List<ResourcesQuery> queryChildRes(String id) throws Exception;

	/**
	 * 
	 * <新增保存>
	 * 
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:54:49
	 * @param resources
	 * @throws AuthorityException
	 * @see [类、类#方法、类#成员]
	 */
	public void add(Resources resources) throws AuthorityException;

	/**
	 * 
	 * <根据条件查询资源>
	 * 
	 * @Title: getResourcesByMap
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2017年3月3日 下午4:55:09
	 * @param paramMap
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Resources> getResourcesByMap(Map<String, Object> paramMap);

	/**
	 * 
	 * <根据角色获取资源>
	 * 
	 * @Title: getRoleResources
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2017年3月3日 下午4:57:27
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */

	public List<Resources> getRoleResources(String roleId) throws Exception;

	/**
	 * 
	 * < 根据角色ID查询资源>
	 * 
	 * @Title: queryByRole
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2017年3月3日 下午4:57:40
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> queryByRole(String roleId) throws Exception;

	/**
	 * 
	 * <根据用户id查询用户权限资源>
	 * 
	 * @Title: queryNodeAuth
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:55:29
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Map<String, Object> queryNodeAuth(String userId);

	/**
	 * 
	 * < 增加角色和权限>
	 * 
	 * @Title: saveRoleRescours
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:55:41
	 * @param roleId
	 * @param list
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public void saveRoleRescours(String roleId, List<String> list) throws Exception;

	/**
	 * 
	 * <校验资源key唯一性>
	 * 
	 * @Title: queryResKey
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:55:50
	 * @param resKey
	 * @return
	 * @throws AuthorityException
	 * @see [类、类#方法、类#成员]
	 */
	List<Resources> queryResKey(String resKey) throws AuthorityException;

	/**
	 * 
	 * <编辑保存>
	 * 
	 * @Title: modify
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午4:56:00
	 * @param resources
	 * @throws AuthorityException
	 * @see [类、类#方法、类#成员]
	 */
	public void modify(Resources resources) throws AuthorityException;

}
