package com.sirajganj3.app.ui.communication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityCommunicationBinding;
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.ui.opinion.OpinionActivity;
import com.sirajganj3.app.ui.opinion.OpinionAdapter;

import java.util.List;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener,CommunicationView {

    ActivityCommunicationBinding communicationBinding;
    CommunicationPresenter mPresenter;
    Repository repository=new Repository(this);
    private String[] vehiclesName = {"-- যানবাহন বাছাই করুন --","মোটরসাইকেল", "ইজি বাইক", "প্রাইভেট কার/মাইক্রোবাস", "পিক আপ/ট্রাক", "বাস", "নৌযান"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        communicationBinding = DataBindingUtil.setContentView(this, R.layout.activity_communication);
        mPresenter=new CommunicationPresenter(this,repository);
        mPresenter.loadTransport();
        communicationBinding.addVehicles.setOnClickListener(this);
        communicationBinding.home.setOnClickListener(this);

        communicationBinding.boat.setOnClickListener(this);
        communicationBinding.bus.setOnClickListener(this);
        communicationBinding.pickUp.setOnClickListener(this);
        communicationBinding.privateCar.setOnClickListener(this);
        communicationBinding.easyBike.setOnClickListener(this);
        communicationBinding.motorBike.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.add_vehicles:
                AlertDialog.Builder builder = new AlertDialog.Builder(CommunicationActivity.this);
                builder.setCancelable(false);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.vehicles_form_view, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                initXml(dialogView, alertDialog);
                break;

            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

            case R.id.boat:
                if (communicationBinding.boatInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.boatInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.boatInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.boatInfoRv);
                }
                break;

            case R.id.bus:
                if (communicationBinding.busInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.busInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.busInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.busInfoRv);
                }
                break;

            case R.id.pick_up:
                if (communicationBinding.pickUpInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.pickUpInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.pickUpInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.pickUpInfoRv);
                }
                break;

            case R.id.private_car:
                if (communicationBinding.privateCarInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.privateCarInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.privateCarInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.privateCarInfoRv);
                }
                break;

            case R.id.easy_bike:
                if (communicationBinding.easyBikeInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.easyBikeInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.easyBikeInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.easyBikeInfoRv);
                }
                break;

            case R.id.motor_bike:
                if (communicationBinding.motorBikeInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.motorBikeInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.motorBikeInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.motorBikeInfoRv);
                }
                break;
        }
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CommunicationActivity.this, android.R.layout.simple_spinner_item, vehiclesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner vehiclesSpinner = dialogView.findViewById(R.id.vehicles_spinner);
        vehiclesSpinner.setAdapter(adapter);
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitVehicles());
    }

    private void submitVehicles() {
    }

    private void setDataOnRecyclerView(RecyclerView boatInfoRv) {
        boatInfoRv.setLayoutManager(new LinearLayoutManager(this));
        boatInfoRv.setHasFixedSize(true);
        boatInfoRv.setAdapter(new VehiclesAdapter(this));
    }

    @Override
    public void loadTransport(List<Vehicle> vehicles) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
