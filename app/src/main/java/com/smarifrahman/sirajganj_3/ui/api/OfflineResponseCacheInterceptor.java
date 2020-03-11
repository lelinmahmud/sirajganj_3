package com.audacityit.meenaclick.core.api;

/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */


import com.audacityit.meenaclick.core.App;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor to cache data and maintain it for four weeks.
 * <p>
 * If the device is offline, stale (at most four weeks old)
 * response is fetched from the cache.
 */

public class OfflineResponseCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (Boolean.valueOf(request.header("ApplyOfflineCache"))) {
            if (!App.getApp().isNetworkAvailable()) {
                request = request.newBuilder()
                        .removeHeader("ApplyOfflineCache")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 2419200)
                        .build();
            }
        }
        return chain.proceed(request);
    }
}
