package com.smarifrahman.sirajganj_3.ui.news;

import android.util.Log;

import com.smarifrahman.sirajganj_3.api.Repository;
import com.smarifrahman.sirajganj_3.ui.news.model.News;
import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailsPresenter {

    private static final String TAG = "NewsDetailsPresenter";
    private NewsDetailsView mDetailsView;
    private Repository repository;

    public NewsDetailsPresenter(NewsDetailsView mDetailsView, Repository repository) {
        this.mDetailsView = mDetailsView;
        this.repository = repository;
    }

    public void getNewsDetails(int newsId) {
        mDetailsView.showProgressBar();
        repository.newsDetails(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
    }


    private void onSuccess(NewsDetails newsDetails) {
        mDetailsView.loadNewsDetails(newsDetails);

        Log.e(TAG, "onSuccess: " + newsDetails);

    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: " + throwable.getMessage());
        mDetailsView.hideProgressBar();
    }
}
