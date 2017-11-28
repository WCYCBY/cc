package com.zp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.Common;
import com.llmjcommon.utils.RequestUtil;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Roles;
import com.zp.entity.UserRoles;
import com.zp.service.AuthUserService;


/**
 * 用户基本信息类
 * @Title: AuthUserController.java
 * @Description: <功能详细描述>
 * @author  caoju
 * @date 2017年2月16日下午5:02:10
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/auth/user/")
public class AuthUserController extends BaseController{
	@Resource
	private AuthUserService authUserService;
	/**
	 * 查询用户列表
	 * <一句话功能简述>
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月17日 下午2:29:00 
	 * @param model
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("query")
	public String query(Model model, AuthUserEntity user,
			HttpServletRequest request) {
		int pageNo = 1;
		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		AuthUserEntity u = null;
		try {
			u = authUserService.getById(userId);
		} catch (Exception e) {
			LOGGER.error("根据id查询用户异常:"+e);
		}
		user.setLevel(u.getLevel());
		List<AuthUserEntity> userlist = null;
		try {
			userlist = authUserService.query(pageNo,10,user);
		} catch (Exception e) {
			LOGGER.error("查询用户列表异常:"+e);
		}
		model.addAttribute("pageInfo", new PageInfo<AuthUserEntity>(userlist));
		model.addAttribute("user", user);
		model.addAttribute("loginUser", u);
		model.addAttribute("userlist", userlist);
		return "/auth/userList/list";
	}
	
	/**
	 * 跳转到分配角色弹窗页面
	 * <一句话功能简述>
	 * @Title: toUserRole
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月28日 下午2:44:42 
	 * @param request
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "toUserRole",method = {RequestMethod.GET,RequestMethod.POST})
	public String toUserRole(HttpServletRequest request,Model model,String userId,String returnUrl){
		AuthUserEntity user = null;
		try {
			user = authUserService.getById(userId);
		} catch (Exception e) {
			LOGGER.error("根据id查询用户异常:"+e);
		}
		model.addAttribute("user",user);
		
		List<Roles> roles = null;
		try {
			roles = authUserService.findRoles();
		} catch (Exception e) {
			LOGGER.error("查询用户角色列表异常:"+e);
		}
		model.addAttribute("roles", roles);
		model.addAttribute("returnUrl", returnUrl);
		return "/auth/userList/userRole";
	}
	
	/**
	 * 分配角色
	 * <一句话功能简述>
	 * @Title: saveRole
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月28日 下午4:34:55 
	 * @param model
	 * @param userRoles
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("saveRole")
	public JSONObject saveRole(Model model, UserRoles userRoles) {
		JSONObject json = new JSONObject();
		String code = "";
		try {
			authUserService.saveUserRole(userRoles);
		} catch (Exception e) {
			LOGGER.error("分配用户角色异常:"+e);
			code = AuthorityErrorCodeEnum.ERROR_SAVE_ROLE_USER.getErrorCode();
		}
		json.put("code", code);
		return json;
	}
	
	
	
	/**
	 * 跳转到新增页面
	 * <一句话功能简述>
	 * @Title: toAddPage
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月27日 下午4:34:40 
	 * @param model
	 * @param dept
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toAddPage")
	public String toAddPage(Model model,AuthDepartment dept,String returnUrl) {
		List<AuthDepartment> deptList = null;
		try {
			deptList = authUserService.queryDeptList(dept);
		} catch (Exception e) {
			LOGGER.error("查询工厂异常:"+e);
		}
		model.addAttribute("deptList", deptList);
		model.addAttribute("returnUrl", returnUrl);
		return "/auth/userList/add";
	}
	
	/**
	 * 添加用户
	 * <一句话功能简述>
	 * @Title: addUser
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月27日 下午5:30:45 
	 * @param model
	 * @param user
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addUser")
	public String addUser(Model model, AuthUserEntity user) {
		String passWord = Common.MD5(user.getUserPassWord());
		user.setUserPassWord(passWord);
		String userId1 = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		AuthUserEntity nowUser = null;
		try {
			nowUser = authUserService.getById(userId1);
		} catch (Exception e) {
			LOGGER.error("根据id查询用户异常:"+e);
		}
		user.setLevel(nowUser.getLevel()+1);
		user.setCode(Long.toString(System.currentTimeMillis()));
		try {
			authUserService.addUser(user);
		} catch (Exception e) {
			LOGGER.error("添加用户异常:"+e);
		}
		return "redirect:query.html";
	}
	
	/**
	 * 删除
	 * <一句话功能简述>
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月28日 上午11:48:05 
	 * @param model
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("deleteById")
	public String deleteById(Model model, String userId) {
		try {
			authUserService.updateDel(userId);
		} catch (Exception e) {
			LOGGER.error("删除用户异常:"+e);
		}
		try {
			authUserService.delUserRole(userId);
		} catch (Exception e) {
			LOGGER.error("删除用户对应的角色异常:"+e);
		}
		//删除用户时踢出当前用户保存在redis中的登录状态
		delRedisKeyByUserId(userId);
		return "redirect:query.html";
	}
	
	/**
	 * 根据名字查询 跳转到详情页或者编辑页
	 * <一句话功能简述>
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月23日 下午6:17:40 
	 * @param model
	 * @param userId
	 * @param type
	 * @param returnUrl
	 * @param request
	 * @param sectionList
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("edit")
	public String getById(Model model, String userId, int type,String returnUrl,
			HttpServletRequest request,AuthDepartment sectionList) {
		
		model.addAttribute("returnUrl", returnUrl);
		AuthUserEntity user = null;
		try {
			user = authUserService.getById(userId);
		} catch (Exception e) {
			LOGGER.error("根据id查询用户异常:"+e);
		}
		AuthDepartment authDepartment = new AuthDepartment();
		List<AuthDepartment> deptList = null;
		try {
			deptList = authUserService.queryDeptList(authDepartment);
		} catch (Exception e) {
			LOGGER.error("查询工厂异常:"+e);
		}
		model.addAttribute("deptList", deptList);
		model.addAttribute("user", user);
		if (type == 1) {
			return "/auth/userList/edit";
		} else {
			return "/auth/userList/show";
		}
	}
	
	/**
	 * 更新修改
	 * <一句话功能简述>
	 * @Title: update
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年2月27日 下午2:56:17 
	 * @param model
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("update")
	public String update(Model model, AuthUserEntity user,HttpServletRequest request) {
		if(user.getUserPassWord()!=null&&user.getUserPassWord()!=""){
			String passWord = Common.MD5(user.getUserPassWord());
			user.setUserPassWord(passWord);
		}
		try {
			authUserService.updateUser(user);
		} catch (Exception e) {
			LOGGER.error("修改用户信息异常:"+e);
		}
		return "redirect:query.html";
	}
	
	/**
	 * 校验用户名是否存在
	 * <一句话功能简述>
	 * @Title: changeNameCheck
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年3月2日 下午3:52:10 
	 * @param model
	 * @param userName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("changeNameCheck")
	public String changeNameCheck(Model model, String userName) {
		String errorCode = "";
		AuthUserEntity user = null;
		try {
			user = authUserService.querySingleUser(userName);
		} catch (Exception e) {
			LOGGER.error("根据用户名查询用户异常:"+e);
		}
		if (user != null) {
			errorCode = AuthorityErrorCodeEnum.ERROR_USERNAME_ISHAVE.getErrorCode();
		}
		return errorCode;
	}
	
	
	
	/**
	 * @Title
	 * @Description: <跳转到首页修改密界面>
	 * @author qrc
	 * @date 2017/2/17
	 * @param 
	 * @return
	 * @see [类、类#方法、类#成员]
	*/
	@RequestMapping(value = "goEditPassword",method = {RequestMethod.GET,RequestMethod.POST})
	public String goEditPassword(HttpServletRequest request,Model model){
		//RoutingXeRedis redis = new RoutingXeRedis("xeRedis");
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		//AuthUserEntity userInfo = JSONObject.parseObject(redis.get(AuthConstantUtil.REDIS_USER_LOGIN + userId),AuthUserEntity.class);
		model.addAttribute("loginUser",userId);
		return "/auth/userList/homePageEditPassword";
	}


	/**
	 * @Title 
	 * @Description: <修改密码>
	 * @author qrc
	 * @date 2017/2/23
	 * @param 
	 * @return
	 * @see [类、类#方法、类#成员]
	*/
	@ResponseBody
	@RequestMapping(value = "/updatePassword",method = {RequestMethod.POST})
	public JSONObject updatePassword(String userId, String passwd){
		JSONObject jsonObject = new JSONObject();
		boolean flag = Boolean.FALSE;
		if (StringUtils.isNotBlank(passwd)){
			authUserService.updatePassword(userId, Common.MD5(passwd));
			flag = Boolean.TRUE;
		}
		jsonObject.put("flag",flag);
		return jsonObject;
	}
}
