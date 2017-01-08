package com.tlh.sys.controller;

import com.tlh.sys.entity.User;
import com.tlh.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by hup on 2017/1/8.
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService mUserService;

    @RequestMapping("/index")
    public String index(User user, Model model) throws Exception {
        List<User> users = mUserService.findUserInfos(user);
        model.addAttribute("users",users);
        return "sys/user/index";
    }

}
