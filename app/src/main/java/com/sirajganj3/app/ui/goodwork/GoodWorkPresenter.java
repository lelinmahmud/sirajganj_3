package com.sirajganj3.app.ui.goodwork;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.bazar.models.BazarPostResponse;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.goodwork.models.GoodWorkPostResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    void postGoodWork(RequestBody name, RequestBody village, RequestBody thana, RequestBody details, MultipartBody.Part image){
        mView.showProgressBar();
        repository.postGoodWord(name,village,thana,details,image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::goodWorksPostSccess,this::onError);

    }


    void goodWorksSccess(List<GoodWork> goodWorks){
        mView.loadGoodWork(goodWorks);
        Log.e(TAG, "goodWorks Sccess: "+goodWorks.size() );
    }

    void goodWorksPostSccess(BazarPostResponse response){
      //  mView.loadGoodWork(goodWorks);
        mView.hideProgressBar();
        mView.showToast(response.getMessage());
        Log.e(TAG, "goodWorks Sccess: "+response.getMessage() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
