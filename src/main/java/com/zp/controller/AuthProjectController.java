package com.zp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthProject;
import com.zp.entity.query.AuthProjectQuery;
import com.zp.service.IAuthProjectService;

/**
 * <一句话功能简述>
 * @Title: ProjectController.java
 * @Description: <功能详细描述>
 * @author  qinrenchuan
 * @date 2016年8月25日下午6:03:24
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/auth/pManager/")
public class AuthProjectController extends BaseController {
	
	@Autowired
	private IAuthProjectService iAuthProjectService;
	
	/**
	 * 
	 *查询项目列表
	 * @Title: list
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 上午11:15:05 
	 * @param authProject
	 * @param
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("list")
	public String list(AuthProjectQuery authProject,Model model){
		Integer page = 1;
		if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
			page =Integer.valueOf(request.getParameter("pageNo"));
		}
		List<AuthProjectQuery> list = null;
		try {
			list = iAuthProjectService.queryList(authProject,page, 10);
		} catch (Exception e) {
			LOGGER.error("查询项目列表异常:"+e);
		}
		model.addAttribute("pageInfo", new PageInfo<AuthProjectQuery>(list));
		model.addAttribute("authProject", authProject);
		model.addAttribute("list", list);
		return "auth/project/list";
	}
	
	/**
	 * 跳转到新增项目页面
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午3:33:42 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("add")
	public String add(String returnUrl, Model model){
		model.addAttribute("returnUrl", returnUrl);
		return "auth/project/add";
	}
	
	/**
	 * 新增项目
	 * @Title: addData
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午4:26:36 
	 * @param authProject
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addData")
	public String addData(AuthProject authProject,HttpServletRequest request){
		try {
			authProject.setCreateUser((Integer)request.getAttribute("userId"));
			iAuthProjectService.add(authProject);
		} catch (Exception e) {
			LOGGER.error("新增项目:"+e);
		}
		return "redirect:list.html";
	}
	
	/**
	 * 
	 *根据用户名校验用户是否存在
	 * @Title: changeNameCheck
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午4:29:11 
	 * @param
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("changeNameCheck")
	public String changeNameCheck(String key){
		String errorCode = "";
		AuthProjectQuery authProjectQuery;
		try {
			authProjectQuery = iAuthProjectService.querySingleUserByKey(key);
			if (authProjectQuery != null) {
				errorCode = AuthorityErrorCodeEnum.NULL_OBJ.getErrorCode();
			}
		} catch (Exception e) {
			LOGGER.error("根据用户名校验用户是否存在:"+e);
			errorCode = AuthorityErrorCodeEnum.SYS_ERROR.getErrorCode();
		}
		return errorCode;
	}
	
	/**
	 *  
	 * 去编辑页面
	 * @Title: edit
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 上午10:26:13 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("edit")
	public String edit(Integer id,String returnUrl , Model model){
		AuthProjectQuery authProjectQuery;
		try {
			authProjectQuery = iAuthProjectService.queryById(id);
			model.addAttribute("authProjectQuery", authProjectQuery);
		} catch (Exception e) {
			LOGGER.error("去编辑页面:"+e);
		}
		model.addAttribute("returnUrl", returnUrl);
		return "auth/project/edit";
	}
	
	/**
	 * 修改项目
	 * @Title: addData
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月26日 下午4:26:36 
	 * @param authProject
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("update")
	public String update(AuthProject authProject,HttpServletRequest request){
		try {
			authProject.setUpdateUser((Integer)request.getAttribute("userId"));
			iAuthProjectService.update(authProject);
		} catch (Exception e) {
			LOGGER.error("修改项目:"+e);
		}
		return "redirect:list.html";
	}
	
	/**
	 * 根据id删除，删除后不可恢复
	 * <一句话功能简述>
	 * @Title: del
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年8月29日 下午2:45:01 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("del")
	public String del(Integer id){
		 try {
			iAuthProjectService.delById(id);
		} catch (Exception e) {
			LOGGER.error("根据id删除:"+e);
		}
		return "redirect:list.html";
	}
	
	/**
	 * 根据项目id判断有没有关联的角色
	 * <一句话功能简述>
	 * @Title: checkProRole
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2016年9月7日 上午10:31:26 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("checkProRole")
	@ResponseBody
	public boolean checkProRole(Integer id){
		List<Integer> list = null;
		try {
			list = iAuthProjectService.queryRoleByPro(id);
		} catch (Exception e) {
			LOGGER.error("根据id删除:"+e);
		}
		if(null != list && list.size() > 0){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
