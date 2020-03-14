package com.smarifrahman.sirajganj_3.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.databinding.ActivityMainBinding;
import com.smarifrahman.sirajganj_3.ui.news.NewsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.newsItem.setOnClickListener(this);
        activityMainBinding.communicationItem.setOnClickListener(this);
        activityMainBinding.finish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.news_item:
                startNewActivity(this, NewsActivity.class);
                break;

            case R.id.communication_item:
                break;

            case R.id.finish:
                MainActivity.this.finishAffinity();
                break;
        }
    }

    private void startNewActivity(Context mContext, Class<?> cls) {
        startActivity(new Intent(mContext, cls));
        finish();
    }
}
