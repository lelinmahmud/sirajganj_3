package com.sirajganj3.app.api;

import android.media.Image;

import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.areaDetails.AreaNews;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.bazar.models.BazarPostResponse;
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.job.models.JobInfo;
import com.sirajganj3.app.ui.job.models.JobPostResponse;
import com.sirajganj3.app.ui.login.ForgotPassword;
import com.sirajganj3.app.ui.login.ForgotPasswordResponse;
import com.sirajganj3.app.ui.login.Register;
import com.sirajganj3.app.ui.login.RegisterResponse;
import com.sirajganj3.app.ui.login.User;
import com.sirajganj3.app.ui.news.model.News;
import com.sirajganj3.app.ui.news.model.NewsDetails;
import com.sirajganj3.app.ui.opinion.Opinion;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("acf/v3/bazar")
    Flowable<List<BazarInfo>> getBazarInfo();
    @GET("acf/v3/transport")
    Flowable<List<Vehicle>> getTransport();

    @GET("acf/v3/jobs")
    Flowable<List<JobInfo>> getJobInfo();

    @GET("ar/v1/area")
    Flowable<List<AreaInfo>> getAreaInfo();

    @GET("acf/v3/good_work")
    Flowable<List<GoodWork>> getGoodWork();



    @GET("acf/v3/good_work/{news_id}")
    Flowable<GoodWorkDetails> getGoodWorkDetails(@Path("news_id") int newsId);

    @GET("ar/v1/opinion")
    Flowable<List<Opinion>> getOpinion();

    @FormUrlEncoded
    @POST("contact-form-7/v1/contact-forms/183/feedback")
    Flowable<JobPostResponse> postJob(
            @Field("post-name") String postName,
            @Field("company") String company,
            @Field("salary") String salary,
            @Field("owner") String owner,
            @Field("phone") String phone

    );

    @Multipart
    @POST("contact-form-7/v1/contact-forms/173/feedback")
    Flowable<BazarPostResponse> postBazar(
            @Part("product-name") RequestBody productName,
            @Part("p-quantity") RequestBody quantity,
            @Part("price") RequestBody price,
            @Part("seller") RequestBody seller,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part file
    );


    @Multipart
    @POST("contact-form-7/v1/contact-forms/187/feedback")
    Flowable<BazarPostResponse> postGoodWork(
            @Part("person-name") RequestBody name,
            @Part("village") RequestBody village,
            @Part("thana") RequestBody thana,
            @Part("details") RequestBody details,
            @Part MultipartBody.Part file
    );


}
