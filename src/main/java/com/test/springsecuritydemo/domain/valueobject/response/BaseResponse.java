package com.test.springsecuritydemo.domain.valueobject.response;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public class BaseResponse<T> implements IApiResponse<T> {

    private final T value;
    private final ErrorMsg errorMsg;

    public BaseResponse(T value) {
        this.value = value;
        this.errorMsg = null;
    }

    public BaseResponse(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
        this.value = null;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean isSuccessful() {
        return this.errorMsg == null;
    }

    @Override
    public ErrorMsg getErrorMsg() {
        return this.errorMsg;
    }
}
