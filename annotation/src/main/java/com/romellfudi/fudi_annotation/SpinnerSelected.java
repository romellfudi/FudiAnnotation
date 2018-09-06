package com.romellfudi.fudi_annotation;

import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
@java.lang.annotation.Target({ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SpinnerSelected {
    @IdRes int identifier() ;
    @ArrayRes int arrayStringId();
    @LayoutRes int typeSpinner() default android.R.layout.simple_spinner_item;
}
