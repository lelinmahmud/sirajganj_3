package com.sirajganj3.app.ui.job;

import android.util.Log;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.job.models.JobInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JobPresenter {
    private static final String TAG = "JobPresenter";
    private JobView mView;
    private Repository repository;

    public JobPresenter(JobView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void loadJobs(){
        mView.showProgressBar();
        repository.getJobs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::jobSccess,this::onError);
    }

    void jobSccess(List<JobInfo> jobInfos){
        mView.loadJobInfo(jobInfos);
        Log.e(TAG, "job Sccess: "+jobInfos.size() );
    }

    void onError(Throwable throwable){
        mView.hideProgressBar();
        Log.e(TAG, "onError: "+throwable.getMessage() );
    }
}
