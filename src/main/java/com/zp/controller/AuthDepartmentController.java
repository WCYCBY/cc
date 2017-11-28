package com.zp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.RequestUtil;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthDepartment;
import com.zp.entity.AuthUserEntity;
import com.zp.service.AuthUserService;
import com.zp.service.IAuthDepartmentService;



/**
 * <工厂>
 * @Title: AuthDepartmentController.java
 * @Description: <功能详细描述>
 * @author  songjunyi
 * @date 2017年2月17日下午2:11:27
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping(value="/auth/department/")
public class AuthDepartmentController extends BaseController{
	
	@Autowired
	private IAuthDepartmentService iAuthDepartmentService;
	
	@Autowired
	private AuthUserService authUserService;
	
	/**
	 * 
	 * <查询工厂列表>
	 * @Title: queryDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:53:09 
	 * @param model
	 * @param authDepartment
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("queryDepartment")
	public String queryDepartment(Model model, AuthDepartment authDepartment){
		Integer page = 1;
		if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
			page =Integer.valueOf(request.getParameter("pageNo"));
		}
		List<AuthDepartment> list = null;
		try {
			list = iAuthDepartmentService.queryList(authDepartment,page,10);
		} catch (Exception e) {
			LOGGER.error("查询工厂列表异常:"+e);
		}
		model.addAttribute("pageInfo", new PageInfo<AuthDepartment>(list));
		model.addAttribute("authDepartment", authDepartment);
		model.addAttribute("list", list);
		return "auth/department/list";
		
	}
	
	/**
	 * 
	 * <根据工厂id判断工厂是否被用户使用>
	 * @Title: checkDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:55:40 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("checkDepartment")
	public Boolean checkDepartment(String id){
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("department", id); 
		List<AuthUserEntity> list = null;
		try {
			list = iAuthDepartmentService.checkDepartment(map);
		} catch (Exception e) {
			LOGGER.error("根据工厂id判断工厂是否被用户使用异常:"+e);
		}
		if(null != list && list.size() >0){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 
	 * <根据工厂id删除工厂>
	 * @Title: deleteDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:57:05 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("deleteDepartment")
	public String deleteDepartment(Integer id){
		try {
			iAuthDepartmentService.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("根据工厂id删除工厂异常:"+e);
		}
		return "redirect:queryDepartment.html";
	}
	
	/**
	 * 
	 * <跳转到新增页面>
	 * @Title: toAddDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:58:23 
	 * @param returnUrl
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toAddDepartment")
	public String toAddDepartment(String returnUrl,Model model){
		model.addAttribute("returnUrl",  returnUrl);
		return "auth/department/add";
	}
	
	/**
	 * 
	 * <新增工厂>
	 * @Title: addDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:58:46 
	 * @param authDepartment
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addDepartment")
	public String addDepartment(AuthDepartment authDepartment){
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		AuthUserEntity authUser = null;
		try {
			authUser = authUserService.getById(userId);
		} catch (Exception e1) {
			LOGGER.error("新增工厂异常:"+e1);
		}
		authDepartment.setCreater(authUser.getUserName());
		try {
			iAuthDepartmentService.addDepartment(authDepartment);
		} catch (Exception e) {
			LOGGER.error("新增工厂异常:"+e);
		}
		return "redirect:/auth/department/queryDepartment.html";
	}
	
	/**
	 * 
	 * <跳转到编辑页面>
	 * @Title: toEditDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午10:59:59 
	 * @param model
	 * @param id
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toEditDepartment")
	public String toEditDepartment(Model model,String id,String returnUrl){
		AuthDepartment authDepartment = null;
		try {
			authDepartment = iAuthDepartmentService.queryById(id);
		} catch (Exception e) {
			LOGGER.error("跳转到编辑页面异常:"+e);
		}
		model.addAttribute("authDepartment", authDepartment);
		model.addAttribute("returnUrl", returnUrl);
		return "auth/department/edit";
	}
	
	/**
	 * 
	 * <编辑工厂>
	 * @Title: editDepartment
	 * @Description: <功能详细描述>
	 * @author songjunyi
	 * @date 2017年3月3日 上午11:00:18 
	 * @param authDepartment
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("editDepartment")
	public String editDepartment(AuthDepartment authDepartment){
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		AuthUserEntity authUser = null;
		try {
			authUser = authUserService.getById(userId);
		} catch (Exception e1) {
			LOGGER.error("编辑工厂异常:"+e1);
		}
		authDepartment.setAlterName(authUser.getUserName());
		try {
			iAuthDepartmentService.editDepartment(authDepartment);
		} catch (Exception e) {
			LOGGER.error("编辑工厂异常:"+e);
		}
		return "redirect:/auth/department/queryDepartment.html";
	}
	

}
