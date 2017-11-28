package com.zp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;
import com.zp.mapper.AuthDepartmentMapper;
import com.zp.mapper.AuthUserMapper;
import com.zp.service.IAuthDepartmentService;

/**
 * <一句话功能简述>
 * @Title: AuthDepartmentServiceImpl.java
 * @Description: <功能详细描述>
 * @author  songjunyi
 * @date 2017年2月17日下午2:28:30
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class AuthDepartmentServiceImpl implements IAuthDepartmentService{
	
	@Autowired
	private AuthDepartmentMapper authDepartmentMapper;
	
	@Autowired
	private AuthUserMapper authUserMapper;

	/**
	 * 
	 * <工厂列表>
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午10:54:15
	 * @param authDepartment
	 * @param page
	 * @param pageSize
	 * @return
	 * @see com.llmj.service.IAuthDepartmentService#queryList(com.llmj.entity.AuthDepartment, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<AuthDepartment> queryList(AuthDepartment authDepartment,Integer page,Integer pageSize) throws Exception {
		PageHelper.startPage(page,pageSize);
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("authDepartment", authDepartment);
		List<AuthDepartment> list = null;
		try {
			list = authDepartmentMapper.queryList(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_DEPARTMENT);
		}
		return list;
	}

	/**
	 * 
	 * <根据工厂id删除工厂>
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午10:57:39
	 * @param id
	 * @see com.llmj.service.IAuthDepartmentService#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) throws Exception{
		try {
			authDepartmentMapper.deleteById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE＿DEPARTMENT);
		}
		
	}

	/**
	 * 
	 * <根据工厂id判断工厂是否被用户使用>
	 * @Title: checkDepartment
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午10:56:16
	 * @param map
	 * @return
	 * @see com.llmj.service.IAuthDepartmentService#checkDepartment(java.util.Map)
	 */
	@Override
	public List<AuthUserEntity> checkDepartment(Map<Object, Object> map) throws Exception{
		List<AuthUserEntity> list = null;
		try {
			list = authUserMapper.query(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_CHECK_DEPARTMENT);
		}
		return list;
		
	}

	/**
	 * 
	 * <新增工厂>
	 * @Title: addDepartment
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午10:59:33
	 * @param authDepartment
	 * @see com.llmj.service.IAuthDepartmentService#addDepartment(com.llmj.entity.AuthDepartment)
	 */
	@Override
	public void addDepartment(AuthDepartment authDepartment) throws Exception{
		try {
			authDepartmentMapper.addDepartment(authDepartment);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD＿DEPARTMENT);
		}
		
	}

	/**
	 * 
	 * <根据工厂id查询工厂信息>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午11:02:46
	 * @param id
	 * @return
	 * @see com.llmj.service.IAuthDepartmentService#queryById(java.lang.String)
	 */
	@Override
	public AuthDepartment queryById(String id) throws Exception{
		AuthDepartment authDepartment = null;
		try {
			authDepartment = authDepartmentMapper.queryById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_DEPARTMENT);
		}
		return authDepartment;
	}

	/**
	 * 
	 * <编辑工厂>
	 * @Title: editDepartment
	 * @Description: <功能详细描述>
	 * @author: songjunyi
	 * @date: 2017年3月3日 上午11:00:58
	 * @param authDepartment
	 * @see com.llmj.service.IAuthDepartmentService#editDepartment(com.llmj.entity.AuthDepartment)
	 */
	@Override
	public void editDepartment(AuthDepartment authDepartment) throws Exception{
		try {
			authDepartmentMapper.editDepartment(authDepartment);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_EDIT＿DEPARTMENT);
		}
	}

}
