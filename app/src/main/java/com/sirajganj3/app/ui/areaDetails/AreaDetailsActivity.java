package com.sirajganj3.app.ui.areaDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityAreaDetailsBinding;
import com.sirajganj3.app.ui.area.MyAreaActivity;
import com.sirajganj3.app.ui.news.NewsActivity;
import com.sirajganj3.app.ui.news.NewsDetailsActivity;
import com.sirajganj3.app.ui.news.NewsDetailsPresenter;
import com.sirajganj3.app.ui.news.model.NewsDetails;


public class AreaDetailsActivity extends AppCompatActivity implements AreaDetailsView{
    ActivityAreaDetailsBinding binding;
    AreaDetailsPresenter mPresenter;
    Repository repository=new Repository(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_area_details);
        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("news_id", 0);
        mPresenter = new AreaDetailsPresenter(this, repository);
        mPresenter.getNewsDetails(id);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            startActivity(new Intent(AreaDetailsActivity.this, MyAreaActivity.class));
            finish();
        });


    }




    @Override
    public void loadNewsDetails(NewsDetails newsDetails) {
        binding.newsDetailsTitle.setText(newsDetails.getTitle());
        binding.newsDetails.setText(newsDetails.getContent());
        Glide.with(this).load(newsDetails.getFeaturedImage()).into(binding.newsDetailsImg);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
            binding.detailsPb.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        binding.detailsPb.setVisibility(View.INVISIBLE);

    }
}
