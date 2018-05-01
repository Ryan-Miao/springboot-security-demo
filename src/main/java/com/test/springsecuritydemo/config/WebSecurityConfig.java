package com.test.springsecuritydemo.config;

import com.test.springsecuritydemo.constant.AuthorityEnum;
import com.test.springsecuritydemo.security.JwtAuthorizationTokenFilter;
import com.test.springsecuritydemo.security.JwtTokenUtil;
import com.test.springsecuritydemo.security.JwtUserDetailsService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(securedEnabled = true)// 控制权限注解
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService jwtUserDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;


    @Autowired
    public WebSecurityConfig(JwtUserDetailsService jwtUserDetailsService,
        JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.headers().frameOptions().disable()
            // 针对Cross Site Request Forgery(跨站请求伪造)开启token验证，即除了get之外的http动词都将被这个
            //e.g. Set-Cookie: XSRF-TOKEN=89d9a369-553b-45f2-8fd0-1114dd5cd01f; Path=/
            //则post等请求必须携带header X-XSRF-TOKEN=这个值;
            //这个可以防止用户打开本网站后，未关闭浏览器而访问钓鱼网站post正规请求。见https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-cookie
            .and()
            // we don't need CSRF because our token is invulnerable
            //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .csrf().disable()
            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/**").hasAuthority(AuthorityEnum.ROLE_ADMIN.name())
            .antMatchers("/login*").permitAll()
            .anyRequest().authenticated()

            .and()
            .formLogin().loginPage("/login").failureUrl("/login?error")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Unauthorized"));
        // @formatter:on

        // Custom JWT based security filter
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(
            userDetailsService(), jwtTokenUtil, tokenHeader);
        http
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) {
        // AuthenticationTokenFilter will ignore the below paths
        web.ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath
            )

            // allow anonymous resource requests
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/swagger-*/**",
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
            );
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
