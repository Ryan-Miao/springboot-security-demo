package com.test.springsecuritydemo.controller;

import com.test.springsecuritydemo.security.JwtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Role Security Test", description = "Role Security Test")
@RestController
public class RolePermTestController {

    @Secured({"ROLE_INDEX"})
    @GetMapping("/role-index")
    @ApiOperation("admin/test用户有此权限.")
    public String all() {
        return "admin/test用户有此权限";
    }

    @Secured({"ROLE_ROOM_SHOW"})
    @GetMapping("/role-room-show")
    @ApiOperation("admin用户有此权限.")
    public String admin() {
        return "admin用户有此权限";
    }

    @Secured({"ROLE_INDEX", "ROLE_ROOM_SHOW"})
    @GetMapping("/admin-user")
    @ApiOperation("admin/test权限的用户都可见")
    public String role() {
        return "admin/test权限的用户都可见";
    }

    @ApiOperation("获取当前登陆用户信息.")
    @GetMapping("/users/current")
    @ResponseBody
    public JwtUser currentUser(
        @ApiIgnore @AuthenticationPrincipal JwtUser loginUser
    ) {
        return loginUser;
    }
}
