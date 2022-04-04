package com.zhanarbek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
