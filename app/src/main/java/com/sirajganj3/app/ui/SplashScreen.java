package com.sirajganj3.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.sirajganj3.app.ui.login.LoginActivity;
import com.sirajganj3.app.ui.login.VerifyPhoneActivity;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.utils.MySharedPrefarance;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySharedPrefarance mySharedPrefarance = MySharedPrefarance.getPrefarences(SplashScreen.this);
        if (mySharedPrefarance.getLoginSession().equals("https://sirajganj3.com.bd/")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }


        /*
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Log.d(TAG, "onStart: " + FirebaseAuth.getInstance().getCurrentUser());
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

         */

    }

}
