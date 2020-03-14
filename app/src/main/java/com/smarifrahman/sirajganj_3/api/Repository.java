package com.smarifrahman.sirajganj_3.api;

import android.content.Context;


import com.smarifrahman.sirajganj_3.ui.news.model.News;
import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

import java.util.List;

import io.reactivex.Flowable;


public class Repository {
    private APIService apiService;

    public Repository(Context context) {
        apiService = RetrofitAPIFactory.createService(context, 30);

    }

    public Flowable<Object> getHomeContent(String outletId) {
        return apiService.getHomeContents(outletId);
    }

    public Flowable<List<News>> allNews(){
        return apiService.getAllNews();
    }

    public Flowable<NewsDetails> newsDetails(int newsId){
        return apiService.getNewsDetails(newsId);
    }

}
