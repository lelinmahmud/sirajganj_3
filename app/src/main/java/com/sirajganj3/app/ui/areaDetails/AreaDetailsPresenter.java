package com.sirajganj3.app.ui.areaDetails;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.news.model.NewsDetails;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AreaDetailsPresenter {

    private static final String TAG = "AreaDetailsPresenter";
    private AreaDetailsView mDetailsView;
    private Repository repository;

    public AreaDetailsPresenter(AreaDetailsView mDetailsView, Repository repository) {
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
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: " + throwable.getMessage());
        mDetailsView.hideProgressBar();
    }
}
