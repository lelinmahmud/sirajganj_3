package com.smarifrahman.sirajganj_3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.databinding.ActivityRegisterBinding;
import com.smarifrahman.sirajganj_3.ui.news.NewsDetailsActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerBinding.backToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
}
