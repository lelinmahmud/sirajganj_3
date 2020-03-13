package com.smarifrahman.sirajganj_3.ui.main.news;

import java.util.List;

public interface NewsView {

    void loadNews(List<News> allNews);
    void showProgressBar();
    void hideProgressBar();
}
