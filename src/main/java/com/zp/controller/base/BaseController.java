/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: BaseController.java
 * @Prject: llmj-authority
 * @Package: com.mj.controller.base
 * @Description: <功能详细描述>
 * @author: qinrenchuan  
 * @date: 2016年9月9日 下午5:52:04
 * @version: V1.0  
 */
package com.zp.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llmjcommon.authClient.AuthConstantUtil;

import redis.clients.jedis.Jedis;
/**
 * 
 * BaseController
 * @Title: BaseController.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年9月9日下午5:52:31
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseController {
	@Resource
	protected HttpServletRequest request;

	public static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 */
	public void sendAjaxResponse(HttpServletResponse response, Object strMsg) {
		PrintWriter out = null;
		try {
			if (response != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/json");
				out = response.getWriter();
				out.print(strMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	/**
	 * 根据用户id删除其redis数据
	 * <一句话功能简述>
	 * @Title: delRedisKeyByUserId
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2016年12月30日 下午3:02:21 
	 * @param userId
	 * @see [类、类#方法、类#成员]
	 */
	public void delRedisKeyByUserId(String userId){
		
		Jedis redis = getJedis();
		try {
			String thisUserLoginFlag   = AuthConstantUtil.REDIS_USER_LOGIN + userId;
			String thisUserLoginFlagIp = AuthConstantUtil.REDIS_USER_LOGIN + userId + AuthConstantUtil.AUTH_SEPARATION_CHAR;
			Set<String> keys = redis.keys(thisUserLoginFlag+"*");
			for (String key : keys) {
				if(key.equals(thisUserLoginFlag) || key.startsWith(thisUserLoginFlagIp)){
					redis.del(key);
				}
			}
			LOGGER.info("删除用户redis中的登录状态》》》》"+userId);
		} catch (Exception e) {
			LOGGER.error("删除用户缓存信息失败"+e);
		}
		
	}
	
	
	/**
	 * 读取配置文件redisrouting.xml，并获取jedis
	 * @Title: getJedis
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2017年1月4日 下午4:25:27 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private Jedis getJedis(){
		try {
			XMLConfiguration routingConfig = new XMLConfiguration("redisrouting.xml");
			List<Object> serverNodesList = routingConfig.getList("servernode.node.id");
			for(int clusterIndex=0;clusterIndex<serverNodesList.size();clusterIndex++){
				String host = routingConfig.getString("servernode.node("+clusterIndex+").host");
				int port = routingConfig.getInt("servernode.node("+clusterIndex+").port");
				if(StringUtils.isNotBlank(host) && port != 0){
					LOGGER.info("redis地址为》》》》"+host+":"+port);
					return new Jedis(host, port);
				}
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
