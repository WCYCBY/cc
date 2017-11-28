package com.zp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.zp.config.AuthorityException;
import com.zp.config.PageInfo;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthProjectRole;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Resources;
import com.zp.entity.query.AuthProjectQuery;
import com.zp.entity.query.ResourcesQuery;
import com.zp.service.AuthUserService;
import com.zp.service.IAuthProjectService;
import com.zp.service.IResourcesService;

/**
 * <资源Controller>
 * 
 * @Title: AuthResourceController.java
 * @Description: <功能详细描述>
 * @author yanglu
 * @date 2017年2月17日上午11:55:27
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping(value = "/auth/resources/")
public class AuthResourceController extends BaseController{

	@Autowired
	private IResourcesService iResourcesService;
	@Autowired
	private IAuthProjectService iAuthProjectService;

	@Autowired
	private AuthUserService authUserService;

	/**
	 * 
	 * <查询资源列表>
	 * 
	 * @Title: list
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月20日 上午9:29:37
	 * @param model
	 * @param resources
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, Resources resources, HttpServletRequest request) {
		Integer page = 1;
		if (request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))) {
			page = Integer.valueOf(request.getParameter("pageNo"));
		}
		// 查询资源列表
		List<Resources> list =null;
		// 查询所有项目列表，用于下拉框展示
		List<AuthProjectQuery> projects =null;
		try {
			 list = iResourcesService.queryList(resources, page, 10);
			 projects = iAuthProjectService.queryAll();
		} catch (Exception e) {
			LOGGER.error("查询资源列表异常:"+e);
		}
		for (AuthProjectQuery authProjectQuery : projects) {
			if (authProjectQuery.getId().equals(resources.getProId())) {
				authProjectQuery.setCheckFlag(Boolean.TRUE);
				break;
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("resources", resources);
		model.addAttribute("projects", projects);
		model.addAttribute("pageInfo", new PageInfo<Resources>(list));
		return "auth/resources/list";
	}

	/**
	 * 
	 * <跳转到新增页面>
	 * 
	 * @Title: toAdd
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月22日 下午4:22:54
	 * @param model
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("toAdd")
	public String toAdd(Model model, String returnUrl) {
		List<Resources> resources=null;
		List<AuthProjectQuery> projects=null;
		try {
			resources = iResourcesService.findAll();
			projects = iAuthProjectService.queryAll();
		} catch (Exception e) {
			LOGGER.error("跳转到新增页面查询异常:"+e);
		}
		model.addAttribute("projects", projects);
		model.addAttribute("resources", resources);
		model.addAttribute("returnUrl", returnUrl);
		return "auth/resources/add";
	}

	/**
	 * 
	 * <新增----保存>
	 * 
	 * @Title: add
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月27日 下午1:55:50
	 * @param model
	 * @param resources
	 * @param initKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "add")
	public String add(Model model, Resources resources, String initKey) {
		resources.setResKey(initKey + "_" + resources.getResKey());
		try {
			iResourcesService.add(resources);
		} catch (Exception e) {
			LOGGER.error("新增资源异常:"+e);
		}
		return "redirect:list.html";
	}

	/**
	 * 
	 * <根据资源id删除单个资源，以及下面的子节点>
	 * 
	 * @Title: deleteById
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月22日 下午5:12:23
	 * @param model
	 * @param resourcesId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value = "deleteById")
	public boolean deleteById(Model model, String resourcesId) {
		boolean boo = false;
		try {
			boo = iResourcesService.delete(resourcesId);
		} catch (Exception e) {
			LOGGER.error("删除单个资源异常:"+e);
		}
		if (!boo) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <根据资源IDs批量删除资源信息>
	 * 
	 * @Title: deleteAll
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月24日 上午10:07:01
	 * @param idStr
	 * @param model
	 * @param resources
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAll")
	public JSONObject deleteAll(String idStr, Model model, Resources resources, HttpServletRequest request) {
		String check[] = idStr.split(",");
		List<Resources> list=null;
		try {
			list = iResourcesService.deleteByIds(check);
		} catch (Exception e) {
			LOGGER.error("批量删除资源异常:"+e);
		}
		StringBuffer errorMsg = new StringBuffer();
		if (list != null && list.size() > 0) {
			errorMsg.append("以下资源由于还在使用中，删除失败:");
			for (int i = 0; i < list.size(); i++) {
				if (i == (list.size() - 1))
					errorMsg.append(list.get(i).getName() + "!");
				else
					errorMsg.append(list.get(i).getName() + "、");
			}
		} else {
			errorMsg.append("删除成功！");
		}
		JSONObject json = new JSONObject();
		json.put("json", errorMsg.toString());
		return json;
	}

	/**
	 * 
	 * <详情和编辑通用方法。 根据传递的typeKey判断是详情(0)还是编辑(1)>
	 * 
	 * @Title: getById
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月27日 上午10:05:04
	 * @param model
	 * @param resourcesId
	 * @param typeKey
	 * @param returnUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "getById")
	public String getById(Model model, String resourcesId, int typeKey, String returnUrl) {
		Resources resources = iResourcesService.getById(resourcesId);
		// 资源key
		String[] split = resources.getResKey().split("_");
		resources.setResKey(split[split.length - 1]);
		List<Resources> resLists = null;
		try {
			resLists = iResourcesService.findAll();
		} catch (AuthorityException e) {
			LOGGER.error("编辑和详情页面查询异常:"+e);
		}
		model.addAttribute("resLists", resLists);
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("resources", resources);
		if (typeKey == 1) {// typeKey == 1 编辑
			// 查询所有项目列表
			Boolean hasChildRes = null;
			try {
				List<AuthProjectQuery> queryAll = iAuthProjectService.queryAll();
				model.addAttribute("projects", queryAll);
				Resources parRes = iResourcesService.getById(resources.getParentId().toString());
				if (null != parRes) {
					model.addAttribute("initKey", parRes.getResKey());
				} else {
					AuthProjectQuery pro = iAuthProjectService.queryById(resources.getProId());
					model.addAttribute("initKey", pro.getUniqueKey());
				}
				// 按照资源类型查找 0表示目录，1表示菜单，2表示按钮
				String type = resources.getType();
				if (!type.equals("0")) {//
					List<Resources> menus = this.queryByProId(resources.getProId(), type);
					model.addAttribute("menus", menus);
				}
				hasChildRes = this.queryChildRes(resourcesId);
			} catch (Exception e) {
				LOGGER.error("编辑和详情页面查询异常:"+e);
			}
			model.addAttribute("hasChildRes", hasChildRes);
			return "auth/resources/edit";
		} else {// typeKey == 0详情页面
			return "auth/resources/detail";
		}
	}

	/**
	 * 
	 * <根据项目id、资源类型加载资源>
	 * 
	 * @Title: queryByProId
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月27日 上午10:04:52
	 * @param proId
	 * @param type
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("queryByProId")
	@ResponseBody
	public List<Resources> queryByProId(Integer proId, String type) {
		List<Resources> resources =null;
		try {
			resources = iResourcesService.queryByProId(proId, type);
		} catch (Exception e) {
			LOGGER.error("根据项目id、资源类型查询资源异常:"+e);
		}
		return resources;
	}

	/**
	 * 
	 * <根据资源id校验是否有下级菜单或者按钮>
	 * 
	 * @Title: queryChildRes
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年2月27日 上午10:09:54
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("queryChildRes")
	@ResponseBody
	public Boolean queryChildRes(String id) {
		List<ResourcesQuery> list=null;
		try {
			list = iResourcesService.queryChildRes(id);
		} catch (Exception e) {
			LOGGER.error("校验是否有下级菜单或者按钮异常:"+e);
		}
		if (null != list && list.size() > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 根据角色id查询出项目
	 * 
	 * @Title: permissioRoleProject
	 * @Description: <功能详细描述>
	 * @author Liuyu
	 * @date 2017年2月28日 下午1:44:27
	 * @param roleId
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("permissioRoleProject")
	public String permissioRoleProject(String roleId, Model model, String nowURoleId) {
		List<AuthProjectQuery> projects =null ;
		List<AuthProjectRole> projectRoles =null;
		try {
			projects = iAuthProjectService.queryAll();
			projectRoles = iAuthProjectService.queryProjectRoleByRoleId(roleId);
		} catch (Exception e) {
			LOGGER.error("根据角色id查询项目异常:"+e);
		}
		for (AuthProjectQuery p : projects) {
			for (AuthProjectRole apr : projectRoles) {
				if (p.getId().equals(apr.getProjectId())) {
					p.setCheckFlag(Boolean.TRUE);
				}
			}
		}
		model.addAttribute("projects", projects);
		model.addAttribute("roleId", roleId);
		model.addAttribute("nowURoleId", nowURoleId);
		return "auth/roleList/updateRoleProject";
	}

	/**
	 * 
	 * 保存分配项目权限
	 * 
	 * @Title: saveRoleProject
	 * @Description: <功能详细描述>
	 * @author LiuYu
	 * @date 2017年2月27日 下午4:19:13
	 * @param roleId
	 * @param proIds
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("saveRoleProject")
	public String saveRoleProject(String roleId, String proIds) {
		String errorCode = "1000";
		List<String> list = Common.removeSameItem(Arrays.asList(proIds.split(",")));
		try {
			iResourcesService.saveRoleProject(roleId, list);
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		}
		return errorCode;
	}

	/**
	 * 
	 * 查询角色权限
	 * 
	 * @Title: permissioRole
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2017年2月27日 下午6:38:50
	 * @param model
	 * @param roleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "permissioRole")
	public String permissioRole(HttpServletRequest request, Model model, String roleId) {
		List<Resources> resources = null;
		List<Resources> allRes = null;
		try {
			resources = iResourcesService.getRoleResources(roleId);
			allRes = iResourcesService.queryByRole(roleId);
		} catch (Exception e) {
			LOGGER.error("查询角色权限异常:"+e);
		}
		
		String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
		AuthUserEntity nowU = null;
		try {
			nowU = authUserService.getById(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("nowUser", nowU);
		model.addAttribute("allRes", allRes);
		model.addAttribute("resources", resources);
		model.addAttribute("id", roleId);
		return "auth/roleList/permissioUser";
	}

	/**
	 * 
	 * <增加角色和权限>
	 * 
	 * @Title: saveRoleRescours
	 * @Description: <功能详细描述>
	 * @author qinrenchuan
	 * @date 2017年2月27日 下午1:48:42
	 * @param roleId
	 * @param rescId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value = "saveRoleRescours")
	public String saveRoleRescours(String roleId, String rescId) {
		String errorCode = "1000";
		List<String> list = Common.removeSameItem(Arrays.asList(rescId.split(",")));
		try {
			iResourcesService.saveRoleRescours(roleId, list);
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		}
		return errorCode;
	}

	/**
	 * 
	 * <校验资源key唯一性>
	 * 
	 * @Title: changeResKey
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月1日 下午2:58:46
	 * @param model
	 * @param resKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("changeResKey")
	@ResponseBody
	public String changeResKey(Model model, String resKey) {
		String errorCode = "";
		List<Resources> Key=null;
		try {
			Key = iResourcesService.queryResKey(resKey);
			if (Key != null && Key.size() > 0) {
				errorCode = AuthorityErrorCodeEnum.NOTNULL_KEY.getErrorCode();
			}
		} catch (Exception e) {
			LOGGER.error("校验资源key唯一性异常:"+e);
			errorCode = AuthorityErrorCodeEnum.SYS_ERROR.getErrorCode();
		}
		return errorCode;
	}

	/**
	 * 
	 * <编辑保存>
	 * 
	 * @Title: updateResources
	 * @Description: <功能详细描述>
	 * @author yanglu
	 * @date 2017年3月1日 上午10:24:05
	 * @param model
	 * @param resources
	 * @param initKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "update")
	public String updateResources(Model model, Resources resources, String initKey) {
		try {
			if (StringUtils.isNotBlank(resources.getResKey())) {
				resources.setResKey(initKey + "_" + resources.getResKey());
			}
			iResourcesService.modify(resources);
		} catch (Exception e) {
			LOGGER.error("编辑资源失败:"+e);
		}
		return "redirect:list.html";
	}
}
