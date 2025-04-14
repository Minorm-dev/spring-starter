package com.minorm.logging.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class CommonPointcuts {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    @Pointcut("within(com.minorm.*.service.*Service)")
    public void isServiceLayer() {
    }
}
