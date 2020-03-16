package com.smarifrahman.sirajganj_3.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.api.Repository;
import com.smarifrahman.sirajganj_3.databinding.ActivityNewsDetailsBinding;
import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailsView {

    ActivityNewsDetailsBinding newsDetailsBinding;
    NewsDetailsPresenter mPresenter;
    Repository repository = new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        newsDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);

        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("news_id", 0);
        mPresenter = new NewsDetailsPresenter(this, repository);
        mPresenter.getNewsDetails(id);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            startActivity(new Intent(NewsDetailsActivity.this, NewsActivity.class));
            finish();
        });
    }


    @Override
    public void loadNewsDetails(NewsDetails newsDetails) {
        newsDetailsBinding.newsDetailsTitle.setText(newsDetails.getTitle());
        newsDetailsBinding.newsDetails.setText(newsDetails.getContent());
        Glide.with(this).load(newsDetails.getFeaturedImage()).into(newsDetailsBinding.newsDetailsImg);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        newsDetailsBinding.detailsPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        newsDetailsBinding.detailsPb.setVisibility(View.GONE);
    }
}
