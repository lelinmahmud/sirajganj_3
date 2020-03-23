package com.sirajganj3.app.ui.number;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityEmergencyNumberBinding;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.ui.number.model.EmergencyNumberInfo;

import java.util.List;

public class EmergencyNumberActivity extends AppCompatActivity implements EmergencyNumberView {

    ActivityEmergencyNumberBinding emergencyNumberBinding;
    EmergencyNumberPresenter emergencyNumberPresenter;
    Repository repository = new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_number);
        emergencyNumberBinding = DataBindingUtil.setContentView(this, R.layout.activity_emergency_number);

        emergencyNumberPresenter = new EmergencyNumberPresenter(this, repository);
        emergencyNumberPresenter.getEmergencyNumber();

        registerForContextMenu(emergencyNumberBinding.tThana1);
        registerForContextMenu(emergencyNumberBinding.tThana2);

        registerForContextMenu(emergencyNumberBinding.rTahna1);
        registerForContextMenu(emergencyNumberBinding.rTahna2);

        registerForContextMenu(emergencyNumberBinding.fireNumber1);
        registerForContextMenu(emergencyNumberBinding.fireNumber2);

        registerForContextMenu(emergencyNumberBinding.tUNumber1);
        registerForContextMenu(emergencyNumberBinding.tUNumber2);

        registerForContextMenu(emergencyNumberBinding.rUNumber1);
        registerForContextMenu(emergencyNumberBinding.rUNumber2);

        registerForContextMenu(emergencyNumberBinding.ambulance1);
        registerForContextMenu(emergencyNumberBinding.ambulance2);

        findViewById(R.id.home).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(), 0, "Copy");
        //menu.setHeaderTitle("Copy text"); //setting header title for menu
        TextView textView = (TextView) v; //calling our textView
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", textView.getText());
        assert manager != null;
        manager.setPrimaryClip(clipData);
    }

    @Override
    public void loadEmergencyNumber(List<EmergencyNumberInfo> emergencyNumberResponse) {

        emergencyNumberBinding.tThana1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getTarashThana1());
        emergencyNumberBinding.tThana2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getTarashThana2());

        emergencyNumberBinding.rTahna1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getRaiganjThana1());
        emergencyNumberBinding.rTahna2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getRaiganjThana2());

        emergencyNumberBinding.fireNumber1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getFireService1());
        emergencyNumberBinding.fireNumber2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getFireService2());

        emergencyNumberBinding.tUNumber1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getTarashUpazilaHealthComplex1());
        emergencyNumberBinding.tUNumber2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getTarashUpazilaHealthComplexContact2());

        emergencyNumberBinding.rUNumber1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getRaiganjUpazilaHealthComplexContact1());
        emergencyNumberBinding.rUNumber2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getRaiganjUpazilaHealthComplexContact2());

        emergencyNumberBinding.ambulance1.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getAmbulanceContact1());
        emergencyNumberBinding.ambulance2.setText(emergencyNumberResponse.get(0).getEmergencyNumberResponse().getAmbulanceContact2());
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
