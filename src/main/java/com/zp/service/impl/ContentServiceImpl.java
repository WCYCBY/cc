package com.zp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsContentinfo;
import com.zp.mapper.AuthCmsColumnMapper;
import com.zp.mapper.CmsColumnMapper;
import com.zp.mapper.CmsContentinfoMapper;
import com.zp.service.CmsColumService;
import com.zp.service.IContentService;

/**
 * <一句话功能简述>
 * @Title: ContentServiceImpl.java
 * @Description: <功能详细描述>
 * @author  chenbaiyu
 * @date 2017年5月4日下午7:05:58
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ContentServiceImpl implements IContentService {

	public static Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);
	
	@Resource
	private CmsContentinfoMapper contentMapper;

	@Resource
	private AuthCmsColumnMapper columnMapper;
	
	/**
	 * <添加内容，或者预览返回要生成静态页的模板>
	 * @Title: addContent
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param userId
	 * @param content
	 * @param isPreview
	 * @return
	 * @see com.zp.service.IContentService#addContent(int, com.zp.entity.CmsContentinfo, java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String addContent(int userId, CmsContentinfo content, Integer isPreview) {
		String ftlForContent = null;
		try {
			if (isPreview !=null && isPreview == 0) {
				content.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				content.setCreateTime(new Date());
				content.setUpdateTime(new Date());
				contentMapper.insert(content);
			}
			ftlForContent = columnMapper.queryById(Integer.parseInt(content.getColumsId())).getFtlForContent();
		} catch (Exception e) {
			LOGGER.error("添加内容失败");
			throw new RuntimeException(e.getMessage());
		}
		return ftlForContent;
	}

	/**
	 * <一句话功能简述>
	 * @Title: contentList
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param content
	 * @param page
	 * @param pageSize
	 * @return
	 * @see com.zp.service.IContentService#contentList(com.zp.entity.CmsContentinfo, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CmsContentinfo> contentList(CmsContentinfo content, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		List<CmsContentinfo> list;
		try {
			PageHelper.startPage(page,pageSize);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", content);
			list = contentMapper.queryList(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	/**
	 * <一句话功能简述>
	 * @Title: updateContent
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param userId
	 * @param content
	 * @return
	 * @see com.zp.service.IContentService#updateContent(int, com.zp.entity.CmsContentinfo)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String updateContent(int userId, CmsContentinfo content) {
		// TODO Auto-generated method stub
		String ftlForContent = null;
		try {
			int i = contentMapper.updateByPrimaryKeySelective(content);
			if(i != 0){
				ftlForContent = columnMapper.queryById(Integer.parseInt(content.getColumsId())).getFtlForContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return ftlForContent;
	}

	/**
	 * <一句话功能简述>
	 * @Title: getContentById
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param contentId
	 * @return
	 * @see com.zp.service.IContentService#getContentById(java.lang.String)
	 */
	@Override
	public CmsContentinfo getContentById(String contentId) {
		// TODO Auto-generated method stub
		CmsContentinfo contentinfo = null;
		try {
			contentinfo = contentMapper.selectByPrimaryKey(contentId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return contentinfo;
	}

	/**
	 * <一句话功能简述>
	 * @Title: deleteContent
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param contentId
	 * @see com.zp.service.IContentService#deleteContent(java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteContent(String contentId) {
		try {
			contentMapper.deleteByPrimaryKey(contentId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * <一句话功能简述>
	 * @Title: queryContentList
	 * @Description: <功能详细描述>
	 * @author: chenbaiyu
	 * @date: 2017年5月4日 下午7:05:58
	 * @param columnId
	 * @param size
	 * @return
	 * @see com.zp.service.IContentService#queryContentList(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<CmsContentinfo> queryContentList(String columnId, Integer size) {
		// TODO Auto-generated method stub
		List<CmsContentinfo> list = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("size", size);
			map.put("columsId", columnId);
			list = contentMapper.queryAll(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

}
