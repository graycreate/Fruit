package me.ghui.example_android.general;

import android.app.Application;
import me.ghui.example_android.network.APIService;

public class App extends Application{
    private static App sInstance;

    public static App get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        APIService.init();
    }
}
