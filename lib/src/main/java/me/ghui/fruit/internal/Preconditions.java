package me.ghui.fruit.internal;

import java.util.List;

/**
 * Created by ghui on 13/04/2017.
 */

public class Preconditions {
    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() <= 0;
    }

    public static boolean notEmpty(CharSequence... texts) {
        for (CharSequence text : texts) {
            if (isEmpty(text)) return false;
        }
        return true;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean notEmpty(List list) {
        return !isEmpty(list);
    }

}
