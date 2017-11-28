package com.zp.service.impl;

import com.github.pagehelper.PageHelper;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.entity.UserRoles;
import com.zp.mapper.AuthDepartmentMapper;
import com.zp.mapper.AuthRoleMapper;
import com.zp.mapper.AuthUserMapper;
import com.zp.service.AuthUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Title:
 * @Description: <系统用户>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
	private AuthDepartmentMapper authDepartmentMapper;
    @Autowired
	private AuthRoleMapper roleMapper;
    /**
     * @Title
     * @Description: <根据用户名查询用户>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @Override
    public AuthUserEntity queryUserName(String userName) throws Exception {
        return authUserMapper.queryUserName(userName);
    }
    
    /**
     * @Title
     * @Description: <修改密码>
     * @author qrc
     * @date 2017/2/23
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void updatePassword(String userId, String passwd) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("passwd", passwd);
        authUserMapper.updatePassword(map);
    }
    

	/**
	 * 根据id查
	 * <一句话功能简述>
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author: qinrenchuan
	 * @date: 2016年7月29日 下午9:34:43
	 * @param id
	 * @return
	 * @see com.mj.service.IUserService#getById(java.lang.String)
	 */
	public AuthUserEntity getById(String id) throws Exception{
		AuthUserEntity user = null;
		try {
			user = authUserMapper.getById(id);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_BYID_USER);
		}
		return user;
	}

	/**
	 * 查列表
	 * <一句话功能简述>
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年2月17日 下午3:18:42
	 * @param user
	 * @return
	 * @see com.pss.service.IUserService#query(com.zp.entity.AuthUserEntity)
	 */
	public List<AuthUserEntity> query(int page, int pageSize,AuthUserEntity user) throws Exception{
		PageHelper.startPage(page,pageSize);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("t", user);
		List<AuthUserEntity> list = null;
		try {
			list = authUserMapper.query(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_LIST_USER);
		}
		return list;
	}

	/**
	 * 查询所有工厂
	 * <一句话功能简述>
	 * @Title: queryDeptList
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年2月27日 下午2:26:14
	 * @param authDepartment
	 * @return
	 * @see com.zp.service.AuthUserService#queryDeptList(com.zp.entity.AuthDepartment)
	 */
	public List<AuthDepartment> queryDeptList(AuthDepartment authDepartment) throws Exception{
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("authDepartment", authDepartment);
		List<AuthDepartment> deptList = null;
		try {
			deptList = authDepartmentMapper.queryList(map);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_DEPARTMENT);
		}
		return deptList;
	}

	/**
	 * 修改用户信息
	 * <一句话功能简述>
	 * @Title: updateUser
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:42:50
	 * @param user
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#updateUser(com.zp.entity.AuthUserEntity)
	 */
	public void updateUser(AuthUserEntity user) throws Exception{
		try {
			authUserMapper.updateUser(user);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_UPDATE_USER);
		}
	}

	/**
	 * 添加用户
	 * <一句话功能简述>
	 * @Title: addUser
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:43:00
	 * @param user
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#addUser(com.zp.entity.AuthUserEntity)
	 */
	public void addUser(AuthUserEntity user) throws Exception{
		try {
			authUserMapper.addUser(user);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_ADD_USER);
		}
	}

	/**
	 * 删除用户,整理是修改is_del字段,不是真删除
	 * <一句话功能简述>
	 * @Title: updateDel
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:43:09
	 * @param userId
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#updateDel(java.lang.String)
	 */
	public void updateDel(String userId) throws Exception{
		try {
			authUserMapper.updateDel(userId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_USER);
		}
	}

	/**
	 * 删除用户以后，删除其对应的角色
	 * <一句话功能简述>
	 * @Title: delUserRole
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:43:46
	 * @param userId
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#delUserRole(java.lang.String)
	 */
	public void delUserRole(String userId) throws Exception{
		try {
			authUserMapper.deleteUserRole(userId);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_ROLE_USER);
		}
	}

	/**
	 * 查询用户角色
	 * <一句话功能简述>
	 * @Title: findRoles
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:44:05
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#findRoles()
	 */
	public List<Roles> findRoles() throws Exception{
		List<Roles> roleList = null;
		try {
			roleList = authUserMapper.findRoles();
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_ROLES_USER);
		}
		return roleList;
	}

	/**
	 * 分配用户角色
	 * <一句话功能简述>
	 * @Title: saveUserRole
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:44:16
	 * @param userRoles
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#saveUserRole(com.zp.entity.UserRoles)
	 */
	public void saveUserRole(UserRoles userRoles) throws Exception{
		try {
			authUserMapper.deleteUserRole(userRoles.getUserId().toString());
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_DELETE_ROLE_USER);
		}
		try {
			authUserMapper.saveUserRole(userRoles);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_SAVE_ROLE_USER);
		}
		Roles role=roleMapper.queryById(userRoles.getRoleId().toString());
		AuthUserEntity user = null;
		try {
			user = authUserMapper.getById(userRoles.getUserId().toString());
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_BYID_USER);
		}
		if("超级管理员".equals(role.getName())){
			user.setLevel(0);
		}else if("系统管理员".equals(role.getName())){
			user.setLevel(1);
		}else{
			user.setLevel(2);
		}
		authUserMapper.updateUserLevel(user);
	}

	/**
	 * 根据用户名查询用户是否存在
	 * <一句话功能简述>
	 * @Title: querySingleUser
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月3日 下午4:44:26
	 * @param userName
	 * @return
	 * @throws Exception
	 * @see com.zp.service.AuthUserService#querySingleUser(java.lang.String)
	 */
	public AuthUserEntity querySingleUser(String userName) throws Exception{
		AuthUserEntity user;
		try {
			user = authUserMapper.queryUserName(userName);
		} catch (Exception e) {
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_BYNMAE_USER);
		}
		return user;
	}
	
}
