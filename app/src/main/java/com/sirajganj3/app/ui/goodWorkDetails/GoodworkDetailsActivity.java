package com.sirajganj3.app.ui.goodWorkDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityGoodworkDetailsBinding;
import com.sirajganj3.app.ui.area.MyAreaActivity;
import com.sirajganj3.app.ui.areaDetails.AreaDetailsActivity;
import com.sirajganj3.app.ui.areaDetails.AreaDetailsPresenter;
import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;
import com.sirajganj3.app.ui.goodwork.GoodWorkActivity;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;

import java.util.List;

public class GoodworkDetailsActivity extends AppCompatActivity implements GoodWorkDetailsView{

    ActivityGoodworkDetailsBinding binding;
    GoodWorkDetailsPresenter mPresenter;
    Repository repository=new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodwork_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goodwork_details);
        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("news_id", 0);
        mPresenter =new GoodWorkDetailsPresenter(this,repository);
        mPresenter.loadGoodDetailsWork(id);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            startActivity(new Intent(GoodworkDetailsActivity.this, GoodWorkActivity.class));
            finish();
        });
    }


    @Override
    public void loadGoodWork(GoodWorkDetails goodWorks) {
        binding.newsDetailsTitle.setText(goodWorks.getAcf().getName());
        binding.newsDetails.setText(goodWorks.getAcf().getDescription());
        Glide.with(this).load(goodWorks.getAcf().getPhoto()).into(binding.newsDetailsImg);
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
