package com.zp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zp.entity.CmsColumnContent;
import com.zp.mapper.CmsColumnContentMapper;
import com.zp.service.ICmsColumnContentService;

/**
 * <图片实现类>
 * 
 * @Title: CmsColumnServiceImpl.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日下午2:01:12
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Transactional
public class CmsColumnContentServiceImpl implements ICmsColumnContentService {
	@Autowired
	private CmsColumnContentMapper columnContentMapper;

	/**
	 * 
	 * <根据栏目id查询栏目下的所有图片>
	 * 
	 * @Title: queryColumnContent
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年3月7日 下午6:23:53
	 * @param columnId
	 * @return
	 * @see com.llmj.service.ICmsColumnContentService#queryColumnContent(java.lang.Integer)
	 */
	@Override
	public List<CmsColumnContent> queryColumnContent(Integer columnId) {
		return columnContentMapper.queryColumnContent(columnId);
	}

	/**
	 * 
	 * <编辑数据-----保存图片>
	 * 
	 * @Title: addColumnContent
	 * @Description: <功能详细描述>
	 * @author: yanglu
	 * @date: 2017年3月7日 下午2:34:04
	 * @param columnContents
	 * @return
	 * @see com.llmj.service.ICmsColumnContentService#addColumnContent(java.util.List)
	 */
	@Override
	public int addColumnContent(List<CmsColumnContent> columnContents) {
		int flag = 0;
		columnContentMapper.deleteColumnContent(columnContents.get(0).getColumnId());
		for (CmsColumnContent cmsColumnContent : columnContents) {
			if (cmsColumnContent.getPicUrl() != null && cmsColumnContent.getPicUrl() != "") {
				flag = columnContentMapper.addColumnContent(cmsColumnContent);
			}
		}
		return flag;
	}

}
