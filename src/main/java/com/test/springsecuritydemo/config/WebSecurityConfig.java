package com.test.springsecuritydemo.config;

import com.test.springsecuritydemo.utils.CustomizedPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(securedEnabled = true)// 控制权限注解
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService customUserService;

    @Autowired
    public WebSecurityConfig(UserDetailsService customUserService) {
        this.customUserService = customUserService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new CustomizedPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin().loginPage("/login").failureUrl("/login?error")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
            .exceptionHandling().accessDeniedPage("/access?error")
            .and()
            .httpBasic();
        ;
        // @formatter:on
    }
}
