package com.sirajganj3.app.api;

import android.content.Context;


import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.bazar.models.BazarPostResponse;
import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.job.models.JobInfo;
import com.sirajganj3.app.ui.job.models.JobPostResponse;
import com.sirajganj3.app.ui.login.User;
import com.sirajganj3.app.ui.news.model.News;
import com.sirajganj3.app.ui.news.model.NewsDetails;
import com.sirajganj3.app.ui.opinion.Opinion;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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
    public Flowable<List<Opinion>> getOpinion(){
        return apiService.getOpinion();
    }
    public Flowable<List<JobInfo>> getJobs(){
        return apiService.getJobInfo();
    }
    public Flowable<List<Vehicle>> getTransport(){
        return apiService.getTransport();
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
    public Flowable<BazarPostResponse> postBazar(RequestBody productName, RequestBody quantity, RequestBody price, RequestBody seller, RequestBody phone, MultipartBody.Part image){
        return apiService.postBazar(productName,quantity,price,seller,phone,image);
    }

    public Flowable<BazarPostResponse>postOpinion(String title,String details){
        return apiService.postOpinion(title,details);
    }

    public Flowable<BazarPostResponse> postGoodWord(RequestBody name, RequestBody village, RequestBody thana, RequestBody details, MultipartBody.Part image){
        return apiService.postGoodWork(name,village,thana,details,image);
    }
    public Flowable<BazarPostResponse> postVehicle(RequestBody type, RequestBody driver, RequestBody phone, MultipartBody.Part image){
        return apiService.postVehicle(type,driver,phone,image);
    }

}
