package com.smarifrahman.sirajganj_3.ui.main.news;

import android.util.Log;

import com.smarifrahman.sirajganj_3.ui.api.Repository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter {
    private static final String TAG = "NewsPresenter";
    private NewsView mView;
    private Repository repository;

    public NewsPresenter(NewsView mView, Repository repository) {
        this.mView = mView;
        this.repository = repository;
    }

    public void getNews(){
        mView.showProgressBar();
        repository.allNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,this::onError);
    }


    private void onSuccess(List<News> newsList){
        for (int i=0;i<newsList.size();i++){
            Log.e(TAG, "onSuccess: news :"+newsList.get(i).getTitle());
            Log.e(TAG, "onSuccess: content :"+newsList.get(i).getContent());
        }

        mView.loadNews(newsList);

    }
//
//    public void news(){
//        Log.e(TAG, "news: is called" );
//        repository.News().enqueue(new Callback<List<News>>() {
//            @Override
//            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
//
//                Log.e(TAG, "onResponse: is called" );
//
//                if (response.isSuccessful()){
//
//                    Log.e(TAG, "onResponse: is called" );
//                    List<News> list=response.body();
//                    for (int i=0;i<list.size();i++){
//                        Log.e(TAG, "onSuccess: news :"+list.get(i).getTitle());
//                    }
//
//                }
//                else {
//                    try {
//                        Log.e(TAG, "onResponse: "+response.errorBody().string() );
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<News>> call, Throwable t) {
//                Log.e(TAG, "onFailure: "+t.getMessage() );
//            }
//        });
//    }
//
    private void onError(Throwable throwable){
        Log.e(TAG, "onError: "+throwable.getMessage() );
        mView.hideProgressBar();
    }

}
