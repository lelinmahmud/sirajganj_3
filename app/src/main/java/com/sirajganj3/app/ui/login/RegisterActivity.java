package com.sirajganj3.app.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sirajganj3.app.R;
import com.sirajganj3.app.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerBinding.backToLogin.setOnClickListener(this);
        registerBinding.register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.back_to_login) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
        } else if (id == R.id.register) {

            String mobile = registerBinding.phoneNumber.getText().toString().trim();

            if (mobile.isEmpty() || mobile.length() < 11) {
                registerBinding.phoneNumber.setError("Enter a valid mobile");
                registerBinding.phoneNumber.requestFocus();
                return;
            }

            Intent intent = new Intent(RegisterActivity.this, VerifyPhoneActivity.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        }
    }

}
