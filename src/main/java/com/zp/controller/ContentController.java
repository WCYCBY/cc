/**  
 * Copyright © 2017众品集团. All rights reserved.
 *
 * @Title: ContentController.java
 * @Prject: zp-auth
 * @Package: com.zp.controller
 * @Description: <功能详细描述>
 * @author: chenbaiyu  
 * @date: 2017年5月4日 下午5:32:07
 * @version: V1.0  
 */
package com.zp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsContentinfo;
import com.zp.service.CmsColumService;
import com.zp.service.IContentService;
import com.zp.utils.Constant;
import com.zp.utils.FreeMarkerUtil;
import com.zp.utils.ImageUpload;
/**
 * <一句话功能简述>
 * @Title: ContentController.java
 * @Description: <功能详细描述>
 * @author  chenbaiyu
 * @date 2017年5月4日下午5:32:07
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/mj/content")
public class ContentController extends BaseController {

	@Resource
	private IContentService contentService;
	@Resource
	private CmsColumService iColumnService;

	public static final String STATIC_FAIL_NEWSDETAIL = "7";
	public static String ORIGINAL_PATH = "/original/";
	public static String ARTICLE = "/article/";
	public static String PREVIEW = "preview";
	public static String PATH = "zp/zp_web/bidcenter/content";
	/**
	 * 
	 * <新增内容>
	 * 
	 * @Title: addContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月2日 下午6:25:39
	 * @param content
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addContent")
	public String addContent(CmsContentinfo content) {
		int userId = (Integer) request.getAttribute("userId");
		//int userId = 526;
		Integer isPreview = 0;
		String ftl = contentService.addContent(userId, content, isPreview);
		if (ftl != null) {
			createPage(userId, content, ftl, isPreview);
		}
		// 触发首页新闻动态生成
		createNews(content);
		return "redirect:/mj/content/contentList.html?columnId=" + content.getColumsId() + "&page=" + 1;

	}

	/**
	 * 生成静态页面 <一句话功能简述>
	 * 
	 * @Title: createPage
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月5日 下午3:49:11
	 * @param content
	 * @param ftl
	 * @param isPreview
	 * @return 生成页面链接
	 * @see [类、类#方法、类#成员]
	 */
	public String createPage(int userId, CmsContentinfo content, String ftl, Integer isPreview) {
		Map<String, Object> m = new HashMap<String, Object>();
		content.setUpdateTime(new Date());
		m.put("content", content);
		// 文件名
		String fileName = null;
		try {
			if (isPreview == 0) {
				fileName =System.currentTimeMillis() + ".shtml";
				FreeMarkerUtil.createrHtml(m, ftl, File.separator+ PATH +File.separator + fileName);
				CmsContentinfo content2 = contentService.getContentById(content.getId());
				content2.setFileName(fileName);
				contentService.updateContent(userId, content2);
			} else if (isPreview == 1) {
				fileName = PREVIEW +"_"+ content.getTitle() + ".shtml";
				FreeMarkerUtil.createrHtml(m, ftl,
						File.separator + PATH + File.separator +PREVIEW + File.separator + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.NOTICE_HOST + File.separator  + File.separator + PREVIEW + File.separator + fileName + "?time="
				+ new Date().getTime();
	}

	/**
	 * 触发首页新闻动态生成 <一句话功能简述>
	 * 
	 * @Title: createNews
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月23日 上午10:10:49
	 * @see [类、类#方法、类#成员]
	 */
	public void createNews(CmsContentinfo content) {
		iColumnService.createColumnHtml(content);
	}

	/**
	 * <跳转到新增内容页>
	 * @Title: toContentAdd
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2017年5月5日 上午9:39:35 
	 * @param model
	 * @param colunmsId
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toContentAdd")
	public String toContentAdd(Model model,String columsId,String returnUrl){
		model.addAttribute("returnUrl",  returnUrl);
		model.addAttribute("columsId",  columsId);
		return "auth/cms/contentAdd";
	}
	/**
	 * 
	 * <查询内容>
	 * 
	 * @Title: contentList
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月2日 下午6:26:05
	 * @param model
	 * @param content
	 * @param pv
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("contentList")
	public String contentList(String columnId, Model model, CmsContentinfo content,  Integer page,
			HttpServletResponse res) {
		content.setColumsId(columnId);
		if(request.getParameter("page")!=null && !"".equals(request.getParameter("page"))){
			page =Integer.valueOf(request.getParameter("page"));
		}
		List<CmsContentinfo> list = contentService.contentList(content, page, 10);
		model.addAttribute("pageInfo", new PageInfo<CmsContentinfo>(list));
		model.addAttribute("content", content);
		model.addAttribute("list", list);
		return "auth/cms/contentList";
	}

	/**
	 * 更新内容 <一句话功能简述>
	 * 
	 * @Title: editContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月8日 上午11:22:07
	 * @param content
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("editContent")
	public String editContent(CmsContentinfo content) {
		int userId = (Integer) request.getAttribute("userId");
		//int userId =526;
		Integer isPreview = 0;
		String ftl = contentService.updateContent(userId, content);
		if (ftl != null) {
			createPage(userId, content, ftl, isPreview);
		}
		// 触发首页新闻动态生成
		createNews(content);
		return "redirect:/mj/content/contentList.html?columnId=" + content.getColumsId() + "&page=" + 1;
	}

	@RequestMapping("toContentEdit")
	public String queryColumnNameToEdit(Model model,String columsId,String returnUrl, String contentId) {
		CmsContentinfo content = contentService.getContentById(contentId);
		model.addAttribute("content", content);
		model.addAttribute("returnUrl",  returnUrl);
		model.addAttribute("columsId",  columsId);
		return "auth/cms/contentEdit";
	}
	
	@RequestMapping("deleteContent")
	public String deleteContent(String contentId) {
			CmsContentinfo content = contentService.getContentById(contentId);
			String columnId = content.getColumsId();
			contentService.deleteContent(contentId);
			// 触发首页新闻动态生成
			createNews(content);
			return "redirect:/mj/content/contentList.html?columnId=" + columnId + "&page=" + 1;
	}

	@RequestMapping("setRelease")
	public String setRelease(String contentId, Integer status) {
		int userId = (Integer) request.getAttribute("userId");
		CmsContentinfo content = contentService.getContentById(contentId);
		String columnId = content.getColumsId();
		if (status == 1) {
			content.setStatus(2);
		} else if (status == 2) {
			content.setStatus(1);
		}
		contentService.updateContent(userId, content);
		// 触发首页新闻动态生成
		createNews(content);
		return "redirect:/mj/content/contentList.html?columnId=" + columnId + "&page=" + 1;
	}

	@RequestMapping("setIsTop")
	public String setIsTop(String contentId, Integer isTop) {
		int userId = (Integer) request.getAttribute("userId");
			CmsContentinfo content = contentService.getContentById(contentId);
			String columnId = content.getColumsId();
			if (isTop == 1) {
				content.setIsTop(2);
			} else if (isTop == 2) {
				content.setIsTop(1);
			}
			contentService.updateContent(userId, content);
			// 触发首页新闻动态生成
			createNews(content);
			return "redirect:/mj/content/contentList.html?columnId=" + columnId + "&page=" + 1;
	}

	/**
	 * 生成预览页，并跳转至预览页面 <一句话功能简述>
	 * 
	 * @Title: previewContent
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月5日 下午3:35:50
	 * @param content
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("previewContent")
	@ResponseBody
	public String previewContent(CmsContentinfo content) {
		int userId = (Integer) request.getAttribute("userId");
		String url = null;
		try {
			//int userId = 526;
			Integer isPreview = 1;
			String ftl = contentService.addContent(userId, content, isPreview);
			url = createPage(userId, content, ftl, isPreview);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 
	 * <文本编辑器文件上传>
	 * 
	 * @Title: ueditImageUpload
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月18日 上午10:19:11
	 * @param upfile
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("ueditImageUpload")
	@ResponseBody
	public JSONObject ueditImageUpload(@RequestParam(value = "upfile", required = false) MultipartFile upfile) {
		String url = null;
		InputStream inputStream = null;
		ByteArrayOutputStream stream = null;
		int width = 0;
		int height = 0;
		try {
			BufferedImage io = ImageIO.read(upfile.getInputStream());
			width = io.getWidth();
			height = io.getHeight();
		} catch (Exception e) {
			// 经过p过的图片 格式ImageIO.read 不可识别，已流的形式读取
			try {
				inputStream = upfile.getInputStream();
				stream = new ByteArrayOutputStream();
				int num = 0;
				while ((num = inputStream.read()) != -1) {
					stream.write(num);
				}
			} catch (IOException e1) {
				LOGGER.error("ueditor上传图片异常：" + e);
			}
			ImageIcon image = new ImageIcon(stream.toByteArray());
			width = image.getIconWidth();
			height = image.getIconHeight();
		}
		try {
			url = upLoadImg(upfile, "newsImage", width, height);
		} catch (Exception e) {
			LOGGER.error("ueditor上传图片异常：" + e);
		}
		JSONObject json = new JSONObject();
		json.put("url",  "http://pic.gyl.zhongpin.cn"+ "/newsImage/"+ ORIGINAL_PATH +  url);
		json.put("upfile", upfile.getName());
		json.put("size", upfile.getSize());
		json.put("state", "SUCCESS");
		json.put("type", upfile.getContentType());
		json.put("originalName", upfile.getOriginalFilename());
		return json;
	}

	/**
	 * 
	 * <图片上传>
	 * 
	 * @Title: upLoadImg
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年8月18日 上午10:18:08
	 * @param file
	 * @param type
	 * @param width
	 * @param height
	 * @param map
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String upLoadImg(MultipartFile file, String type, int width, int height) throws Exception {
		String imgUrl = null;
		JSONObject dataInfo = new JSONObject();
		dataInfo.put("type", type);
		PostMethod postMethod = new PostMethod(Constant.IMAGES_SERVER_UPLOAD_API_URL);
		StringPart sp = new StringPart("data", dataInfo.toString());
		String returnStr = null;
		File f = null;
		try {
			String filePath = file.getOriginalFilename();
			String renameFile = UUID.randomUUID().toString().replace("-", "")
					+ filePath.substring(filePath.lastIndexOf("."), filePath.length());
			// 上传的文件
			f = new File(renameFile);
			// 转存文件
			file.transferTo(f);

			FilePart fp = new FilePart("files", f);

			Part[] parts = { fp, sp };

			// 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());

			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				returnStr = postMethod.getResponseBodyAsString();
				System.out.println(returnStr);
				JSONObject temp = JSONObject.parseObject(returnStr);
				if ("0".equals(temp.getString("recode"))) {
					/*JSONArray brandlist = temp.getJSONArray("reUrl");// 拿到服务器上的json地址
					imgUrl = brandlist.getString(0).substring(brandlist.getString(0).lastIndexOf("/") + 1,
							brandlist.getString(0).length());*/
					String reUrl = (String) temp.get("reUrl");// 拿到服务器上的json地址
					imgUrl = reUrl.substring(reUrl.lastIndexOf("/") + 1,
							reUrl.length());
				}
				// 上传成功
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (f != null && f.exists()) {
				f.delete();
			}
		}

		return imgUrl;
	}

}
