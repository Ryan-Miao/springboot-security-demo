Spring security learning
===
[![Build Status](https://travis-ci.org/Ryan-Miao/springboot-security-demo.svg?branch=master)](https://travis-ci.org/Ryan-Miao/springboot-security-demo)

## How to Run

```
git clone xxx
gradlew clean build bootRun
```


参见`com.test.springsecuritydemo.utils.MD5Util.main`来获得加密后的密码。



## 校验

### 基本密码
adminn-admin;

test-test;

### 获取token
访问[登陆接口](http://localhost:9095/swagger-ui.html#!/authentication45rest45controller/createAuthenticationTokenUsingPOST)

### 刷新token
访问[刷新接口](http://localhost:9095/swagger-ui.html#!/authentication45rest45controller/refreshAndGetAuthenticationTokenUsingGET)

### 验证权限

访问[权限测试接口](http://localhost:9095/swagger-ui.html#/role45perm45test45controller)




