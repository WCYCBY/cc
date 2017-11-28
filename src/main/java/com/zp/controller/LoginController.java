package com.zp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.redis.routing.RoutingXeRedis;
import com.llmjcommon.utils.Common;
import com.llmjcommon.utils.RequestUtil;
import com.zp.config.AuthorityErrorCodeEnum;
import com.zp.controller.base.BaseController;
import com.zp.entity.AuthUserEntity;
import com.zp.entity.Resources;
import com.zp.entity.UserLoginListEntity;
import com.zp.entity.query.AuthProjectQuery;
import com.zp.service.AuthUserService;
import com.zp.service.IAuthProjectService;
import com.zp.service.IResourcesService;
import com.zp.service.UserLoginListService;


/**
 * @Title:
 * @Description: <登录入口>
 * @author: qrc
 * @date: 2017-02-17 11:24
 * @version: V1.0
 */
@Controller
@RequestMapping("/auth")
public class LoginController extends BaseController{

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private UserLoginListService userLoginListService;
    @Autowired
    private IAuthProjectService iAuthProjectService;
    @Autowired
    private IResourcesService iResourcesService;



    /**
     * @Title
     * @Description: <跳转到登录页面>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/login/login_toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "/auth/system/login";
    }

    /**
     * @Title 
     * @Description: <用户登录>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/login/loginCheck")
    @ResponseBody
    public JSONObject loginCheck(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            if (!request.getMethod().equals("POST")) {
                json.put("error", "支持POST方法提交！");
                return json;
            }
            if (Common.isEmpty(username) || Common.isEmpty(password)) {
                json.put("error", "用户名或密码不能为空！");
                return json;
            }
            // 验证用户账号与密码是否正确
            AuthUserEntity users = authUserService.queryUserName(username);
            String md5PassWord = Common.MD5(password);
            if (users == null || !users.getUserPassWord().equals(md5PassWord)) {
                json.put("error", "用户或密码不正确！");
                return json;
            }

            //登录成功后记录redis登录状态
            RoutingXeRedis redis = new RoutingXeRedis("xeRedis");
            JSONObject userInfo = new JSONObject();
            userInfo.put("id", users.getUserId());
            userInfo.put("userName", users.getUserName());
            userInfo.put("departmentName", users.getDepartmentName());
            userInfo.put("department", users.getDepartment());
            //权限资源
            Map<String, Object> queryNodeAuth = iResourcesService.queryNodeAuth(String.valueOf(users.getUserId()));
            LOGGER.info("权限资源》》》》》》》》》："+queryNodeAuth.toString());
            LOGGER.info(queryNodeAuth.toString());
            userInfo.put("nodeAuths", queryNodeAuth);

            //用户userId写入cookie
            RequestUtil.setCookie(response, AuthConstantUtil.AUTH_LOGIN_USERID, String.valueOf(users.getUserId()), AuthConstantUtil.COOKIEMAXAGE,AuthConstantUtil.DOMAIN, null);

            //用户信息写入redis缓存
            redis.setex(AuthConstantUtil.REDIS_USER_LOGIN + users.getUserId(), 60 * 60 * 10, userInfo.toJSONString());

            // 记录登录信息
            UserLoginListEntity userLoginList = new UserLoginListEntity();
            userLoginList.setUserId(users.getUserId());
            userLoginList.setLoginIp(Common.toIpAddr(request));
            userLoginListService.add(userLoginList);
        } catch (Exception ae) {
            //request.setAttribute("error", "登录异常，请联系管理员！");
            //return "framework/login";
            json.put("error", "登录异常，请联系管理员！");
            return json;
        }
        json.put("error", "0000");
        return json;
    }

    /**
     * @Title 
     * @Description: <跳转到系统首页>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/background/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String toIndex(HttpServletRequest request,Model model){
        // 查询所有项目列表及菜单，用于下拉框展示
        String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
        List<AuthProjectQuery> proList = null;
		try {
			proList = iAuthProjectService.queryProListByUserId(userId);
		} catch (Exception e) {
			LOGGER.error("跳转到首页异常:"+e);
		}
        model.addAttribute("proList", proList);
        int pSize = proList.size();
        JSONArray parentMenu = new JSONArray();
        for(int i=0;i<pSize;i++){
            JSONObject parentMenuNode = new JSONObject();
            AuthProjectQuery authProjectQuery = proList.get(i);

            parentMenuNode.put("id", String.valueOf(i+1));
            parentMenuNode.put("text", authProjectQuery.getName());
            parentMenuNode.put("icon", authProjectQuery.getIcon());
            parentMenuNode.put("menu", getTreeMenu(authProjectQuery.getId(),userId));

            parentMenu.add(parentMenuNode);
        }
        model.addAttribute("menus",parentMenu);
        Integer department = (Integer)request.getAttribute("department");
        model.addAttribute("factoryCode",department);
        LOGGER.info("MENUS>>>>>>>>>>>>>>>>>>>>>>>>>>>"+parentMenu.toJSONString());
        return "authIndex";
    }

    /**
     * @Title 
     * @Description: <获取树型菜单>
     * @author qrc
     * @date 2017/2/27
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    public JSONArray getTreeMenu(Integer projectId,String userId){
        JSONArray menuNode = new JSONArray();
        Map<String,Object> parmMap = new HashMap<String, Object>();
        parmMap.put("projectId", projectId);
        parmMap.put("userId", userId);
        parmMap.put("type", AuthorityErrorCodeEnum.AUTHORITY_RESOURCE_TYPE_DIRECTORY.getErrorCode());
       List<Resources> directoryList = iResourcesService.getResourcesByMap(parmMap);

        if(null == directoryList || directoryList.size() == 0){
            return null;
        }
        for (Resources resources : directoryList) {
            JSONObject menuNodeJson = new JSONObject();
            JSONArray itemsArry = new JSONArray();
            menuNodeJson.put("text", resources.getName());
            menuNodeJson.put("id",resources.getId());
            parmMap.put("type", AuthorityErrorCodeEnum.AUTHORITY_RESOURCE_TYPE_MENU.getErrorCode());
            parmMap.put("parentId", resources.getId());
            List<Resources> menuList = iResourcesService.getResourcesByMap(parmMap);
            for (Resources res : menuList) {
                JSONObject items = new JSONObject();
                items.put("id", String.valueOf(res.getId()));
                items.put("text", res.getName());
                items.put("href", res.getResUrl());

                itemsArry.add(items);
            }
            menuNodeJson.put("items", itemsArry);

            menuNode.add(menuNodeJson);
        }

        return menuNode;
    }

    /**
     * @Title
     * @Description: <进入tab页面>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value="/background/tab",method = {RequestMethod.GET,RequestMethod.POST})
    public String tab(){
        return "/common/tab";
    }

    /**
     * @Title
     * @Description: <登录以后默认主页>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/backgrounk/loginDefault" ,method = {RequestMethod.GET,RequestMethod.POST} )
    public  String loginDefault(){
        return "/common/default";
    }

    /**
     * @Title
     * @Description: <退出登录>
     * @author qrc
     * @date 2017/2/17
     * @param
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/login/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = RequestUtil.getCookie(request, AuthConstantUtil.AUTH_LOGIN_USERID);
        if (!Common.isEmpty(cookie.getValue())){
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setDomain(AuthConstantUtil.DOMAIN);
            response.addCookie(cookie);
            //删除redis中登录状态
            RoutingXeRedis redis = new RoutingXeRedis("xeRedis");
            redis.del(AuthConstantUtil.REDIS_USER_LOGIN + "");
        }
        return "redirect:login_toLogin.html";
    }


    /**
     * @Title
     * @Description: <获取当前登录用户>
     * @author qrc
     * @date 2017/2/17
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping(value = "/background/getUname",method = RequestMethod.POST)
    public JSONObject getLoginUserName(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = String.valueOf(RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID));
        RoutingXeRedis redis = new RoutingXeRedis("xeRedis");
        AuthUserEntity userInfo = JSONObject.parseObject(redis.get(AuthConstantUtil.REDIS_USER_LOGIN + userId),AuthUserEntity.class);
        jsonObject.put("loginUserName",userInfo.getUserName());
        return jsonObject;
    }
}
