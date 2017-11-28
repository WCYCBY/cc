/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CmsColumnMapper.java
 * @Prject: zp-authority_boot
 * @Package: com.zp.mapper
 * @Description: <功能详细描述>
 * @author: yanglu  
 * @date: 2017年3月6日 下午2:14:58
 * @version: V1.0  
 */
package com.zp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.CmsColumn;

/**
 * <一句话功能简述>
 * @Title: CmsColumnMapper.java
 * @Description: <功能详细描述>
 * @author  yanglu
 * @date 2017年3月6日下午2:14:58
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface CmsColumnMapper {
	public CmsColumn getColumnById(String columnId);
}
