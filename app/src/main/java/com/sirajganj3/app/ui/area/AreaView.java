package com.sirajganj3.app.ui.area;

import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;

import java.util.List;

public interface AreaView {
    void loadAresInfo(List<AreaInfo> areaInfos);
    void showProgressBar();
    void hideProgressBar();

}
