package com.audacityit.meenaclick.core.api;

import android.content.Context;

import com.audacityit.meenaclick.BuildConfig;
import com.audacityit.meenaclick.R;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */
public class RetrofitAPIFactory {
    private final static String BASE_URL = BuildConfig.BASE_URL;
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
                    .client(getOkHttpClient(context, timeOut))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient getOkHttpClient(Context context, int timeOut) {
        if (okHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            okHttpClientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(timeOut, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(timeOut, TimeUnit.SECONDS);

            okHttpClientBuilder.cache(getCache(context));

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            okHttpClientBuilder.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context)));

            okHttpClientBuilder.addNetworkInterceptor(new ResponseCacheInterceptor());
            okHttpClientBuilder.addInterceptor(new OfflineResponseCacheInterceptor());

            okHttpClientBuilder.addInterceptor(chain ->
                    chain.proceed(chain.request().newBuilder().build()));

            LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .tag(context.getString(R.string.app_name))
                    .request("Request")
                    .response("Response")
                    .executor(Executors.newSingleThreadExecutor())
                    .build();

            okHttpClientBuilder.addInterceptor(loggingInterceptor);

            okHttpClient = okHttpClientBuilder.build();
        }

        return okHttpClient;
    }

    static Cache getCache(Context context) {
        if (cache == null) {
            File cacheDir = new File(context.getCacheDir(), "okhttp-cache");
            cache = new Cache(cacheDir, cacheSize);
        }
        return cache;
    }

}
