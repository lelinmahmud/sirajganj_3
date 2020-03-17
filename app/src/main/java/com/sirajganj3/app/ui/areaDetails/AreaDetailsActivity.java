package com.sirajganj3.app.ui.areaDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.sirajganj3.app.R;



public class AreaDetailsActivity extends AppCompatActivity implements AreaDetailsView{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);

    }

    @Override
    public void loadNewsDetails(AreaDetailsActivity areaDetailsActivity) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
