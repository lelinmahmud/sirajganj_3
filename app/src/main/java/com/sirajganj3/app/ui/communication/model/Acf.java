
package com.sirajganj3.app.ui.communication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acf {

    @SerializedName("transport_type")
    @Expose
    private String transportType;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("driver_image")
    @Expose
    private String driverImage;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

}
