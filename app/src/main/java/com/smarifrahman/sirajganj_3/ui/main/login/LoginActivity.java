package com.smarifrahman.sirajganj_3.ui.main.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.databinding.ActivityLoginBinding;
import com.smarifrahman.sirajganj_3.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding mLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoginBinding.loginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.login_btn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
