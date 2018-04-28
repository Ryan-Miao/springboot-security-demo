package com.test.springsecuritydemo.domain.valueobject.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public class ResponseMapper {

    public static <T> ResponseEntity<IApiResponse<T>> map(IApiResponse<T> one) {
        if (one.isSuccessful()) {
            return ResponseEntity.ok(one);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(one);
    }
}
