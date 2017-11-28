package com.zp.controller;

import com.alibaba.fastjson.JSONObject;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsColumn;
import com.zp.entity.CmsColumnContent;
import com.zp.service.CmsColumService;
import com.zp.service.ICmsColumnContentService;
import com.zp.utils.Constant;
import com.zp.utils.ImageUpload;
import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.RequestUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <栏目图片controller>
 * 
 * @Title: CmsColumnContentController.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年3月6日下午1:52:03
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping(value = "/cms/columnContentCtl/")
public class CmsColumnContentController extends BaseController {
	@Autowired
	private CmsColumService columnService;
	@Autowired
	private ICmsColumnContentService columnContentService;
	// 定义的常量
	public static final String CMS_IMAGE = "cmsImage";

	/**
	 * 
	 * <跳转到编辑数据页面>
	 * 
	 * @Title: toEdit
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月6日 下午3:02:15
	 * @param model
	 * @param columnId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Model model, Integer columnId) {
		// 根据栏目id获取栏目信息
		CmsColumn column = columnService.queryById(columnId);
		model.addAttribute("column", column);
		// 根据栏目id查询图片信息
		List<CmsColumnContent> contentList = columnContentService.queryColumnContent(columnId);
		addColumnContent(contentList, "index", model);
		// 图片数量上限
		Integer upperLimitPic = column.getUpperLimitPic();
		// 图片数量下限
		Integer lowerLimitPic = column.getLowerLimitPic();
		model.addAttribute("upperLimitPic", upperLimitPic);
		model.addAttribute("lowerLimitPic", lowerLimitPic);
		model.addAttribute("PicServer", Constant.IMAGES_URL);
		model.addAttribute("columnId", columnId);
		return "auth/cms/edit";
	}

	/**
	 * 
	 * <设置图片的下标,以及获取图片信息>
	 * 
	 * @Title: addColumnContent
	 * @Description: <如index1,index2>
	 * @author yanglu
	 * @date 2017年3月6日 下午3:06:41
	 * @param list
	 * @param prefix
	 * @param model
	 * @see [类、类#方法、类#成员]
	 */
	private void addColumnContent(List<CmsColumnContent> list, String prefix, Model model) {
		for (CmsColumnContent cmsColumnContent : list) {
			model.addAttribute(
					prefix + (cmsColumnContent.getPicOrderIndex() != null ? cmsColumnContent.getPicOrderIndex() : ""),
					cmsColumnContent);
		}

	}

	/**
	 * 
	 * <保存图片>
	 * 
	 * @Title: upload
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 上午11:45:12
	 * @param response
	 * @param outputWidth
	 * @param outputHeight
	 * @param file
	 * @param type
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("upload")
	public void upload(HttpServletResponse response, CmsColumnContent column, String outputWidth, String outputHeight,
			@RequestParam(value = "file", required = false) MultipartFile file, String type) throws Exception {
		String imgUrl = null;
		String returnStr = null;
		JSONObject dataInfo = new JSONObject();
		dataInfo.put("type", CMS_IMAGE);
		PostMethod postMethod = new PostMethod(Constant.IMAGES_SERVER_UPLOAD_API_URL);
		StringPart sp = new StringPart("data", dataInfo.toString());
		File f = null;
		try {
			String filePath = file.getOriginalFilename();
			String renameFile = UUID.randomUUID().toString().replaceAll("-", "")
					+ filePath.substring(filePath.lastIndexOf("."));
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
				JSONObject resultJson = JSONObject.parseObject(returnStr);
				if ("0".equals(resultJson.getString("recode"))) {
					String reUrl = resultJson.getString("reUrl");
					imgUrl = reUrl.substring(reUrl.lastIndexOf("/") + 1);
				}
			}
			returnStr = "<script>parent.callback('" + imgUrl + "')</script>";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(returnStr);
			pw.flush();
			pw.close();
		} catch (Exception ex) {
			LOGGER.error("上传图片图片异常，异常信息：", ex);
		} finally {
			if (f != null && f.exists()) {
				f.delete();
			}
		}
	}

	/**
	 * 
	 * <读取图片>
	 * 
	 * @Title: ueditImageUpload
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午1:50:48
	 * @param upfile
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unused")
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
			// 经过p过的图片 格式ImageIO.read 不可识别，以流的形式读取
			try {
				inputStream = upfile.getInputStream();
				stream = new ByteArrayOutputStream();
				int num = 0;
				while ((num = inputStream.read()) != -1) {
					stream.write(num);
				}
			} catch (IOException e1) {
				LOGGER.error("读取图片异常:" + e1);
			}
			ImageIcon image = new ImageIcon(stream.toByteArray());
			width = image.getIconWidth();
			height = image.getIconHeight();
		}
		try {
			url = ImageUpload.upLoadImg(upfile,CMS_IMAGE);
		} catch (Exception e) {
			LOGGER.error("读取图片异常:" + e);
		}
		JSONObject json = new JSONObject();
		json.put("url", Constant.IMAGES_URL + url);
		json.put("upfile", upfile.getName());
		json.put("size", upfile.getSize());
		json.put("state", "SUCCESS");
		json.put("type", upfile.getContentType());
		json.put("originalName", upfile.getOriginalFilename());
		return json;
	}

	/**
	 * 
	 * <编辑数据-----保存>
	 * 
	 * @Title: insertInfo
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月7日 下午2:46:00
	 * @param request
	 * @param response
	 * @param model
	 * @param column
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/insertInfo")
	public void insertInfo(HttpServletRequest request, HttpServletResponse response, Model model, CmsColumn column) {
		int userId = Integer.parseInt(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		List<CmsColumnContent> list = column.getColumnContents();
		JSONObject json = new JSONObject();
		try {
			for (CmsColumnContent cmsColumnContent : list) {
				cmsColumnContent.setColumnId(column.getId());
				if (cmsColumnContent.getPicUrl() != null && cmsColumnContent.getPicUrl() != "") {
					cmsColumnContent.setCreater(userId);
					cmsColumnContent.setCreateTime(new Date());
				}
			}
			columnContentService.addColumnContent(list);
			json.put("msg", "保存成功");
			json.put("code", "1");
		} catch (Exception e) {
			LOGGER.error("编辑数据-----保存异常：" + e);
			json.put("msg", "保存失败");
			json.put("code", "0");
		}
		sendAjaxResponse(response, json.toString());
	}
}
