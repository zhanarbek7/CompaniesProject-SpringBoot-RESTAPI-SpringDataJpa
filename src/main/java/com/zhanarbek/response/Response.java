package com.zhanarbek.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
@Data
@Builder
public class Response{
    private HttpStatus httpStatus;
    private String message;
}
