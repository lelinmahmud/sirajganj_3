package com.sirajganj3.app.ui.bazar;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.job.models.JobPostResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class BazarPresenter {
    private static final String TAG = "BazarPresenter";
    private BazarView mView;
    private Repository repository;

    public BazarPresenter(BazarView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadBazar(){
        mView.showProgressBar();
        repository.getBazar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bazarSccess,this::onError);
    }

    void bazarSccess(List<BazarInfo> bazarInfos){
        mView.loadBazarInfo(bazarInfos);
        Log.e(TAG, "bazarSccess: "+bazarInfos.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }

    void postBazar(MultipartBody.Part body){
        mView.showProgressBar();
        repository.postBazar("Lelin","23","1200","lelin","000000",body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::postBazarSuccess,this::onError);
    }

    void postBazarSuccess(JobPostResponse jobPostResponse){
        mView.hideProgressBar();
      //  mView.showToast(jobPostResponse.getMessage());
        Log.e(TAG, "postJobSuccess: "+jobPostResponse.getMessage() );
    }
}
