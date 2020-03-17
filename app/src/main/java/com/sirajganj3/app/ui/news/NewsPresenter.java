package com.sirajganj3.app.ui.news;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.news.model.News;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter {
    private static final String TAG = "NewsPresenter";
    private NewsView mView;
    private Repository repository;

    public NewsPresenter(NewsView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void getNews(){
        mView.showProgressBar();
        repository.allNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,this::onError);
    }


    private void onSuccess(List<News> newsList){
        for (int i=0;i<newsList.size();i++){
            Log.e(TAG, "onSuccess: news :"+newsList.get(i).getTitle());
            Log.e(TAG, "onSuccess: content :"+newsList.get(i).getContent());
            Log.e(TAG, "onSuccess: News ID: " + newsList.get(i).getId());
        }

        mView.loadNews(newsList);

    }

    private void onError(Throwable throwable){
        Log.e(TAG, "onError: "+throwable.getMessage() );
        mView.hideProgressBar();
    }

}
