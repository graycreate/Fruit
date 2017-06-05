package me.ghui.fruit.internal;

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

    public static boolean isNotEmpty(String text) {
        return text != null && text.length() > 0;
    }
}
