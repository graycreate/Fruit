package me.ghui.fruit.annotations;

import me.ghui.fruit.Attrs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ghui on 10/04/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Pick {
    String value();

    String attr() default Attrs.TEXT;
}
