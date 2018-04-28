package com.test.springsecuritydemo.domain.valueobject.response;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public class ErrorMsg {

    private final int code;
    private final String message;

    public ErrorMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
