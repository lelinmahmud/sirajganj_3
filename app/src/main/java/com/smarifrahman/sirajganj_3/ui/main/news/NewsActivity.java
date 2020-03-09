package com.smarifrahman.sirajganj_3.ui.main.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.databinding.ActivityNewsBinding;
import com.smarifrahman.sirajganj_3.ui.main.MainActivity;

public class NewsActivity extends AppCompatActivity {

    ActivityNewsBinding activityNewsBinding;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        activityNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        newsAdapter = new NewsAdapter(this);
        activityNewsBinding.newsRv.setLayoutManager(new LinearLayoutManager(this));
        activityNewsBinding.newsRv.setHasFixedSize(true);
        activityNewsBinding.newsRv.setAdapter(newsAdapter);

        activityNewsBinding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
