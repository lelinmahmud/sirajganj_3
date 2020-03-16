package com.sirajganj3.app.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sirajganj3.app.R;
import com.sirajganj3.app.databinding.ActivityNewsBinding;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.ui.news.model.News;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements NewsView, RecyclerViewItemClickListener {
    private static final String TAG = "NewsActivity";

    ActivityNewsBinding activityNewsBinding;
    NewsAdapter newsAdapter;
    NewsPresenter mPresenter;
    Repository repository = new Repository(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        activityNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        mPresenter = new NewsPresenter(this, repository);
        mPresenter.getNews();


        activityNewsBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(NewsActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void loadNews(List<News> allNews) {
        newsAdapter = new NewsAdapter(this, this, allNews);
        activityNewsBinding.newsRv.setLayoutManager(new LinearLayoutManager(this));
        activityNewsBinding.newsRv.setHasFixedSize(true);
        activityNewsBinding.newsRv.setAdapter(newsAdapter);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        activityNewsBinding.newsPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        activityNewsBinding.newsPb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void didPressed(int position) {
        Intent detailsIntent = new Intent(NewsActivity.this, NewsDetailsActivity.class);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        detailsIntent.putExtra("news_id", position);
        startActivity(detailsIntent);
    }
}
