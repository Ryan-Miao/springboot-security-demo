package com.test.springsecuritydemo.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 辅助理解加密原理。这是最简单的方式，固定加盐.
 * @deprecated 不用这种方式，@see BCryptPasswordEncoder
 */
@Deprecated
public class CustomizedPasswordEncoder implements PasswordEncoder {

    private static final String SALT = "密码加密的yan";

    private Md5PasswordEncoder md5;

    public CustomizedPasswordEncoder() {
        md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);
    }

    @Override
    public String encode(CharSequence rawPassword) {

        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        String rawPass = String.valueOf(rawPassword);
        String pwd = md5.encodePassword(rawPass, SALT);
        System.out.println("MD5 SystemWideSaltSource: " + pwd + " len=" + pwd.length());

        return pwd;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }

}
