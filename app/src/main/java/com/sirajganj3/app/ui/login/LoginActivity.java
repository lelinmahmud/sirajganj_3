package com.sirajganj3.app.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityLoginBinding;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.utils.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    ActivityLoginBinding mLoginBinding;
    private LoginPresenter mPresenter;
    private Repository repository = new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

//        if (SharedPrefManager.getInstance(this).loggedIn()) {
//            navigateToMain();
//            return;
//        }

        mPresenter = new LoginPresenter(repository, this, this);
        mLoginBinding.loginBtn.setOnClickListener(this);
        mLoginBinding.registerBtn.setOnClickListener(this);

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
