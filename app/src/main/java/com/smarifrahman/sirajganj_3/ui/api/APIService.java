package com.smarifrahman.sirajganj_3.ui.api;

import com.smarifrahman.sirajganj_3.ui.main.news.News;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface APIService {

    @Headers({"ApplyOfflineCache: true", "ApplyResponseCache: true"})
    @GET("home-contents/{outlet_id}")
    Flowable<Object> getHomeContents(@Path("outlet_id")String outletId);

    @GET("posts")
    Flowable<List<News>> getAllNews();

}
