
package com.sirajganj3.app.ui.communication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("acf")
    @Expose
    private Acf acf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acf getAcf() {
        return acf;
    }

    public void setAcf(Acf acf) {
        this.acf = acf;
    }

}
