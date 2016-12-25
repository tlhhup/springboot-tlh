package com.tlh.sys.controller;

import com.tlh.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hup on 2016/12/25.
 */
@Controller
public class ShiroController {

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user){
        SecurityUtils.getSubject().getPrincipal();
        return "";
    }

}
