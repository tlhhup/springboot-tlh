package com.tlh.sys.controller;

import com.tlh.sys.entity.Role;
import com.tlh.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by hup on 2017/1/11.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService mRoleService;

    @RequestMapping("/index")
    public String index(Role role, Model model) throws Exception{
        List<Role> roles = mRoleService.findRoleInfos(role);
        model.addAttribute("roles",roles);
        return "sys/role/index";
    }

}
