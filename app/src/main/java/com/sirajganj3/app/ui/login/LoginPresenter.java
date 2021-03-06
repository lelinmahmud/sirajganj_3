package com.sirajganj3.app.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.utils.SharedPrefManager;

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
        SharedPrefManager.getInstance(context).saveUser(user);

        loginView.navigateToMain();
    }
    private void loginError(Throwable throwable){
        loginView.hideProgressBar();
        Toast.makeText(context, R.string.wrong_password,Toast.LENGTH_SHORT).show();

    }
}
