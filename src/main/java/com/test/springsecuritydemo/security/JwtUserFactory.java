package com.test.springsecuritydemo.security;

import com.test.springsecuritydemo.domain.entity.SysUser;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUser user) {
        return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getAuthorities(),
            user.isEnabled(),
            user.getLastPasswordResetDate()
        );
    }

}
