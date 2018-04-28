package com.test.springsecuritydemo.controller;

import com.test.springsecuritydemo.domain.valueobject.Msg;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Secured({"ROLE_ADMIN"})//此方法只允许 ROLE_ADMIN角色访问
    @ResponseBody
    @RequestMapping("/admin")
    public String hello() {
        return "hello admin";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})//此方法只允许OLE_USER 角色访问
    @ResponseBody
    @GetMapping(value = "/user")
    public String getList() {
        return "hello getList";
    }


    @ResponseBody
    @PostMapping(value = "/user")
    public String save() {
        return "hello save";
    }


    @ResponseBody
    @PutMapping(value = "/user")
    public String update() {
        return "hello update";
    }
}
