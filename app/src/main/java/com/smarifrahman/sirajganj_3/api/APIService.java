package com.smarifrahman.sirajganj_3.api;

import com.smarifrahman.sirajganj_3.ui.news.model.News;
import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface APIService {

    @Headers({"ApplyOfflineCache: true", "ApplyResponseCache: true"})
    @GET("home-contents/{outlet_id}")
    Flowable<Object> getHomeContents(@Path("outlet_id") String outletId);

    @GET("posts")
    Flowable<List<News>> getAllNews();

    @GET("single/{news_id}")
    Flowable<NewsDetails> getNewsDetails(@Path("news_id") int newsId);


}
