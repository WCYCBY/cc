/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResourcesDao.java
 * @Prject: pss-authority-boot
 * @Package: com.pss.mapper
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年2月17日 下午2:15:00
 * @version: V1.0  
 */
package com.zp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.ResourceRoles;
import com.zp.entity.Resources;
import com.zp.entity.query.ResourcesQuery;

import java.util.List;
import java.util.Map;

/**
 * <资源dao>
 * 
 * @Title: ResourcesMapper.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年2月17日下午2:15:00
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface AuthResourcesMapper {
	/**
	 * 
	 * <查询资源列表>
	 * 
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月20日 上午10:04:31
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

	List<Resources> queryList(Map<String, Object> map);

	/**
	 * 
	 * <查询所有资源>
	 * 
	 * @Title: findAll
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:00:47
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Resources> findAll();

	/**
	 * 
	 * <根据资源ID获取资源角色关系表>
	 * 
	 * @Title: getRoleRescoursBySourceId
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:00:57
	 * @param sourceid
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<String> getRoleRescoursBySourceId(String sourceid);

	/**
	 * 
	 * <删除子节点>
	 * 
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:01:08
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteById(String id);

	/**
	 * 
	 * < 根据资源parentId查询资源ID信息,可以获取到子节点>
	 * 
	 * @Title: getResourcesIdByParentId
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:01:21
	 * @param parentId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> getResourcesIdByParentId(String parentId);

	/**
	 * 
	 * <根据资源id查询资源>
	 * 
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:01:34
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
	 * @date 2017年3月3日 下午5:01:44
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> queryByProId(Map<String, Object> map);

	/**
	 * 
	 * <根据资源id查询下级菜单或者按钮>
	 * 
	 * @Title: queryChildRes
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:01:55
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<ResourcesQuery> queryChildRes(String id);

	/**
	 * 
	 * < 根据项目id查询资源>
	 * 
	 * @Title: queryByProKey
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:02:05
	 * @param projectId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Resources> queryByProKey(Integer projectId);

	/**
	 * 
	 * <根据资源id，删除资源角色关联>
	 * 
	 * @Title: deleteRoleByResId
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:09:46
	 * @param resId
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteRoleByResId(String resId);

	// ------cby end----
	/**
	 * 
	 * <新增资源>
	 * 
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:02:13
	 * @param resources
	 * @see [类、类#方法、类#成员]
	 */
	public void add(Resources resources);

	/**
	 * 
	 * <根据条件查询资源>
	 * 
	 * @Title: getResourcesByMap
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:02:49
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
	 * @author yanglu
	 * @date 2017年3月3日 下午5:04:19
	 * @param roleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

	List<Resources> getRoleResources(String roleId);

	/***
	 * 
	 * <根据用户id查询用户权限资源>
	 * 
	 * @Title: queryNodeAuth
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:03:01
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Resources> queryNodeAuth(String userId);

	/**
	 * 
	 * <根据roleId删除角色资源关联关系>
	 * 
	 * @Title: deleteRoleRescours
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2017年3月3日 下午5:03:35
	 * @param roleId
	 * @see [类、类#方法、类#成员]
	 */
	void deleteRoleRescours(String roleId);

	/**
	 * 
	 * <增加角色和权限>
	 * 
	 * @Title: saveRoleRescours
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月3日 下午5:03:28
	 * @param resourceRoles
	 * @see [类、类#方法、类#成员]
	 */
	void saveRoleRescours(ResourceRoles resourceRoles);

	/**
	 * 
	 * <校验资源key唯一性>
	 * 
	 * @Title: queryResKey
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月1日 下午3:01:01
	 * @param resKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Resources> queryResKey(String resKey);

	/**
	 * 
	 * <编辑----保存>
	 * 
	 * @Title: update
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月1日 下午6:07:37
	 * @param resources
	 * @see [类、类#方法、类#成员]
	 */
	void update(Resources resources);

}
