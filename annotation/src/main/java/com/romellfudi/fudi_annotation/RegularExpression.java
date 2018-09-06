package com.romellfudi.fudi_annotation;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
@java.lang.annotation.Target({ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface RegularExpression {
    @IdRes int identifier();
    String regularExpression() ;
    String[] dataMultiple() default "0";
    int inputType() default 0;
    int maxLeght() default 0;
    boolean isIPAdress() default false;
}
