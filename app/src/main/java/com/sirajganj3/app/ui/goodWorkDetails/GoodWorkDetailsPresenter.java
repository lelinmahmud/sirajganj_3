package com.sirajganj3.app.ui.goodWorkDetails;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GoodWorkDetailsPresenter {
    private static final String TAG = "GoodWorkPresenter";
    private GoodWorkDetailsView mView;
    private Repository repository;

    public GoodWorkDetailsPresenter(GoodWorkDetailsView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadGoodDetailsWork(int id){
        mView.showProgressBar();
        repository.goodWorkDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::goodWorksDetailsSccess,this::onError);
    }

    private void goodWorksDetailsSccess(GoodWorkDetails goodWorks){
        mView.loadGoodWork(goodWorks);
    }

    private void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
