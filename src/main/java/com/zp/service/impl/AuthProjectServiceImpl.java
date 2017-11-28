package com.zp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.entity.AuthProject;
import com.zp.entity.AuthProjectRole;
import com.zp.entity.Resources;
import com.zp.entity.query.AuthProjectQuery;
import com.zp.mapper.AuthProjectMapper;
import com.zp.mapper.AuthResourcesMapper;
import com.zp.service.IAuthProjectService;

/**
 * <一句话功能简述>
 * @Title: AuthProjectServiceImpl.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月26日上午10:58:03
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
@Transactional
public class AuthProjectServiceImpl implements IAuthProjectService {

	@Autowired
	private AuthProjectMapper authProjectMapper;
	@Autowired
	private AuthResourcesMapper resourcesMapper;
	@Override
	public List<AuthProjectQuery>  queryList(AuthProjectQuery authProject,Integer page,Integer pageSize)  throws Exception  {
		PageHelper.startPage(page,pageSize);
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("authProject", authProject);
		List<AuthProjectQuery> list = null;
		try {
			list = authProjectMapper.queryList(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return list;
	}
	
	@Override
	public void add(AuthProject authProject)  throws Exception {
		try {
			authProjectMapper.add(authProject);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_PROJECT);
		}
	}

	@Override
	public AuthProjectQuery querySingleUserByKey(String key) throws Exception {
		AuthProjectQuery authProjectQuery = null;
		try {
			authProjectQuery = authProjectMapper.querySingleUserByKey(key);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return authProjectQuery;
	}

	@Override
	public List<AuthProjectQuery> queryAll()   throws Exception {
		List<AuthProjectQuery> queryAll;
		try {
			queryAll = authProjectMapper.queryAll();
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return queryAll;
	}

	@Override
	public AuthProjectQuery queryById(Integer id)   throws Exception{
		AuthProjectQuery queryById;
		try {
			queryById = authProjectMapper.queryById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		} 
		return queryById;
	}

	@Override
	public void update(AuthProject authProject)  throws Exception  {
		try {
			authProjectMapper.update(authProject);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_UPDATE_PROJECT);
		}
	}

	@Override
	public void delById(Integer id)  throws Exception  {
		//根据项目id查询相关资源
		try {
			List<Resources> res = resourcesMapper.queryByProKey(id);
			for (Resources resources : res) {
				//删除资源角色关联表中的数据
				resourcesMapper.deleteRoleByResId(resources.getId().toString());
				//删除资源
				resourcesMapper.deleteById(resources.getId().toString());
			}
			//删除项目
			authProjectMapper.delById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_PROJECT);
		}
	}

	@Override
	public List<AuthProjectRole> queryProjectRoleByRoleId(String roleId)  throws Exception  {
		List<AuthProjectRole> roleByRoleId;
		try {
			roleByRoleId = authProjectMapper.queryProjectRoleByRoleId(roleId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return roleByRoleId;
	}

	@Override
	public List<Integer> queryRoleByPro(Integer id)  throws Exception  {
		List<Integer> roleByPro;
		try {
			roleByPro = authProjectMapper.queryRoleByPro(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return roleByPro;
	}

	@Override
	public List<AuthProjectQuery> queryProListByUserId(String userId)   throws Exception {
		List<AuthProjectQuery> listByUserId;
		try {
			listByUserId = authProjectMapper.queryProListByUserId(userId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return listByUserId;
	}

	@Override
	public List<AuthProject> queryProjectByRoleId(String roleId)  throws Exception  {
		List<AuthProject> roleProject;
		try {
			roleProject = authProjectMapper.getRoleProject(roleId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_PROJECT);
		}
		return roleProject;
	}
}
