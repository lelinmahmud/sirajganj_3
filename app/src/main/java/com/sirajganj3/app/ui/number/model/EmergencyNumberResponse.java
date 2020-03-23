package com.sirajganj3.app.ui.number.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmergencyNumberResponse {

    @SerializedName("tarash_thana_1")
    @Expose
    private String tarashThana1;
    @SerializedName("tarash_thana_2")
    @Expose
    private String tarashThana2;
    @SerializedName("raiganj_thana_1")
    @Expose
    private String raiganjThana1;
    @SerializedName("raiganj_thana_2")
    @Expose
    private String raiganjThana2;
    @SerializedName("fire_service_1")
    @Expose
    private String fireService1;
    @SerializedName("fire_service_2")
    @Expose
    private String fireService2;
    @SerializedName("tarash_upazila_health_complex_1")
    @Expose
    private String tarashUpazilaHealthComplex1;
    @SerializedName("tarash_upazila_health_complex_contact_2")
    @Expose
    private String tarashUpazilaHealthComplexContact2;
    @SerializedName("raiganj_upazila_health_complex_contact_1")
    @Expose
    private String raiganjUpazilaHealthComplexContact1;
    @SerializedName("raiganj_upazila_health_complex_contact_2")
    @Expose
    private String raiganjUpazilaHealthComplexContact2;
    @SerializedName("ambulance_contact_1")
    @Expose
    private String ambulanceContact1;
    @SerializedName("ambulance_contact_2")
    @Expose
    private String ambulanceContact2;

    public String getTarashThana1() {
        return tarashThana1;
    }

    public void setTarashThana1(String tarashThana1) {
        this.tarashThana1 = tarashThana1;
    }

    public String getTarashThana2() {
        return tarashThana2;
    }

    public void setTarashThana2(String tarashThana2) {
        this.tarashThana2 = tarashThana2;
    }

    public String getRaiganjThana1() {
        return raiganjThana1;
    }

    public void setRaiganjThana1(String raiganjThana1) {
        this.raiganjThana1 = raiganjThana1;
    }

    public String getRaiganjThana2() {
        return raiganjThana2;
    }

    public void setRaiganjThana2(String raiganjThana2) {
        this.raiganjThana2 = raiganjThana2;
    }

    public String getFireService1() {
        return fireService1;
    }

    public void setFireService1(String fireService1) {
        this.fireService1 = fireService1;
    }

    public String getFireService2() {
        return fireService2;
    }

    public void setFireService2(String fireService2) {
        this.fireService2 = fireService2;
    }

    public String getTarashUpazilaHealthComplex1() {
        return tarashUpazilaHealthComplex1;
    }

    public void setTarashUpazilaHealthComplex1(String tarashUpazilaHealthComplex1) {
        this.tarashUpazilaHealthComplex1 = tarashUpazilaHealthComplex1;
    }

    public String getTarashUpazilaHealthComplexContact2() {
        return tarashUpazilaHealthComplexContact2;
    }

    public void setTarashUpazilaHealthComplexContact2(String tarashUpazilaHealthComplexContact2) {
        this.tarashUpazilaHealthComplexContact2 = tarashUpazilaHealthComplexContact2;
    }

    public String getRaiganjUpazilaHealthComplexContact1() {
        return raiganjUpazilaHealthComplexContact1;
    }

    public void setRaiganjUpazilaHealthComplexContact1(String raiganjUpazilaHealthComplexContact1) {
        this.raiganjUpazilaHealthComplexContact1 = raiganjUpazilaHealthComplexContact1;
    }

    public String getRaiganjUpazilaHealthComplexContact2() {
        return raiganjUpazilaHealthComplexContact2;
    }

    public void setRaiganjUpazilaHealthComplexContact2(String raiganjUpazilaHealthComplexContact2) {
        this.raiganjUpazilaHealthComplexContact2 = raiganjUpazilaHealthComplexContact2;
    }

    public String getAmbulanceContact1() {
        return ambulanceContact1;
    }

    public void setAmbulanceContact1(String ambulanceContact1) {
        this.ambulanceContact1 = ambulanceContact1;
    }

    public String getAmbulanceContact2() {
        return ambulanceContact2;
    }

    public void setAmbulanceContact2(String ambulanceContact2) {
        this.ambulanceContact2 = ambulanceContact2;
    }

}
