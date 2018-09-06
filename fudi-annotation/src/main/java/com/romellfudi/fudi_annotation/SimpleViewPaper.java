package com.romellfudi.fudi_annotation;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;


/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SimpleViewPaper {
    Class c();
    @StringRes String t();
    @DrawableRes int d();
}
