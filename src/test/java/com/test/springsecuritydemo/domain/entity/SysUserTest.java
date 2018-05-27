package com.test.springsecuritydemo.domain.entity;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Lists;
import com.test.springsecuritydemo.constant.AuthorityEnum;
import java.util.Collection;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

public class SysUserTest {

    /**
     * 当User没有关联role的时候.
     */
    @Test
    public void getAuthoritiesNone() {
        SysUser sysUser = new SysUser();
        sysUser.setRole(Lists.newArrayList());

        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        assertEquals(0, authorities.size());
    }

    /**
     * 当User关联role的时候.
     */
    @Test
    public void getAuthoritiesSuccess() {

        SysRole sysRole = new SysRole();
        sysRole.setName(AuthorityEnum.ROLE_ADMIN);
        SysUser sysUser = new SysUser();
        sysUser.setRole(Lists.newArrayList(sysRole));

        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        System.out.println(authorities);
        assertEquals(1, authorities.size());
        authorities.forEach(a -> {
            String authority = a.getAuthority();
            assertEquals("ROLE_ADMIN", authority);
        });
    }
}