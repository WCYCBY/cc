package com.zp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.CmsColumn;

@Mapper
public interface AuthCmsColumnMapper {

	List<CmsColumn> columnList(Map<String, Object> map);

	void add(CmsColumn column);


	CmsColumn queryOnlyName(String name);

	/**
	 * 
	 * 根据id查询
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年3月6日 下午4:07:26 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	CmsColumn queryById(int id);

	void updateColumn(CmsColumn column);

	void deleteColumn(String id);
	
}
