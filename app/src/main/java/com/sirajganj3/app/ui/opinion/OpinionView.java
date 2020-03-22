package com.sirajganj3.app.ui.opinion;

import com.sirajganj3.app.ui.area.models.AreaInfo;

import java.util.List;

public interface OpinionView {
    void opinonsInfo(List<Opinion> opinonInfos);
    void showProgressBar();
    void hideProgressBar();
    void showToast(String msg);


}
