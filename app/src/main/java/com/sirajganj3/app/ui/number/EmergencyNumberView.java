package com.sirajganj3.app.ui.number;

import com.sirajganj3.app.ui.number.model.EmergencyNumberInfo;

import java.util.List;

public interface EmergencyNumberView {

    void loadEmergencyNumber(List<EmergencyNumberInfo> emergencyNumberResponse);

    void showProgressBar();

    void hideProgressBar();
}
