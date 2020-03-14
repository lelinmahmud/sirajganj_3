package com.smarifrahman.sirajganj_3.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.databinding.ActivityNewsBinding;
import com.smarifrahman.sirajganj_3.api.Repository;
import com.smarifrahman.sirajganj_3.ui.main.MainActivity;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements NewsView {

    ActivityNewsBinding activityNewsBinding;
    NewsAdapter newsAdapter;
    NewsPresenter mPresenter;
    Repository repository=new Repository(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        activityNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        mPresenter=new NewsPresenter(this,repository);
        mPresenter.getNews();


        activityNewsBinding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void loadNews(List<News> allNews) {
        newsAdapter = new NewsAdapter(this,allNews);
        activityNewsBinding.newsRv.setLayoutManager(new LinearLayoutManager(this));
        activityNewsBinding.newsRv.setHasFixedSize(true);
        activityNewsBinding.newsRv.setAdapter(newsAdapter);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        activityNewsBinding.loadingProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        activityNewsBinding.loadingProgressbar.setVisibility(View.INVISIBLE);

    }
}
