package me.ghui.example_android.widget;

import android.widget.Toast;

import me.ghui.example_android.BuildConfig;
import me.ghui.example_android.general.App;
import me.ghui.example_android.util.PreConditions;


/**
 * Created by ghui on 11/07/2017.
 */

public class Voast {
    public static void show(String msg) {
        if (PreConditions.isEmpty(msg)) return;
        show(msg, false);
    }

    public static void show(String msg, boolean isToastLong) {
        Toast.makeText(App.get(), msg, isToastLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static void debug(String msg) {
        if (BuildConfig.DEBUG) {
            show(msg);
        }
    }
}
