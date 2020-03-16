package com.sirajganj3.app.ui.news;

import com.sirajganj3.app.ui.news.model.NewsDetails;

public interface NewsDetailsView {

    void loadNewsDetails(NewsDetails newsDetails);

    void showProgressBar();

    void hideProgressBar();
}
