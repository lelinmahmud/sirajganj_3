package com.sirajganj3.app.api;

import com.sirajganj3.app.ui.area.models.AreaInfo;
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
import com.sirajganj3.app.ui.number.model.EmergencyNumberInfo;
import com.sirajganj3.app.ui.opinion.Opinion;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    //GET NEWS
    @GET("ar/v1/posts")
    Flowable<List<News>> getAllNews();

    //GET NEWS DETAILS
    @GET("ar/v1/single/{news_id}")
    Flowable<NewsDetails> getNewsDetails(@Path("news_id") int newsId);

    //LOGIN
    @FormUrlEncoded
    @POST("jwt-auth/v1/token")
    Flowable<User> login
            (@Field("username") String username,
             @Field("password") String password);

    //LOST PASSWORD
    @POST("wp/v2/users/lostpassword")
    Flowable<ForgotPasswordResponse> lostpassword
            (@Body ForgotPassword forgotPassword);

    //REGISTER
    @POST("wp/v2/users/register")
    Flowable<RegisterResponse> registerUser
            (@Body Register register);

    //GET BAZAR
    @GET("acf/v3/bazar")
    Flowable<List<BazarInfo>> getBazarInfo();

    //GET VEHICLES
    @GET("acf/v3/transport")
    Flowable<List<Vehicle>> getTransport();

    //GET JOB
    @GET("acf/v3/jobs")
    Flowable<List<JobInfo>> getJobInfo();

    //GET AREA
    @GET("ar/v1/area")
    Flowable<List<AreaInfo>> getAreaInfo();

    //GET GOOD WORK
    @GET("acf/v3/good_work")
    Flowable<List<GoodWork>> getGoodWork();

    //GET GOOD WORK DETAILS
    @GET("acf/v3/good_work/{news_id}")
    Flowable<GoodWorkDetails> getGoodWorkDetails(@Path("news_id") int newsId);

    //GET OPINION
    @GET("ar/v1/opinion")
    Flowable<List<Opinion>> getOpinion();

    //GET EMERGENCY NUMBER
    @GET("acf/v3/emergency")
    Flowable<List<EmergencyNumberInfo>> getEmergencyNumber();

    //POST JOB
    @FormUrlEncoded
    @POST("contact-form-7/v1/contact-forms/183/feedback")
    Flowable<JobPostResponse> postJob(
            @Field("post-name") String postName,
            @Field("company") String company,
            @Field("salary") String salary,
            @Field("owner") String owner,
            @Field("phone") String phone

    );

    //POST BAZAR
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

    //POST GOOD WORK
    @Multipart
    @POST("contact-form-7/v1/contact-forms/187/feedback")
    Flowable<BazarPostResponse> postGoodWork(
            @Part("person-name") RequestBody name,
            @Part("village") RequestBody village,
            @Part("thana") RequestBody thana,
            @Part("details") RequestBody details,
            @Part MultipartBody.Part file
    );

    //POST Vehicles
    @Multipart
    @POST("contact-form-7/v1/contact-forms/368/feedback")
    Flowable<BazarPostResponse> postVehicle(
            @Part("t-type") RequestBody type,
            @Part("driver") RequestBody driver,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part file
    );

    // POST Opinion
    @FormUrlEncoded
    @POST("contact-form-7/v1/contact-forms/509/feedback")
    Flowable<BazarPostResponse> postOpinion(
            @Field("title") String title,
            @Field("your-message") String msg,
            @Field("your-name") String name
    );


}
