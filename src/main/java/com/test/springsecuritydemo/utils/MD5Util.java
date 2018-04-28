package com.test.springsecuritydemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MD5Util {

    private static final String SALT = "tamboo";

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void md5() {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        // false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true  表示：生成24位的Base64版
        md5.setEncodeHashAsBase64(false);
        String pwd = md5.encodePassword("1234", null);
        System.out.println("MD5: " + pwd + " len=" + pwd.length());
    }

    public static void sha_256() throws NoSuchAlgorithmException {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        sha.setEncodeHashAsBase64(true);
        String pwd = sha.encodePassword("1234", null);
        System.out.println("哈希算法 256: " + pwd + " len=" + pwd.length());
    }


    public static void sha_SHA_256() {
        ShaPasswordEncoder sha = new ShaPasswordEncoder();
        sha.setEncodeHashAsBase64(false);
        String pwd = sha.encodePassword("1234", null);
        System.out.println("哈希算法 SHA-256: " + pwd + " len=" + pwd.length());
    }


    public static void md5_SystemWideSaltSource() {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        String pwd = md5.encodePassword("1234", "acegisalt");
        System.out.println("MD5 SystemWideSaltSource: " + pwd + " len=" + pwd.length());
    }

    public static void bc() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("admin= " + encoder.encode("admin"));
        System.out.println("test= " + encoder.encode("test"));

        //相同密码随机加密
        System.out.println("123456= " + encoder.encode("123456"));
        System.out.println("123456= " + encoder.encode("123456"));
        boolean matches = encoder
            .matches("123456", encoder.encode("123456"));
        System.out.println("解析=" + matches);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        md5(); // 使用简单的MD5加密方式

        sha_256(); // 使用256的哈希算法(SHA)加密

        sha_SHA_256(); // 使用SHA-256的哈希算法(SHA)加密

        md5_SystemWideSaltSource(); // 使用MD5再加全局加密盐加密的方式加密

        bc();//官方推荐
    }


}
