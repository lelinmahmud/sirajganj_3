package com.smarifrahman.sirajganj_3.ui.news.model;

import com.smarifrahman.sirajganj_3.ui.news.model.News;

import java.util.List;

public class NewsList {
    private List<News> newsList;

    public NewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
