package com.sirajganj3.app.ui.opinion;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.area.models.AreaInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OpinionPresenter {
    private static final String TAG = "AreaPresenter";
    private OpinionView mView;
    private Repository repository;

    public OpinionPresenter(OpinionView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void opinionArea(){
        mView.showProgressBar();
        repository.getOpinion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::opinionSccess,this::onError);
    }

    void opinionSccess(List<Opinion> opinions){
        mView.opinonsInfo(opinions);
        Log.e(TAG, "Area Sccess: "+opinions.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
