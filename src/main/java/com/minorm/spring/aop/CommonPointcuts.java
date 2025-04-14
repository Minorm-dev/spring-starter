package com.minorm.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    /*
        @ - Все помеченное так, используется с аннотациями, без нее надо указывать класс
        @within - check annotation on the class level
    */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

     /*
        within - check class type name
     */
    @Pointcut("within(com.minorm.spring.service.*Service)")
    public void isServiceLayer() {
    }
}
