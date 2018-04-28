package com.test.springsecuritydemo.domain.dao;

import com.test.springsecuritydemo.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserRepository extends JpaRepository<SysUser, Integer>,
    JpaSpecificationExecutor<SysUser> {

    SysUser findByUsername(String username);
}
