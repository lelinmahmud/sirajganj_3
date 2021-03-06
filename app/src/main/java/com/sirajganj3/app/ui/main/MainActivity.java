package com.sirajganj3.app.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.sirajganj3.app.R;
import com.sirajganj3.app.databinding.ActivityMainBinding;
import com.sirajganj3.app.ui.area.MyAreaActivity;
import com.sirajganj3.app.ui.bazar.BazarActivity;
import com.sirajganj3.app.ui.communication.CommunicationActivity;
import com.sirajganj3.app.ui.goodwork.GoodWorkActivity;
import com.sirajganj3.app.ui.job.JobsActivity;
import com.sirajganj3.app.ui.login.LoginActivity;
import com.sirajganj3.app.ui.news.NewsActivity;
import com.sirajganj3.app.ui.number.EmergencyNumberActivity;
import com.sirajganj3.app.ui.opinion.OpinionActivity;
import com.sirajganj3.app.utils.MySharedPrefarance;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.newsItem.setOnClickListener(this);
        activityMainBinding.emergencyNumber.setOnClickListener(this);
        activityMainBinding.bazarItem.setOnClickListener(this);
        activityMainBinding.jobsItem.setOnClickListener(this);
        activityMainBinding.areaItem.setOnClickListener(this);
        activityMainBinding.opinionItem.setOnClickListener(this);
        activityMainBinding.goodWorkItem.setOnClickListener(this);
        activityMainBinding.communicationItem.setOnClickListener(this);
        activityMainBinding.finish.setOnClickListener(this);

       // activityMainBinding.menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.news_item:
                startNewActivity(this, NewsActivity.class);
                break;

            case R.id.emergency_number:
                startNewActivity(this, EmergencyNumberActivity.class);
                break;

            case R.id.communication_item:
                startNewActivity(this, CommunicationActivity.class);
                break;

            case R.id.bazar_item:
                startNewActivity(this, BazarActivity.class);
                break;

            case R.id.jobs_item:
                startNewActivity(this, JobsActivity.class);
                break;

            case R.id.area_item:
                startNewActivity(this, MyAreaActivity.class);
                break;

            case R.id.opinion_item:
                startNewActivity(this, OpinionActivity.class);
                break;

            case R.id.good_work_item:
                startNewActivity(this, GoodWorkActivity.class);
                break;

            case R.id.finish:
                MainActivity.this.finishAffinity();
                break;

            case R.id.menu:
                startNewActivity(this, LoginActivity.class);
                //Set Login session in SharedPreference
                MySharedPrefarance mySharedPrefarance = MySharedPrefarance.getPrefarences(MainActivity.this);
                mySharedPrefarance.setLoginSession("");
                break;
        }
    }

    private void startNewActivity(Context mContext, Class<?> cls) {
        startActivity(new Intent(mContext, cls));
        finish();
    }
}
