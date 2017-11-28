/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CmsColumnMapper.java
 * @Prject: llmj-authority_boot
 * @Package: com.llmj.mapper
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年3月6日 下午2:14:58
 * @version: V1.0  
 */
package com.zp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.CmsColumnContent;

/**
 * <栏目图片mapper>
 * 
 * @Title: CmsColumnMapper.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日下午2:14:58
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface CmsColumnContentMapper {
	/**
	 * 
	 * <根据栏目id查询栏目下的所有图片>
	 * 
	 * @Title: queryColumnContent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午6:24:50
	 * @param columnId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<CmsColumnContent> queryColumnContent(Integer columnId);

	/**
	 * 
	 * <根据栏目id删除图片>
	 * 
	 * @Title: deleteColumnContent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午6:25:04
	 * @param columnId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	void deleteColumnContent(Integer columnId);

	/**
	 * 
	 * <编辑数据-----保存>
	 * 
	 * @Title: addColumnContent
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午6:25:31
	 * @param columnContent
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int addColumnContent(CmsColumnContent columnContent);
}
