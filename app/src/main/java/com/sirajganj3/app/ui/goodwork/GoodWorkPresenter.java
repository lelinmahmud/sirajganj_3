package com.sirajganj3.app.ui.goodwork;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GoodWorkPresenter {
    private static final String TAG = "GoodWorkPresenter";
    private GoodWorkView mView;
    private Repository repository;

    public GoodWorkPresenter(GoodWorkView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadGoodWork(){
        mView.showProgressBar();
        repository.getGoodWork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::goodWorksSccess,this::onError);
    }

    void goodWorksSccess(List<GoodWork> goodWorks){
        mView.loadGoodWork(goodWorks);
        Log.e(TAG, "goodWorks Sccess: "+goodWorks.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
