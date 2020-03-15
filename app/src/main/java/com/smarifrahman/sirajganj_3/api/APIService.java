package com.smarifrahman.sirajganj_3.api;

import com.smarifrahman.sirajganj_3.ui.login.ForgotPassword;
import com.smarifrahman.sirajganj_3.ui.login.ForgotPasswordResponse;
import com.smarifrahman.sirajganj_3.ui.login.Register;
import com.smarifrahman.sirajganj_3.ui.login.RegisterResponse;
import com.smarifrahman.sirajganj_3.ui.login.User;
import com.smarifrahman.sirajganj_3.ui.news.model.News;
import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface APIService {

    @Headers({"ApplyOfflineCache: true", "ApplyResponseCache: true"})
    @GET("home-contents/{outlet_id}")
    Flowable<Object> getHomeContents(@Path("outlet_id") String outletId);

    @GET("ar/v1/posts")
    Flowable<List<News>> getAllNews();

    @GET("ar/v1/single/{news_id}")
    Flowable<NewsDetails> getNewsDetails(@Path("news_id") int newsId);

    @FormUrlEncoded
    @POST("jwt-auth/v1/token")
    Flowable<User> login
            (@Field("username") String username,
             @Field("password") String password);

    @POST("wp/v2/users/lostpassword")
    Flowable<ForgotPasswordResponse> lostpassword
            (@Body ForgotPassword forgotPassword);

    @POST("wp/v2/users/register")
    Flowable<RegisterResponse> registerUser
            (@Body Register register);



}
