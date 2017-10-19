package me.ghui.example_android.network;

import android.text.TextUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ghui.fruit.Fruit;
import me.ghui.fruit.converter.retrofit.FruitConverterFactory;
import me.ghui.retrofit.converter.GlobalConverterFactory;
import me.ghui.retrofit.converter.annotations.Html;
import me.ghui.retrofit.converter.annotations.Json;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by ghui on 25/03/2017.
 */

public class APIService {
    public static final String WAP_USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Mobile Safari/537.36";
    public static final String UA_KEY = "user-agent";

    public static final long TIMEOUT_LENGTH = 30;
    private static APIs mAPI_SERVICE;
    private static Gson sGson;
    private static Fruit sFruit;
    private static OkHttpClient sHttpClient;


    public static void init() {
        if (mAPI_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient())
                    .addConverterFactory(GlobalConverterFactory
                            .create()
                            .add(FruitConverterFactory.create(fruit()), Html.class)
                            .add(GsonConverterFactory.create(gson()), Json.class))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://www.v2ex.com")
                    .build();
            mAPI_SERVICE = retrofit.create(APIs.class);
        }
    }

    private static class ConfigInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String ua = request.header(UA_KEY);
            if (TextUtils.isEmpty(ua)) {
                request = request.newBuilder()
                        .addHeader("user-agent", WAP_USER_AGENT)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    public static APIs get() {
        return mAPI_SERVICE;
    }

    public static Gson gson() {
        if (sGson == null) {
            sGson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
        }
        return sGson;
    }

    public static Fruit fruit() {
        if (sFruit == null) {
            sFruit = new Fruit();
        }
        return sFruit;
    }

    public static OkHttpClient httpClient() {
        if (sHttpClient == null) {
            sHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_LENGTH, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new ConfigInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return sHttpClient;
    }

}
