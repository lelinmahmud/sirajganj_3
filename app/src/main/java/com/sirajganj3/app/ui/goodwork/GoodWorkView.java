package com.sirajganj3.app.ui.goodwork;

import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;

import java.util.List;

public interface GoodWorkView {
    void loadGoodWork(List<GoodWork> goodWorks);
    void showProgressBar();
    void hideProgressBar();
    void showToast(String msg);


}
