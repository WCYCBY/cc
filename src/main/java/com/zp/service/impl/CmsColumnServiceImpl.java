package com.zp.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.AuthorityException;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsColumn;
import com.zp.entity.CmsContentinfo;
import com.zp.mapper.AuthCmsColumnMapper;
import com.zp.service.CmsColumService;
import com.zp.service.IContentService;
import com.zp.utils.FreeMarkerUtil;

@Service
public class CmsColumnServiceImpl implements CmsColumService {
	
	public static Logger LOGGER = LoggerFactory.getLogger(CmsColumnServiceImpl.class);
	public static final String COLUMN_PATH = "/zp/zp_web/bidcenter/html/column";
	
	@Autowired
	private AuthCmsColumnMapper authCmsColumnMapper;
	@Autowired
	private IContentService	contentService;
	
	@Override
	public List<CmsColumn> columnList(CmsColumn column) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("t", column);	
		List<CmsColumn> list = null;
		try{
			list = authCmsColumnMapper.columnList(map);
		}catch(Exception e){
			throw new AuthorityException(AuthorityErrorCodeEnum.ERROR_QUERY_CMSLIST);
		}
		return list;
	}

	@Override
	public void addColumn(int userId, CmsColumn column) {
		try {
			column.setCreater(userId);
			// 如果类型是图片类型直接添加到图片栏目表中
			authCmsColumnMapper.add(column);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public CmsColumn queryOnlyName(String name) {
		return authCmsColumnMapper.queryOnlyName(name);
	}


	/**
	 * 根据id查询
	 * <一句话功能简述>
	 * @Title: queryById
	 * @Description: <功能详细描述>
	 * @author: caoju
	 * @date: 2017年3月6日 下午4:19:14
	 * @param id
	 * @return
	 * @see com.zp.service.CmsColumService#queryById(int)
	 */
	public CmsColumn queryById(int id) {
		return authCmsColumnMapper.queryById(id);
	}

	@Override
	public void updateColumn(CmsColumn column) {
		authCmsColumnMapper.updateColumn(column);
		
	}

	@Override
	public void delete(String id) {
		authCmsColumnMapper.deleteColumn(id);
		
	}

	@Override
	public int createColumnHtml(CmsContentinfo content) {
		// TODO Auto-generated method stub
		String fileName = null;
		CmsColumn column = null;
		try {
			Map<String, Object> mapRoot = new HashMap<String, Object>();
			List<CmsContentinfo> contentList = contentService.queryContentList(content.getColumsId(), 7);
			mapRoot.put("contentList", contentList);
			column = authCmsColumnMapper.queryById(Integer.parseInt(content.getColumsId()));
			fileName = column.getName() + ".shtml";
			FreeMarkerUtil.createrHtml(mapRoot, column.getFtl(), COLUMN_PATH + File.separator
					+ fileName);
			column.setIsrelease(1);
			updateColumn(column);
		} catch (Exception e) {
			LOGGER.error(fileName + "栏目发布失败，异常原因：" + e + "	失败版块：" + column.getName());
			return 0;
		}
		return 1;
	}

}
