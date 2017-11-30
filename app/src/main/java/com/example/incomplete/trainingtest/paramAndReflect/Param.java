package com.example.incomplete.trainingtest.paramAndReflect;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by incomplete on 17/7/21.
 */
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Param {
    int value() default  1;  //
}
