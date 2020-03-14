package com.smarifrahman.sirajganj_3.api;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;


import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitAPIFactory {
    private final static String BASE_URL = "https://sirajganj3.com.bd/wp-json/ar/v1/";
    private final static int TIME_OUT = 30;
    private final static long cacheSize = 100 * 1024 * 1024;

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;
    private static Cache cache = null;

    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create();

    static APIService createService(Context context, int timeOut) {
        return getRetrofit(context, timeOut).create(APIService.class);
    }

    private static Retrofit getRetrofit(Context context, int timeOut) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }


    static Cache getCache(Context context) {
        if (cache == null) {
            File cacheDir = new File(context.getCacheDir(), "okhttp-cache");
            cache = new Cache(cacheDir, cacheSize);
        }
        return cache;
    }

}
