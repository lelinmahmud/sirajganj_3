package com.sirajganj3.app.ui.goodWorkDetails;

import com.sirajganj3.app.ui.goodWorkDetails.models.GoodWorkDetails;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;

import java.util.List;

public interface GoodWorkDetailsView {
    void loadGoodWork(GoodWorkDetails goodWorks);
    void showProgressBar();
    void hideProgressBar();

}
