package com.smarifrahman.sirajganj_3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.api.Repository;
import com.smarifrahman.sirajganj_3.databinding.ActivityLoginBinding;
import com.smarifrahman.sirajganj_3.ui.main.MainActivity;
import com.smarifrahman.sirajganj_3.utils.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView {

    ActivityLoginBinding mLoginBinding;
    private LoginPresenter mPresenter;
    private Repository repository=new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).loggedIn()){
            navigateToMain();
            return;
        }
        mPresenter= new LoginPresenter(repository,this,this);
        mLoginBinding.loginBtn.setOnClickListener(this);


    }

    private boolean validation(String username,String password){
        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this,R.string.empty_field,Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.login_btn) {
            if (validation(mLoginBinding.userName.getText().toString(),mLoginBinding.password.getText().toString())){
                mPresenter.userLogin(mLoginBinding.userName.getText().toString(),mLoginBinding.password.getText().toString());

            }
        }
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
        mLoginBinding.loadingProgressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        mLoginBinding.loadingProgressbar.setVisibility(View.INVISIBLE);

    }
}
