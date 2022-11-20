package com.example.aoptest.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperate {
    String desc() default "";

    Class<? extends Convert> convert();
}
