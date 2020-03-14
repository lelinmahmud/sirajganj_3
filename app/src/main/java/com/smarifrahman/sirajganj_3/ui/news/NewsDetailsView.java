package com.smarifrahman.sirajganj_3.ui.news;

import com.smarifrahman.sirajganj_3.ui.news.model.NewsDetails;

import java.util.List;

public interface NewsDetailsView {

    void loadNewsDetails(NewsDetails newsDetails);

    void showProgressBar();

    void hideProgressBar();
}
