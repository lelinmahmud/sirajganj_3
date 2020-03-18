package com.sirajganj3.app.api;

import android.content.Context;


import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.job.models.JobInfo;
import com.sirajganj3.app.ui.job.models.JobPostResponse;
import com.sirajganj3.app.ui.login.User;
import com.sirajganj3.app.ui.news.model.News;
import com.sirajganj3.app.ui.news.model.NewsDetails;

import java.util.List;

import io.reactivex.Flowable;


public class Repository {
    private APIService apiService;

    public Repository(Context context) {
        apiService = RetrofitAPIFactory.createService(context, 30);

    }

    public Flowable<Object> getHomeContent(String outletId) {
        return apiService.getHomeContents(outletId);
    }

    public Flowable<List<News>> allNews(){
        return apiService.getAllNews();
    }

    public Flowable<NewsDetails> newsDetails(int newsId){
        return apiService.getNewsDetails(newsId);
    }
    public Flowable<GoodWorkDetails> goodWorkDetails(int newsId){
        return apiService.getGoodWorkDetails(newsId);
    }
    public Flowable<User> login(String username, String password){
        return apiService.login(username,password);
    }

    public Flowable<List<BazarInfo>> getBazar(){
        return apiService.getBazarInfo();
    }
    public Flowable<List<JobInfo>> getJobs(){
        return apiService.getJobInfo();
    }

    public Flowable<List<AreaInfo>> getArea(){
        return apiService.getAreaInfo();
    }
    public Flowable<List<GoodWork>> getGoodWork(){
        return apiService.getGoodWork();
    }

    public Flowable<JobPostResponse> postJob(String postName,String company,String salary,String owner,String phone){
        return apiService.postJob(postName,company,salary,owner,phone);
    }

}
