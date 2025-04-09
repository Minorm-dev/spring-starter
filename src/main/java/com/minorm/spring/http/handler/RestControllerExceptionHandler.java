package com.minorm.spring.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.minorm.spring.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
}
