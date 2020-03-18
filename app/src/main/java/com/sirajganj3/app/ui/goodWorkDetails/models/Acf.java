
package com.sirajganj3.app.ui.goodWorkDetails.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acf {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("thana")
    @Expose
    private String thana;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
