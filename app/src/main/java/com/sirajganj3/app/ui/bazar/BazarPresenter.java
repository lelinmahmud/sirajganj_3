package com.sirajganj3.app.ui.bazar;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.bazar.models.BazarPostResponse;
import com.sirajganj3.app.ui.job.models.JobPostResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BazarPresenter {
    private static final String TAG = "BazarPresenter";
    private BazarView mView;
    private Repository repository;

    public BazarPresenter(BazarView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadBazar() {
        mView.showProgressBar();
        repository.getBazar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bazarSccess, this::onError);
    }

    void bazarSccess(List<BazarInfo> bazarInfos) {
        mView.loadBazarInfo(bazarInfos);
        Log.e(TAG, "bazarSccess: " + bazarInfos.size());
    }

    void onError(Throwable throwable) {
        mView.hideProgressBar();
        Log.e(TAG, "onError: " + throwable.getMessage());
    }

    void postBazar(RequestBody pName, RequestBody quantity, RequestBody price, RequestBody owner, RequestBody phone, MultipartBody.Part body) {
        mView.showProgressBar();
        repository.postBazar(pName, quantity, price, owner, phone, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::postBazarSuccess, this::onError);
    }

    void postBazarSuccess(BazarPostResponse bazarPostResponse) {
        mView.hideProgressBar();
        mView.showToast(bazarPostResponse.getMessage());
        Log.e(TAG, "postJobSuccess: " + bazarPostResponse.getMessage());
    }
}
