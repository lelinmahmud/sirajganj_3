
package com.sirajganj3.app.ui.job.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobPostResponse {

    @SerializedName("into")
    @Expose
    private String into;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getInto() {
        return into;
    }

    public void setInto(String into) {
        this.into = into;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
