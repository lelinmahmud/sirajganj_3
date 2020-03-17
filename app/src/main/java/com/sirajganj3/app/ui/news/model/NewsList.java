package com.sirajganj3.app.ui.news.model;

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
