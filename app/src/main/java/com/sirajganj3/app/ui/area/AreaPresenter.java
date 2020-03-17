package com.sirajganj3.app.ui.area;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.area.models.AreaInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AreaPresenter {
    private static final String TAG = "AreaPresenter";
    private AreaView mView;
    private Repository repository;

    public AreaPresenter(AreaView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadArea(){
        mView.showProgressBar();
        repository.getArea()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::aresSccess,this::onError);
    }

    void aresSccess(List<AreaInfo> areaInfos){
        mView.loadAresInfo(areaInfos);
        Log.e(TAG, "Area Sccess: "+areaInfos.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
