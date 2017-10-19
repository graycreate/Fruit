package me.ghui.example_android.util;

import java.util.List;

/**
 * Created by ghui on 12/06/2017.
 */

public class PreConditions {
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
