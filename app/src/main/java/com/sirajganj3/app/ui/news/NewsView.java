package com.sirajganj3.app.ui.news;

import com.sirajganj3.app.ui.news.model.News;

import java.util.List;

public interface NewsView {

    void loadNews(List<News> allNews);
    void showProgressBar();
    void hideProgressBar();
}
