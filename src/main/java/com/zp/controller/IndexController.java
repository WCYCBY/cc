package com.zp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.llmjcommon.authClient.AuthConstantUtil;
import com.llmjcommon.utils.RequestUtil;

/**
 * @Title: IndexController
 * @Description: <类描述>
 * @author: qrc
 * @date: 2017-02-24 10:07
 * @version: V1.0
 */
@Controller
public class IndexController {

    /**
     * @Title 
     * @Description: <跳转到首页>
     * @author qrc
     * @date 2017/2/24
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping(value = "/")
    public String toIndex(HttpServletRequest request){
        String userId = RequestUtil.getCookieValue(request, AuthConstantUtil.AUTH_LOGIN_USERID);
        if (StringUtils.isBlank(userId)){
            return "/auth/system/login";
        }
        return "redirect:/auth/background/index.html";
    }
}
