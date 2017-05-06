package me.ghui.fruit.annotations;

import java.lang.annotation.*;

/**
 * Created by ghui on 06/05/2017.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Nullable {
}
