/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AuthDepartmentMapper.java
 * @Prject: pss-authority
 * @Package: com.pss.mapper
 * @Description: <功能详细描述>
 * @author: songjunyi  
 * @date: 2017年2月17日 下午2:31:35
 * @version: V1.0  
 */
package com.zp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.AuthDepartment;

/**
 * <一句话功能简述>
 * @Title: AuthDepartmentMapper.java
 * @Description: <功能详细描述>
 * @author  songjunyi
 * @date 2017年2月17日下午2:31:35
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface AuthDepartmentMapper {

	/**
	 * 
	 * <工厂列表>
	 * @Title: queryList
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:54:37 
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<AuthDepartment> queryList(Map<String, Object> map);

	/**
	 * 
	 * <根据工厂id删除工厂>
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:57:50 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void deleteById(Integer id);

	/**
	 * 
	 * <新增工厂>
	 * @Title: addDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:59:44 
	 * @param authDepartment
	 * @see [类、类#方法、类#成员]
	 */
	void addDepartment(AuthDepartment authDepartment);

	/**
	 * 
	 * <根据工厂id查询工厂信息>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午11:02:57 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AuthDepartment queryById(String id);

	/**
	 * 
	 * <编辑工厂>
	 * @Title: editDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午11:01:08 
	 * @param authDepartment
	 * @see [类、类#方法、类#成员]
	 */
	void editDepartment(AuthDepartment authDepartment);

}
