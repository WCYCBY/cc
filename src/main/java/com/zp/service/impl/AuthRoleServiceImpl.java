package com.zp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.mapper.AuthRoleMapper;
import com.zp.service.AuthRoleService;

@Service
public class AuthRoleServiceImpl implements AuthRoleService{
	
	@Autowired
	private AuthRoleMapper authRoleMapper;
	
	/**
	 * 
	 * 查询列表
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:41:01
	 * @param role
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#query(com.zp.entity.Roles)
	 */
	@Override
	public List<Roles> query(Roles role) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("t", role);
		List<Roles> list = null;
		try{
			list =  authRoleMapper.queryRole(map);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_ROLE);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 添加
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:41:16
	 * @param role
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#add(com.zp.entity.Roles)
	 */
	@Override
	public void add(Roles role) throws Exception {
		try{
			authRoleMapper.addRole(role);
			
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_ROLE);
		}
		
		
	}
	
	/**
	 * 
	 * 通过id查找角色
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:41:31
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#getById(java.lang.String)
	 */
	@Override
	public Roles getById(String roleId) throws Exception {
		Roles role = null;
		try{
			role = authRoleMapper.queryById(roleId);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.EROOR_QUERY_ROLEBYID);
		}
		
		return role;
	}
	
	
	/**
	 * 
	 * 更新角色
	 * @Title: modify
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:41:47
	 * @param role
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#modify(com.zp.entity.Roles)
	 */
	@Override
	public void modify(Roles role) throws Exception {
		try{
			authRoleMapper.update(role);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_UPDATE_ROLEBYID);
		}
		
		
	}
	
	
	/**
	 * 
	 *删除角色
	 * @Title: delete
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:42:05
	 * @param roleId
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#delete(java.lang.String)
	 */
	@Override
	public void delete(String roleId)throws Exception {
		try{
			authRoleMapper.deleteById(roleId);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_ROLE);
		}
		
		
	}
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: queryUserRolesByRoleId
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:42:24
	 * @param rolesId
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#queryUserRolesByRoleId(java.lang.String)
	 */
	@Override
	public List<AuthUserEntity> queryUserRolesByRoleId(String rolesId) throws Exception {
		List<AuthUserEntity> us = null;
		try {
			us = authRoleMapper.queryUserRolesByRoleId(rolesId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_UPROLEBYID);
		}
		return us;
	}
	
	/**
	 * 
	 * 验证角色名称是否重复
	 * @Title: querySingleRolesByName
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:42:40
	 * @param rolesName
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#querySingleRolesByName(java.lang.String)
	 */
	@Override
	public Roles querySingleRolesByName(String rolesName) throws Exception {
		Roles r = null;
		try{
			 r = authRoleMapper.queryByName(rolesName);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_ROLENAME);
		}
		return r;
		
	}
	
	
	/**
	 * 
	 * 验证角色key是否重复
	 * @Title: querySingleRolesByKey
	 * @Description: <功能详细描述>
	 * @author: LiuYu
	 * @date: 2017年3月3日 下午4:43:00
	 * @param rolesKey
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthRoleService#querySingleRolesByKey(java.lang.String)
	 */
	@Override
	public Roles querySingleRolesByKey(String rolesKey) throws Exception{
		Roles r = null;
		try{
			r = authRoleMapper.queryByRolesKey(rolesKey);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_ROLEKEY);
		}
		return r;
	}

}
