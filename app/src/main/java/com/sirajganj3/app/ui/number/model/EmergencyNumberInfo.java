package com.sirajganj3.app.ui.number.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmergencyNumberInfo {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("acf")
    @Expose
    private EmergencyNumberResponse emergencyNumberResponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmergencyNumberResponse getEmergencyNumberResponse() {
        return emergencyNumberResponse;
    }

    public void setEmergencyNumberResponse(EmergencyNumberResponse emergencyNumberResponse) {
        this.emergencyNumberResponse = emergencyNumberResponse;
    }

}
