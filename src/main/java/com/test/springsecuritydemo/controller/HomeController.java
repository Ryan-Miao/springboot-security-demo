package com.test.springsecuritydemo.controller;

import com.test.springsecuritydemo.domain.entity.SysUser;
import com.test.springsecuritydemo.domain.valueobject.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "Role Security Test", description = "Role Security Test")
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

    @ApiOperation("提供basic登录认证.")
    @GetMapping("/login-basic")
    @ResponseBody
    public SysUser basicLogin(
        @ApiParam("username和password 64编码") @RequestHeader(defaultValue = "Basic YWRtaW46YWRtaW4=") String authorization,
        @AuthenticationPrincipal SysUser loginUser
    ) {
        return loginUser;
    }

}
