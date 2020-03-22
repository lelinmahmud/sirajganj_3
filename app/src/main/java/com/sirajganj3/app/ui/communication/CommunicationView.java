package com.sirajganj3.app.ui.communication;

import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.communication.model.Vehicle;

import java.util.List;

public interface CommunicationView {
    void loadTransport(List<Vehicle> vehicles);
    void showProgressBar();
    void hideProgressBar();
}
