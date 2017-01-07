package com.tlh.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hup on 2016/12/25.
 */
@Controller
public class LoginController {

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request) throws Exception{
        String exception =(String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(AccountException.class.getName().equals(exception)){
            throw new AccountException("用户名或密码错误");
        }
        if(DisabledAccountException.class.getName().equals(exception)){
            throw new DisabledAccountException("该用户不可用，请联系管理员");
        }
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return "redirect:/login";
        }
        return "sys/home";
    }

}
