
package com.sirajganj3.app.ui.goodWorkDetails.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodWorkDetails {

    @SerializedName("acf")
    @Expose
    private Acf acf;

    public Acf getAcf() {
        return acf;
    }

    public void setAcf(Acf acf) {
        this.acf = acf;
    }

}
