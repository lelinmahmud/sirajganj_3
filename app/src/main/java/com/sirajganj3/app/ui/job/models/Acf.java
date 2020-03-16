
package com.sirajganj3.app.ui.job.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acf {

    @SerializedName("post_name")
    @Expose
    private String postName;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
