/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ImageUpload.java
 * @Prject: llmj-authority
 * @Package: com.llmj.utils
 * @Description: <功能详细描述>
 * @author: caoju  
 * @date: 2017年3月6日 下午2:26:17
 * @version: V1.0  
 */
package com.zp.utils;

import java.io.File;
import java.util.UUID;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;

/**
 * <一句话功能简述>
 * @Title: ImageUpload.java
 * @Description: <功能详细描述>
 * @author  caoju
 * @date 2017年3月6日下午2:26:17
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImageUpload {

	public static Logger LOGGER = LoggerFactory.getLogger(ImageUpload.class);

	public static String upLoadImg(MultipartFile file,String type)
	        throws Exception {
		String imgUrl = null;
		JSONObject dataInfo = new JSONObject();
		dataInfo.put("type", type);
		
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
			MultipartRequestEntity mre = new MultipartRequestEntity(parts,
			        postMethod.getParams());

			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				String returnStr = postMethod.getResponseBodyAsString();
				// {"reUrl":"http://qb-pic.lenglianmajia.com/carImage/original/10244ab7779c42398dec3f288094366f.jpg","recode":0}
				JSONObject resultJson = JSONObject.parseObject(returnStr);
				if ("0".equals(resultJson.getString("recode"))) {
					String reUrl = resultJson.getString("reUrl");
					// imgUrl eg.c73009b1f60b4c00afa55c308304112b.jpg
					imgUrl = reUrl.substring(reUrl.lastIndexOf("/") + 1);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("上传图片图片异常，异常信息：", ex);
		} finally {
			if (f != null && f.exists()) {
				f.delete();
			}
		}
		return imgUrl;
	}


}
