package com.sirajganj3.app.ui.communication;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.job.models.JobPostResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class CommunicationPresenter {
    private static final String TAG = "BazarPresenter";
    private CommunicationView mView;
    private Repository repository;

    public CommunicationPresenter(CommunicationView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadTransport(){
        mView.showProgressBar();
        repository.getTransport()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bazarSccess,this::onError);
    }

    void bazarSccess(List<Vehicle> vehicles){
        mView.loadTransport(vehicles);
        Log.e(TAG, "bazarSccess: "+vehicles.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }


}
