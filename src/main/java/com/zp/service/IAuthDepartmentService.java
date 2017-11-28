/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: IAuthDepartmentService.java
 * @Prject: llmj-authority
 * @Package: com.llmj.service
 * @Description: <功能详细描述>
 * @author: songjunyi  
 * @date: 2017年2月17日 下午2:27:09
 * @version: V1.0  
 */
package com.zp.service;

import java.util.List;
import java.util.Map;

import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;

/**
 * <一句话功能简述>
 * @Title: IAuthDepartmentService.java
 * @Description: <功能详细描述>
 * @author  songjunyi
 * @date 2017年2月17日下午2:27:09
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IAuthDepartmentService {

	/**
	 * 
	 * <工厂列表>
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:53:48 
	 * @param authDepartment
	 * @param page
	 * @param pageSize
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthDepartment> queryList(AuthDepartment authDepartment, Integer page, Integer pageSize) throws Exception;

	/**
	 * 
	 * <根据工厂id删除工厂>
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:57:28 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void deleteById(Integer id) throws Exception;

	/**
	 * 
	 * <根据工厂id判断工厂是否被用户使用>
	 * @Title: checkDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:56:05 
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthUserEntity> checkDepartment(Map<Object, Object> map) throws Exception;

	/**
	 * 
	 * <新增工厂>
	 * @Title: addDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:59:15 
	 * @param authDepartment
	 * @see [类、类#方法、类#成员]
	 */
	void addDepartment(AuthDepartment authDepartment) throws Exception;

	/**
	 * 
	 * <根据工厂id查询工厂信息>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午11:02:17 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthDepartment queryById(String id) throws Exception;

	/**
	 * 
	 * <编辑工厂>
	 * @Title: editDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午11:00:42 
	 * @param authDepartment
	 * @see [类、类#方法、类#成员]
	 */
	void editDepartment(AuthDepartment authDepartment) throws Exception;

}
