/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ICmsColumnService.java
 * @Prject: llmj-authority_boot
 * @Package: com.llmj.service
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年3月6日 下午2:13:40
 * @version: V1.0  
 */
package com.zp.service;

import java.util.List;

import com.zp.entity.CmsColumnContent;

/**
 * <图片service接口>
 * 
 * @Title: ICmsColumnService.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日下午2:13:40
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ICmsColumnContentService {
	/**
	 * 
	 * <根据栏目id查询栏目下的所有图片>
	 * 
	 * @Title: queryColumnContent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午6:22:04
	 * @param columnId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<CmsColumnContent> queryColumnContent(Integer columnId);

	/**
	 * 
	 * <编辑数据保存>
	 * 
	 * @Title: addColumnContent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午6:22:39
	 * @param columnContents
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int addColumnContent(List<CmsColumnContent> columnContents);
}
