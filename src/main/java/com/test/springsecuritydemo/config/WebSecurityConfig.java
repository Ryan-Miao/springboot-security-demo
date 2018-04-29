package com.test.springsecuritydemo.config;

import com.test.springsecuritydemo.constant.AuthorityEnum;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(securedEnabled = true)// 控制权限注解
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService customUserService;

    @Autowired
    public WebSecurityConfig(UserDetailsService customUserService) {
        this.customUserService = customUserService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.headers().frameOptions().disable()
            // 针对Cross Site Request Forgery(跨站请求伪造)开启token验证，即除了get之外的http动词都将被这个
            //e.g. Set-Cookie: XSRF-TOKEN=89d9a369-553b-45f2-8fd0-1114dd5cd01f; Path=/
            //则post等请求必须携带header X-XSRF-TOKEN=这个值;
            //这个可以防止用户打开本网站后，未关闭浏览器而访问钓鱼网站post正规请求。见https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-cookie
            .and().cors()
            .and()
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/**").hasAuthority(AuthorityEnum.ROLE_ADMIN.name())
            .antMatchers("/login*").permitAll()
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin().loginPage("/login").failureUrl("/login?error")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
            .exceptionHandling().accessDeniedPage("/access?error")
            .and()
            .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
            .and()
            .httpBasic();
            // @formatter:on
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://corshost:9095"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
