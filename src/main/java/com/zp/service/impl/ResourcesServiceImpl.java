package com.zp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.entity.AuthProjectRole;
import com.zp.entity.ResourceRoles;
import com.zp.entity.Resources;
import com.zp.entity.query.ResourcesQuery;
import com.zp.mapper.AuthProjectMapper;
import com.zp.mapper.AuthResourcesMapper;
import com.zp.service.IResourcesService;

/**
 * <资源实现类>
 * 
 * @Title: ResourcesServiceImpl.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年2月17日下午2:12:47
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Service
@Transactional
public class ResourcesServiceImpl implements IResourcesService {
	@Autowired
	private AuthResourcesMapper resourcesMapper;
	@Autowired
	private AuthProjectMapper authProjectMapper;

	/**
	 * 
	 * <查询资源列表>
	 * 
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月20日 上午9:51:47
	 * @param resources
	 * @return
	 * @see com.zp.service.IResourcesService#query(com.zp.entity.Resources)
	 */
	@Override
	public List<Resources> queryList(Resources resources, Integer page, Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resources", resources);
		List<Resources> list = null;
		try {
			list = resourcesMapper.queryList(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_RESOURCES);
		}
		return list;
	}

	/**
	 * 
	 * <根据用户id查询用户权限资源>
	 * 
	 * @Title: queryNodeAuth
	 * @Description: <功能详细描述>
	 * @author: qinrenchuan
	 * @date: 2017年3月3日 下午4:43:13
	 * @param userId
	 * @return
	 * @see com.zp.service.IResourcesService#queryNodeAuth(java.lang.String)
	 */
	@Override
	public Map<String, Object> queryNodeAuth(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Resources> list = resourcesMapper.queryNodeAuth(userId);
		for (Resources resources : list) {
			map.put(resources.getResKey(), resources.getResUrl());
		}
		return map;
	}

	/**
	 * 
	 * <根据资源id删除单个资源，以及下面的子节点>
	 * 
	 * @Title: delete
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月22日 下午5:00:37
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.zp.service.IResourcesService#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String id) throws Exception {
		// 根据资源id获取资源角色关系
		try {
			List<String> list = resourcesMapper.getRoleRescoursBySourceId(id);
			if (list != null && list.size() > 0) {
				return false;
			}
			this.deleteResourceByParent(id);
			resourcesMapper.deleteById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_RESOURCES);
		}
		return true;
	}

	/**
	 * 
	 * <根据父节点删除子节点>
	 * 
	 * @Title: deleteResourceByParent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月22日 下午5:15:55
	 * @param parentId
	 * @see [类、类#方法、类#成员]
	 */
	private void deleteResourceByParent(String parentId) {
		// 将传递的资源id做为父id,删除父节点的时候会先删除子节点
		// 根据资源父节点获取资源信息
		List<Resources> list = resourcesMapper.getResourcesIdByParentId(parentId);
		for (Resources res : list) {
			// 删除子节点
			resourcesMapper.deleteById(res.getId().toString());
			deleteResourceByParent(res.getId().toString());
		}
	}

	/**
	 * 
	 * < 查询所有资源>
	 * 
	 * @Title: findAll
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月27日 上午10:20:33
	 * @return
	 * @throws AuthorityException
	 * @see com.zp.service.IResourcesService#findAll()
	 */
	@Override
	public List<Resources> findAll() throws AuthorityException {
		try {
			return resourcesMapper.findAll();
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_RESOURCES);
		}
	}

	/**
	 * 
	 * <根据roleId删除角色资源关联关系>
	 * 
	 * @Title: deleteRoleRescours
	 * @Description: <功能详细描述>
	 * @author: liuyu
	 * @date: 2017年3月3日 下午4:44:00
	 * @param roleId
	 * @see com.zp.service.IResourcesService#deleteRoleRescours(java.lang.String)
	 */
	@Override
	public void deleteRoleRescours(String roleId) throws Exception {
		try{
			resourcesMapper.deleteRoleRescours(roleId);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_ROLE);
		}
		

	}

	/**
	 * 
	 * <批量删除资源数据>
	 * 
	 * @Title: deleteByIds
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月24日 上午10:02:12
	 * @param ids
	 * @return
	 * @throws Exception
	 * @see com.zp.service.IResourcesService#deleteByIds(java.lang.String[])
	 */
	@Override
	public List<Resources> deleteByIds(String[] ids) throws Exception {
		List<Resources> delfailedList = new ArrayList<Resources>();// 存放删除失败的列表
		try {
			for (String id : ids) {
				List<String> list = resourcesMapper.getRoleRescoursBySourceId(id);
				if (list != null && list.size() > 0) {
					delfailedList.add(resourcesMapper.getById(id));
				} else {
					this.deleteResourceByParent(id);
					resourcesMapper.deleteById(id);
				}
			}
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_RESOURCES);
		}
		return delfailedList;
	}

	/**
	 * 
	 * < 给角色分配项目权限>
	 * 
	 * @Title: saveRoleProject
	 * @Description: <功能详细描述>
	 * @author: liuyu
	 * @date: 2017年3月3日 下午4:45:20
	 * @param roleId
	 * @param list
	 * @see com.zp.service.IResourcesService#saveRoleProject(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void saveRoleProject(String roleId, List<String> list) throws Exception  {
		try{
			authProjectMapper.delProRole(roleId);
			for (String str : list) {
				if (!"".equals(str) && str != null) {
					AuthProjectRole authProjectRole = new AuthProjectRole();
					authProjectRole.setRoleId(Integer.valueOf(roleId));
					authProjectRole.setProjectId(Integer.valueOf(str));
					authProjectMapper.saveRoleProject(authProjectRole);
				}
			}
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_PROROLE);
			
		}
		

	}

	/**
	 * 
	 * <根据资源id查询资源>
	 * 
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月27日 上午9:54:43
	 * @param id
	 * @return
	 * @see com.zp.service.IResourcesService#getById(java.lang.String)
	 */
	@Override
	public Resources getById(String id) {
		return resourcesMapper.getById(id);
	}

	/**
	 * 
	 * <根据项目id、资源类型加载资源>
	 * 
	 * @Title: queryByProId
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月27日 上午10:04:23
	 * @param proId
	 * @param type
	 * @return
	 * @throws Exception
	 * @see com.zp.service.IResourcesService#queryByProId(java.lang.Integer,
	 *      java.lang.String)
	 */
	@Override
	public List<Resources> queryByProId(Integer proId, String type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proId", proId);
		map.put("type", type);
		try {
			return resourcesMapper.queryByProId(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_RESOURCES);
		}
	}

	/**
	 * 
	 * <根据资源id查询下级资源>
	 * 
	 * @Title: queryChildRes
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月27日 上午10:07:36
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.zp.service.IResourcesService#queryChildRes(java.lang.String)
	 */
	@Override
	public List<ResourcesQuery> queryChildRes(String id) throws Exception {
		try {
			return resourcesMapper.queryChildRes(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_CHECK_RESOURCES);
		}
	}

	/**
	 * 
	 * <新增资源保存>
	 * 
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年2月27日 下午1:54:22
	 * @param resources
	 * @throws AuthorityException
	 * @see com.zp.service.IResourcesService#add(com.zp.entity.Resources)
	 */
	@Override
	public void add(Resources resources) throws AuthorityException {
		try {
			resourcesMapper.add(resources);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_RESOURCES);
		}
	}

	/**
	 * 
	 * <根据条件查询资源>
	 * 
	 * @Title: getResourcesByMap
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年3月3日 下午4:46:06
	 * @param paramMap
	 * @return
	 * @see com.zp.service.IResourcesService#getResourcesByMap(java.util.Map)
	 */
	@Override
	public List<Resources> getResourcesByMap(Map<String, Object> paramMap) {
		return resourcesMapper.getResourcesByMap(paramMap);
	}

	/**
	 * 
	 * <根据角色获取资源>
	 * 
	 * @Title: getRoleResources
	 * @Description: <功能详细描述>
	 * @author: liuyu
	 * @date: 2017年3月3日 下午4:46:46
	 * @param roleId
	 * @return
	 * @see com.zp.service.IResourcesService#getRoleResources(java.lang.String)
	 */
	@Override
	public List<Resources> getRoleResources(String roleId) throws Exception {
		List<Resources> ls = null;
		try{
			ls = resourcesMapper.getRoleResources(roleId);
		}catch(Exception e ){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PERROLE);
		}
		return ls;
	}

	/**
	 * 
	 * < 根据角色ID查询资源>
	 * 
	 * @Title: queryByRole
	 * @Description: <功能详细描述>
	 * @author: liuyu
	 * @date: 2017年3月3日 下午4:47:12
	 * @param roleId
	 * @return
	 * @see com.zp.service.IResourcesService#queryByRole(java.lang.String)
	 */
	@Override
	public List<Resources> queryByRole(String roleId) throws Exception{
		List<Resources> resList = null ; 
		try{
			List<AuthProjectRole> list = authProjectMapper.queryProjectRoleByRoleId(roleId);
			resList = new ArrayList<Resources>();
			for (AuthProjectRole authProjectRole : list) {
				List<Resources> ls = resourcesMapper.queryByProKey(authProjectRole.getProjectId());
				resList.addAll(ls);
			}
		}catch(Exception e ){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PERROLE);
		}
		return resList;
	}

	/**
	 * 
	 * <增加角色和权限>
	 * 
	 * @Title: saveRoleRescours
	 * @Description: <功能详细描述>
	 * @author: qinrenchuan
	 * @date: 2017年3月3日 下午4:50:58
	 * @param roleId
	 * @param list
	 * @see com.zp.service.IResourcesService#saveRoleRescours(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void saveRoleRescours(String roleId, List<String> list)throws Exception {
		try{
			resourcesMapper.deleteRoleRescours(roleId);
			for (String rId : list) {
				if (!"".equals(rId) && rId != null) {
					ResourceRoles resourceRoles = new ResourceRoles();
					resourceRoles.setRescId(Integer.parseInt(rId));
					resourceRoles.setRoleId(Integer.parseInt(roleId));
					resourcesMapper.saveRoleRescours(resourceRoles);
				}
			}
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_PERPROROLE);
		}
	}

	/**
	 * 
	 * <校验资源key唯一性>
	 * 
	 * @Title: queryResKey
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年3月1日 下午2:59:49
	 * @param resKey
	 * @return
	 * @throws AuthorityException
	 * @see com.zp.service.IResourcesService#queryResKey(java.lang.String)
	 */
	@Override
	public List<Resources> queryResKey(String resKey) throws AuthorityException {
		try {
			return resourcesMapper.queryResKey(resKey);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_CHECK_RES_KEY);
		}
	}

	/**
	 * 
	 * <编辑----保存>
	 * 
	 * @Title: modify
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年3月1日 下午6:07:02
	 * @param resources
	 * @throws AuthorityException
	 * @see com.zp.service.IResourcesService#modify(com.zp.entity.Resources)
	 */
	@Override
	public void modify(Resources resources) throws AuthorityException {
		try {
			resourcesMapper.update(resources);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_UPDATE_RESOURCES);
		}
	}

}
