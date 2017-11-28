/**  
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: FreeMarkerUtil.java
 * @Prject: pss-authority
 * @Package: com.pss.utils
 * @Description: <功能详细描述>
 * @author: caoju  
 * @date: 2017年3月6日 下午4:00:41
 * @version: V1.0  
 */
package com.zp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成静态页工具类
 * @Title: FreeMarkerUtil.java
 * @Description: <功能详细描述>
 * @author  caoju
 * @date 2017年3月6日下午4:00:41
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FreeMarkerUtil {
	
	/**
	 * 生成静态页
	 * <一句话功能简述>
	 * @Title: createrHtml
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年3月6日 下午4:02:23 
	 * @param map
	 * @param ftlContent
	 * @param pagePath
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void createrHtml(Map<String, Object> map ,String ftlContent,String pagePath)throws Exception{
		Configuration configuration = new Configuration();
		
		configuration.setEncoding(Locale.getDefault(), "UTF-8");//设置编码格式
		configuration.setCacheStorage(new MruCacheStorage(20, 250));
		
		File htmlFile = new File(pagePath);		//html目标文件	
		if(!htmlFile.getParentFile().exists()){
			htmlFile.getParentFile().mkdirs();
		}
		
		Writer   out  = null;
		StringReader reader = null;
		try {
			out  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			reader = new StringReader(ftlContent);
			
			Template template = new Template("", reader, configuration);
			
			template.setEncoding("UTF-8");
			template.process(map, out);
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			reader.close();
			out.flush();
	        out.close();
		}
		
	}
}
