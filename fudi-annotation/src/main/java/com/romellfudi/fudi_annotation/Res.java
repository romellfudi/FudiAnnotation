package com.romellfudi.fudi_annotation;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Res {
    @IdRes int identifier() default View.NO_ID;
    Class className() default void.class;
}
