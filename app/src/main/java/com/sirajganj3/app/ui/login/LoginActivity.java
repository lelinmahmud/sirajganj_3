package com.sirajganj3.app.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityLoginBinding;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.utils.MySharedPrefarance;
import com.sirajganj3.app.utils.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private ActivityLoginBinding mLoginBinding;
    private LoginPresenter mPresenter;
    private Repository repository = new Repository(this);

    public static final String LOGIN_URL = "https://sirajganj3.com.bd/login-page/?login=true";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        mLoginBinding.loginWv.getSettings().setJavaScriptEnabled(true);
        mLoginBinding.loginWv.getSettings().setDomStorageEnabled(true);
        mLoginBinding.loginWv.setVerticalScrollBarEnabled(false);
        mLoginBinding.loginWv.loadUrl(LOGIN_URL);
        mLoginBinding.loginWv.setWebViewClient(new MyWebClient());


//        if (SharedPrefManager.getInstance(this).loggedIn()) {
//            navigateToMain();
//            return;
//        }

//        mPresenter = new LoginPresenter(repository, this, this);
//        mLoginBinding.loginBtn.setOnClickListener(this);
//        mLoginBinding.registerBtn.setOnClickListener(this);


    }


    private class MyWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mLoginBinding.loginForm.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            //Hide Specific Part of WebView
            view.loadUrl("javascript:(function() { " +

                    "document.getElementsByClassName('header dark')[0].style.display='none'; " +

                    "})()");

            if (url.equals("https://sirajganj3.com.bd/")) {

                //Set Login session in SharedPreference
                MySharedPrefarance mySharedPrefarance = MySharedPrefarance.getPrefarences(LoginActivity.this);
                mySharedPrefarance.setLoginSession(url);

                //verification successful we will start the profile activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                mLoginBinding.logoIv.setVisibility(View.VISIBLE);
                mLoginBinding.loginForm.setVisibility(View.VISIBLE);
                mLoginBinding.loadingProgressbar.setVisibility(View.GONE);
                mLoginBinding.logoIvTemp.setVisibility(View.GONE);
            }


        }
    }


    private boolean validation(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        /*
        if (id == R.id.login_btn) {
            Intent registerIntent = new Intent(this, MainActivity.class);
            registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(registerIntent);
//            if (validation(mLoginBinding.userName.getText().toString(), mLoginBinding.password.getText().toString())) {
//                mPresenter.userLogin(mLoginBinding.userName.getText().toString(), mLoginBinding.password.getText().toString());
//            }

        } else if (id == R.id.register_btn) {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(registerIntent);
        }

         */

    }


    @Override
    public void navigateToMain() {
        hideProgressBar();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToRegister() {
    }

    @Override
    public void showProgressBar() {
//        mLoginBinding.loadingProgressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
//        mLoginBinding.loadingProgressbar.setVisibility(View.INVISIBLE);

    }


}
