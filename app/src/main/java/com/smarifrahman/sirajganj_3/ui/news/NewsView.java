package com.smarifrahman.sirajganj_3.ui.news;

import com.smarifrahman.sirajganj_3.ui.news.model.News;

import java.util.List;

public interface NewsView {

    void loadNews(List<News> allNews);
    void showProgressBar();
    void hideProgressBar();
}
