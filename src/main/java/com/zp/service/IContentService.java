/**  
 * Copyright © 2017众品集团. All rights reserved.
 *
 * @Title: IContentService.java
 * @Prject: zp-auth
 * @Package: com.zp.service
 * @Description: <功能详细描述>
 * @author: chenbaiyu  
 * @date: 2017年5月4日 下午6:40:41
 * @version: V1.0  
 */
package com.zp.service;

import java.util.List;

import com.zp.entity.CmsContentinfo;

/**
 * <一句话功能简述>
 * @Title: IContentService.java
 * @Description: <功能详细描述>
 * @author  chenbaiyu
 * @date 2017年5月4日下午6:40:41
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IContentService {

	/**
	 * 
	 * <新增内容>
	 * @Title: addContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月2日 下午6:37:50 
	 * @param content
	 * @see [类、类#方法、类#成员]
	 */
	public String addContent(int userId,CmsContentinfo content,Integer isPreview);
	/**
	 * 
	 * <查询内容>
	 * @Title: contentList
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月2日 下午6:38:08 
	 * @param content
	 * @param pv
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<CmsContentinfo> contentList(CmsContentinfo content,Integer page, Integer pageSize);
	
	/**
	 * 更新内容
	 * <一句话功能简述>
	 * @Title: updateContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月5日 下午5:19:42 
	 * @param content
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateContent(int userId,CmsContentinfo content);
	
	/**
	 * 根据内容id查询
	 * <一句话功能简述>
	 * @Title: getContentById
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月8日 下午3:56:42 
	 * @param contentId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	CmsContentinfo getContentById(String contentId);
	
	/**
	 * 删除内容
	 * <一句话功能简述>
	 * @Title: deleteContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月9日 下午1:51:31 
	 * @param contentId
	 * @see [类、类#方法、类#成员]
	 */
	void deleteContent(String contentId);
	
	/**
	 * 
	 * <查询所有>
	 * @Title: queryContentList
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月17日 上午9:58:44 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<CmsContentinfo> queryContentList(String columnId,Integer size);
	
}
