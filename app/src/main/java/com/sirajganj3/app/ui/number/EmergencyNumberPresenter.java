package com.sirajganj3.app.ui.number;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.number.model.EmergencyNumberInfo;
import com.sirajganj3.app.ui.number.model.EmergencyNumberResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EmergencyNumberPresenter {
    private static final String TAG = "EmergencyNumPresenter";
    private EmergencyNumberView emergencyNumberView;
    private Repository repository;

    public EmergencyNumberPresenter(EmergencyNumberView emergencyNumberView, Repository repository) {
        this.emergencyNumberView = emergencyNumberView;
        this.repository = repository;
    }

    public void getEmergencyNumber() {
        emergencyNumberView.showProgressBar();
        repository.getEmergencyNumber()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
    }


    private void onSuccess(List<EmergencyNumberInfo> numberResponse) {
        emergencyNumberView.loadEmergencyNumber(numberResponse);
        Log.e(TAG, "onSuccess: " + numberResponse);

    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: " + throwable.getMessage());
        emergencyNumberView.hideProgressBar();
    }
}
