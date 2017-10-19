package me.ghui.example_android.network;

import io.reactivex.Observable;
import me.ghui.example_android.network.bean.DailyHotInfo;
import me.ghui.example_android.network.bean.NewsInfo;
import me.ghui.retrofit.converter.annotations.Html;
import me.ghui.retrofit.converter.annotations.Json;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ghui on 05/05/2017.
 */

public interface APIs {

    @GET("/api/topics/hot.json") @Json
    Observable<DailyHotInfo> dailyHot();

    @GET("/") @Html
    Observable<NewsInfo> homeNews(@Query("tab") String tab);

}
