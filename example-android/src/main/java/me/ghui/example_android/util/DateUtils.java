package me.ghui.example_android.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ghui on 02/04/2017.
 */

public class DateUtils {

    public static String parseDate(long time) {
        return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(time));
    }
}
