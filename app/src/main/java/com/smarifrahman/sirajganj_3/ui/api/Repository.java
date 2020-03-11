package com.audacityit.meenaclick.core.api;

import android.content.Context;

import io.reactivex.Flowable;

/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */
public class Repository {
    private APIService apiService;

    public Repository(Context context) {
        apiService = RetrofitAPIFactory.createService(context, 30);
    }

    public Flowable<Object> getHomeContent(String outletId) {
        return apiService.getHomeContents(outletId);
    }
}
