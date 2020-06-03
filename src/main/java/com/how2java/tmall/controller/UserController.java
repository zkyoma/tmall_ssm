package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin_user_list")
    public String list(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn, 5);
        List<User> users = userService.list();
        PageInfo<User> pageInfo = new PageInfo<>(users,5);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/listUser";
    }
}
