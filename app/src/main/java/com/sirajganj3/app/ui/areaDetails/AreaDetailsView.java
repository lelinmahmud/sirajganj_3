package com.sirajganj3.app.ui.areaDetails;

import com.sirajganj3.app.ui.news.model.NewsDetails;

public interface AreaDetailsView {

    void loadNewsDetails(NewsDetails newsDetails);

    void showProgressBar();

    void hideProgressBar();
}
