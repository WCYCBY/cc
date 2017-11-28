package com.zp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsColumn;
import com.zp.entity.CmsColumnContent;
import com.zp.entity.CmsContentinfo;
import com.zp.service.CmsColumService;
import com.zp.service.ICmsColumnContentService;
import com.zp.service.IContentService;
import com.zp.utils.Constant;
import com.zp.utils.FreeMarkerUtil;

/**
 * <一句话功能简述>
 * @Title: CmsPublishController.java
 * @Description: <功能详细描述>
 * @author  caoju
 * @date 2017年3月6日下午3:06:45
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/mj/cmsPublishCtl/")
public class CmsPublishController extends BaseController{
	
	public static final String COLUMN_PATH = "/zp/zp_web/bidcenter/html/column";
	public static final String PREVIEW_COLUMN_PATH = "/zp/zp_web/bidcenter/html/preview/column";
	public static final String INDEX_V = "V";
	
	@Resource
	private CmsColumService columnService;
	@Resource
	private ICmsColumnContentService columnContentService;
	@Resource
	private IContentService contentService;
	
	
	/**
	 * CMS发布或者预览
	 * <一句话功能简述>
	 * @Title: publishOrPreview
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年3月6日 下午3:08:10 
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("publishOrPreview")
	public void publishOrPreview(int columnId, String type
			,HttpServletResponse response){
		JSONObject msg = new JSONObject();
		int userId = (Integer) request.getAttribute("userId");
		Map<String, Object> mapRoot = new HashMap<String, Object>();
		CmsColumn column = columnService.queryById(columnId);
		String flag = "";
		if ((column.getType()) == 1) {//图片类型
			List<CmsColumnContent> columnContentList = columnContentService.queryColumnContent(columnId);
			if(columnContentList == null || columnContentList.size() == 0){
				msg.put("code", 201);
				sendAjaxResponse(response, msg);
				return;
			}
			mapRoot.put("columnImageList", columnContentList);
			if("realtime_order_right".equals(column.getName())){
				flag = "#1";
			}else if ("introduce_left".equals(column.getName())) {
				flag = "#2";
			}else if ("partner".equals(column.getName())) {
				flag = "#3";
			}
		}else if(column.getType() == 2){
			List<CmsContentinfo> contentList = contentService.queryContentList(String.valueOf(columnId), 7);
			if(contentList == null || contentList.size() == 0){
				msg.put("code", 201);
				sendAjaxResponse(response, msg);
				return;
			}
			mapRoot.put("contentList", contentList);
		}
		
		if ("1".equals(type)) {//预览
			String fileName = column.getName() + ".shtml";
			try {
				FreeMarkerUtil.createrHtml(mapRoot, column.getFtl(), PREVIEW_COLUMN_PATH + File.separator
						+ fileName);
			} catch (Exception e) {
				LOGGER.error(fileName + "栏目预览失败，异常原因：" + e + "	失败版块：" + column.getName());
				msg.put("code", 500);
				sendAjaxResponse(response, msg);
				return;
			}
			msg.put("code", 200);
			msg.put("url",
					Constant.PREVIEW_ACCESSPATH + "?" + INDEX_V + "="
							+ System.currentTimeMillis() + flag);
			
		}else {//发布
			String fileName = column.getName() + ".shtml";
			try {
				FreeMarkerUtil.createrHtml(mapRoot, column.getFtl(), COLUMN_PATH + File.separator
						+ fileName);
				column.setIsrelease(1);
				column.setUpdater(userId);
				columnService.updateColumn(column);
			} catch (Exception e) {
				LOGGER.error(fileName + "栏目发布失败，异常原因：" + e + "	失败版块：" + column.getName());
				msg.put("code", 500);
				sendAjaxResponse(response, msg);
				return;
			}
			msg.put("code", 200);
			msg.put("url",
					Constant.ACCESSPATH + "?" + INDEX_V + "="
							+ System.currentTimeMillis() + flag);
		}
		sendAjaxResponse(response, msg);
		
		
	}
}
