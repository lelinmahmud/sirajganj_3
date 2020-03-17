package com.sirajganj3.app.ui.bazar;

import com.sirajganj3.app.ui.bazar.models.BazarInfo;

import java.util.List;

public interface BazarView {
    void loadBazarInfo(List<BazarInfo> bazarInfo);
    void showProgressBar();
    void hideProgressBar();
}
