package com.zp.service;

import java.util.List;

import com.zp.entity.CmsColumn;
import com.zp.entity.CmsContentinfo;

public interface CmsColumService {
	
	/**
	 * 
	 *查询站点栏目列表
	 * @Title: columnList
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月6日 下午1:59:20 
	 * @param column
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<CmsColumn> columnList(CmsColumn column) throws Exception ;
	
	
	/**
	 * 
	 * 添加
	 * @Title: addColumn
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:24:53 
	 * @param userId
	 * @param column
	 * @see [类、类#方法、类#成员]
	 */
	void addColumn(int userId, CmsColumn column);

		
	/**
	 * 
	 * 校验名字是否重复
	 * @Title: queryOnlyName
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:24:34 
	 * @param name
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	CmsColumn queryOnlyName(String name);
	
	/**
	 * 根据id查询
	 * <一句话功能简述>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年3月6日 下午4:18:48 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	CmsColumn queryById(int id);
	
	/**
	 * 
	 * 更新
	 * @Title: updateColumn
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:24:24 
	 * @param column
	 * @see [类、类#方法、类#成员]
	 */
	void updateColumn(CmsColumn column);
	
	/**
	 * 
	 * 删除
	 * @Title: delete
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:24:13 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void delete(String id);
	
	
	/**
	 * 生成静态页
	 * <一句话功能简述>
	 * @Title: createColumnHtml
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2017年5月7日 上午10:57:27 
	 * @param columnId
	 * @param size
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	int createColumnHtml(CmsContentinfo content);
}
