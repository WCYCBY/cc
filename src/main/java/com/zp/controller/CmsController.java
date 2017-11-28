package com.zp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.CmsColumn;
import com.zp.service.CmsColumService;
import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.RequestUtil;






@Controller
@RequestMapping("/mj/column/")
public class CmsController extends BaseController{
	
	@Autowired
	private CmsColumService  cmsColumnService;
	
	
	/**
	 * 
	 * 查询cms板块列表
	 * @Title: columnList
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月6日 下午3:42:29 
	 * @param model
	 * @param column
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("columnList")
	public String columnList(Model model, CmsColumn column, HttpServletRequest request) {
		Integer page = 1;
		if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
			page =Integer.valueOf(request.getParameter("pageNo"));
		}
		PageHelper.startPage(page,10);
		List<CmsColumn> columnList = null;
		if(column.getNote() != null){
			model.addAttribute("note",column.getNote() );
		}
		try {
			columnList = cmsColumnService.columnList(column);
		} catch (Exception e) {
			LOGGER.error("查询cms列表异常:"+e);
		}
		model.addAttribute("pageInfo", new PageInfo<CmsColumn>(columnList));
		model.addAttribute("column", columnList);
		return "/auth/cms/list";
	}
	
	
	/**
	 * 
	 * 添加板块
	 * @Title: addColumn
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月6日 下午3:42:49 
	 * @param request
	 * @param column
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("addColumn")
	public String addColumn(HttpServletRequest request, CmsColumn column, Model model) {
		int userId = Integer.parseInt(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		cmsColumnService.addColumn(userId, column);
		return "redirect:/mj/column/columnList.html";
	}
	
	
	
	
	/**
	 * 
	 * 校验名称
	 * @Title: columnNameCheck
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:26:04 
	 * @param name
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("columnNameCheck")
	@ResponseBody
	public String columnNameCheck(String name, Integer id) {
		String errorCode = "";
		CmsColumn column = cmsColumnService.queryOnlyName(name);
		if (column != null) {
			if(!(id != null && id.intValue() == column.getId())){
				errorCode = "1111";
			}
		}

		return errorCode;
	}
	
	/**
	 * 
	 * 添加页面跳转
	 * @Title: toAdd
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:25:52 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toAdd")
	public String toAdd() {
		return "/auth/cms/add";
	}
	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月6日 下午5:59:16 
	 * @param model 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="getById")
	public String getById(Model model,int id){
		CmsColumn column = null;
		try {
			column = cmsColumnService.queryById(id);
		} catch (Exception e) {
			LOGGER.error("查询单条角色异常" + e);
		}
		model.addAttribute("column", column);
		
		return "/auth/cms/editColumn";
	}
	
	
	
	/**
	 * 
	 * 跟新
	 * @Title: updateColumn
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:25:39 
	 * @param request
	 * @param model
	 * @param column
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "updateColumn", method = RequestMethod.POST)
	public String updateColumn(HttpServletRequest request, Model model, CmsColumn column) {
		int userId = Integer.parseInt(String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID)));
		column.setUpdater(userId);
		cmsColumnService.updateColumn(column);
		return "redirect:/mj/column/columnList.html";

	}
	
	/**
	 * 
	 * 编辑页面跳转
	 * @Title: toedit
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:25:23 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toedit")
	public String toedit() {
		return "/auth/cms/editColumn";
	}
	
	
	/**
	 * 
	 * 删除
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年3月7日 上午11:25:11 
	 * @param model
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="deleteById")
	public String deleteById(Model model,String id){
		try {
			cmsColumnService.delete(id);
		} catch (Exception e) {
			LOGGER.error("删除信息异常" + e);
		}
		
		return "redirect:/mj/column/columnList.html";
	}
	
}
