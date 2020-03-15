package com.smarifrahman.sirajganj_3.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.api.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    private Repository repository;
    private LoginView loginView;
    private Context context;

    public LoginPresenter(Repository repository, LoginView loginView, Context context) {
        this.repository = repository;
        this.loginView = loginView;
        this.context = context;
    }

    public void userLogin(String username, String password){
        loginView.showProgressBar();
        repository.login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loginSuccess,this::loginError);
    }

    private void loginSuccess(User user){
        Log.e(TAG, "loginSuccess: "+user.getUserDisplayName() );
        loginView.navigateToMain();
    }
    private void loginError(Throwable throwable){
        loginView.hideProgressBar();
        Toast.makeText(context, R.string.wrong_password,Toast.LENGTH_SHORT).show();

    }
}
