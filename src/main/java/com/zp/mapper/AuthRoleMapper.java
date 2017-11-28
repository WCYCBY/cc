package com.zp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthRoleMapper {
	
	
	public List<Roles> queryRole(Map<String, Object> map);

	public void addRole(Roles role);

	public Roles queryById(String roleId);

	public void update(Roles role);

	public void deleteById(String roleId);

	public List<AuthUserEntity> queryUserRolesByRoleId(String rolesId);

	public Roles queryByName(String rolesName);

	public Roles queryByRolesKey(String rolesKey);
	

	/*List<AuthUserEntity> queryUserRolesByRoleId(String rolesId);
	

	Roles getById(String roleId);
	
	
	
	Roles queryByRolesKey(String rolesKey);
	
	

	void deleteById(String roleId);*/
	
}
