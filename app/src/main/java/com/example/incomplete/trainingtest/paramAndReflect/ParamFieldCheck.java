package com.example.incomplete.trainingtest.paramAndReflect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 必填参数标识
 * @author maxiaolong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamFieldCheck {

    public String valueString() default "null";

    public byte valueByte() default Byte.MIN_VALUE;

    public short valueShort() default Short.MIN_VALUE;

    public int valueInt() default Integer.MIN_VALUE;

    public long valueLong() default Long.MIN_VALUE;

    public char valueChar() default Character.MIN_VALUE;

    public float valueFloat() default Float.MIN_VALUE;

    public double valueDouble() default Double.MIN_VALUE;
}
