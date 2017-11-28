package com.zp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.RequestUtil;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.service.AuthRoleService;
import com.zp.service.AuthUserService;
import com.zp.service.IResourcesService;



/**
 * 
 * <一句话功能简述>
 * @Title: AuthRoleController.java
 * @Description:角色列表
 * @author  LiuYu
 * @date 2017年2月16日下午4:34:25
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/auth/role/")
public class AuthRoleController extends BaseController{
	@Autowired
	private AuthRoleService authRoleService;
	
	@Autowired
	private IResourcesService resourcesService;

	@Autowired
	private AuthUserService authUserService;
	

	/**
	 * 
	 * 查询列表
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月3日 上午10:57:03 
	 * @param model
	 * @param role
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="query")
	public String query(Model model,Roles role,HttpServletRequest request){
		
		Integer page = 1;
		if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
			page =Integer.valueOf(request.getParameter("pageNo"));
		}
		PageHelper.startPage(page,10);
		List<Roles> rolesList = null;
		try {
			rolesList = authRoleService.query(role);
		} catch (Exception e) {
			LOGGER.error("查询角色列表异常:"+e);
		}
		model.addAttribute("pageView", rolesList);
		model.addAttribute("pageInfo", new PageInfo<Roles>(rolesList));
		if(null != role){
			model.addAttribute("name", role.getName());
		}
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		/*String userId = "546";*/
		//TODO 请检查此处
		
		AuthUserEntity nowU = null;
		try {
			nowU = authUserService.getById(userId);
		} catch (Exception e) {
			LOGGER.error("根据id查询用户异常:"+e);
		}
		model.addAttribute("nowUser", nowU);
		return "/auth/roleList/list";
	}
	
	
	
	
	/**
	 * 
	 * 查询单条记录
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:45:00 
	 * @param model
	 * @param roleId
	 * @param typeKey
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="getById")
	public String getById(Model model,String roleId,String returnUrl){
		Roles role = null;
		try {
			role = authRoleService.getById(roleId);
		} catch (Exception e) {
			LOGGER.error("查询单条角色异常" + e);
		}
		model.addAttribute("role", role);
		model.addAttribute("returnUrl", returnUrl);
		
		return "/auth/roleList/edit";
	}
	
	
	
	/**
	 * 
	 * 更新修改的信息
	 * @Title: updaterole
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:55:27 
	 * @param model
	 * @param role
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="update")
	public String updaterole(Model model,Roles role){
		try {
			authRoleService.modify(role);
		} catch (Exception e) {
			LOGGER.error("更新信息异常" + e);
		}
		return "redirect:query.html";
	}
	
	
	/**
	 * 
	 * 根据id删除
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:59:45 
	 * @param model
	 * @param roleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="deleteById")
	public String deleteById(Model model,String roleId){
		try {
			authRoleService.delete(roleId);
			resourcesService.deleteRoleRescours(roleId);
		} catch (Exception e) {
			LOGGER.error("删除信息异常" + e);
			e.printStackTrace();
		}
		
		return "redirect:query.html";
	}
	
	
	/**
	 * 
	 * 检验角色是否被引用
	 * @Title: checkRolesIsUsed
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:33:28 
	 * @param model
	 * @param rolesId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("checkRolesIsUsed")
	public String checkRolesIsUsed(Model model, String rolesId){
		String isUsed = "0";
		List<AuthUserEntity> userRoleList = null;
		try {
			userRoleList = authRoleService.queryUserRolesByRoleId(rolesId);
		} catch (Exception e) {
			LOGGER.error("根据id检验角色异常"+e);
		}
		if(userRoleList != null && userRoleList.size() > 0){
			isUsed = "1";
		}
		return isUsed;
	}
	
	
	
	
	/**
	 * 验证角色名是否重复
	 * <一句话功能简述>
	 * @Title: checkRolesName
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:46:21 
	 * @param model
	 * @param rolesName
	 * @param rolesId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("checkRolesName")
	public String checkRolesName(Model model,String rolesName, Integer rolesId){
		String errorCode = "";
		Roles roles = null;
		try {
			roles = authRoleService.querySingleRolesByName(rolesName);
			if(roles != null){
				if(!(rolesId != null && rolesId.intValue() == roles.getId())){
					errorCode = "1001";
				}
			}
		} catch (Exception e) {
			LOGGER.error("根据角色名是否存在:"+e);
			errorCode = AuthorityErrorCodeEnum.ERROR_QUERY_ROLENAME.getErrorCode();
		}
		
		return errorCode;
	}
	
	
	/**
	 * 验证角色key是否重复
	 * <一句话功能简述>
	 * @Title: checkRolesKey
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月17日 上午11:49:53 
	 * @param model
	 * @param rolesKey
	 * @param rolesId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("checkRolesKey")
	public String checkRolesKey(Model model,String rolesKey, Integer rolesId){
		String errorCode = "";
		Roles roles = null;
		try {
			roles = authRoleService.querySingleRolesByKey(rolesKey);
		} catch (Exception e) {
			LOGGER.error("根据角色key是否存在:"+e);
			errorCode = AuthorityErrorCodeEnum.ERROR_QUERY_ROLEKEY.getErrorCode();
		}
		if(roles != null){
			if(!(rolesId != null && rolesId.intValue() == roles.getId())){
				errorCode = "1001";
			}
		}
		return errorCode;
	}
	
	
	
	
	
	/**
	 * 保存新增
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2016年7月30日 下午9:34:37 
	 * @param model
	 * @param role
	 * @param rescId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="add")
	public String add(Model model,Roles role){
		try {
			authRoleService.add(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:query.html";
	}
	/**
	 * 新增跳转
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author liuyu
	 * @date 2016年7月30日 下午9:34:37 
	 * @param model
	 * @param role
	 * @param rescId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addUI")
	public String add(){
		return "auth/roleList/add";
	}
	
}
