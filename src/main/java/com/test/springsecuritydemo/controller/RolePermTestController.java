package com.test.springsecuritydemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Role Security Test", description = "Role Security Test")
@RestController
public class RolePermTestController {

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    @ApiOperation("admin权限的用户可见.")
    public String admin() {
        return "admin权限的用户可见";
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/user")
    @ApiOperation("user权限的用户可见.")
    public String user() {
        return "user权限的用户可见";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/admin-user")
    @ApiOperation("user和admin权限的用户都可见")
    public String role() {
        return "user和admin权限的用户都可见";
    }

    @GetMapping("/all")
    @ApiOperation("所有人可见，没有特殊配置role")
    public String all() {
        return "所有人可见，没有特殊配置role";
    }


}
