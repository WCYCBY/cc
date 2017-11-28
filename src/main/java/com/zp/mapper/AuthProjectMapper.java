/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthProjectMapper.java
 * @Prject: pss-authority
 * @Package: com.mj.mapper
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年8月26日 上午10:59:03
 * @version: V1.0  
 */
package com.zp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.AuthProject;
import com.zp.entity.AuthProjectRole;
import com.zp.entity.query.AuthProjectQuery;

/**
 * <一句话功能简述>
 * @Title: AuthProjectMapper.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月26日上午10:59:03
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface AuthProjectMapper  {

	/**
	 * 
	 * 查询项目列表
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午1:39:09 
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectQuery> queryList(Map<String, Object> map);

	/**
	 * 新增项目
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午3:59:48 
	 * @param authProject
	 * @see [类、类#方法、类#成员]
	 */
	void add(AuthProject authProject);

	/**
	 * 
	 * 根据用Key查询用户
	 * @Title: querySingleUserByName
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午4:30:53 
	 * @param
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthProjectQuery querySingleUserByKey(String key);

	/**
	 * 
	 * 查询所有项目列表
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月27日 下午4:49:29 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectQuery> queryAll();

	/**
	 * 根据id查询
	 * <一句话功能简述>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 上午10:34:29 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthProjectQuery queryById(Integer id);

	/**
	 * 修改项目
	 * <一句话功能简述>
	 * @Title: update
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 上午11:15:25 
	 * @param authProject
	 * @see [类、类#方法、类#成员]
	 */
	void update(AuthProject authProject);

	/**
	 * 根据id删除，删除后不可恢复
	 * <一句话功能简述>
	 * @Title: del
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 下午2:46:27 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void delById(Integer id);

	/**
	 * 根据角色id查询出项目与角色对应的列表
	 * <一句话功能简述>
	 * @Title: queryProjectRoleByRoleId
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月30日 下午1:52:32 
	 * @param roleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProjectRole> queryProjectRoleByRoleId(String roleId);

	/**
	 * 删除与相应角色关联的项目
	 * <一句话功能简述>
	 * @Title: delProRole
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月30日 下午3:12:19 
	 * @param roleId
	 * @see [类、类#方法、类#成员]
	 */
	void delProRole(String roleId);

	/**
	 * 分配权限
	 * <一句话功能简述>
	 * @Title: saveRoleProject
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月30日 下午3:21:07 
	 * @param authProjectRole
	 * @see [类、类#方法、类#成员]
	 */
	void saveRoleProject(AuthProjectRole authProjectRole);

	/**
	 * 根据项目id查询关联的用户角色
	 * <一句话功能简述>
	 * @Title: queryRoleByPro
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年9月7日 上午10:23:23 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Integer> queryRoleByPro(Integer id);

	/**
	 * @Title 
	 * @Description: <根据用户id查询该用户拥有权限的项目列表>
	 * @author qrc
	 * @date 2017/2/27
	 * @param 
	 * @return
	 * @see [类、类#方法、类#成员]
	*/
	List<AuthProjectQuery> queryProListByUserId(String userId);

	
	/**
	 * 根据角色id查询关联的project
	 * <一句话功能简述>
	 * @Title: queryRoleByPro
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年9月7日 上午10:23:23 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthProject> getRoleProject(String roleId);
}
