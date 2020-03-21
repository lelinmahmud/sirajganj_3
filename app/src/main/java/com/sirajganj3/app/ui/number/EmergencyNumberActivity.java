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
import com.sirajganj3.app.databinding.ActivityEmergencyNumberBinding;
import com.sirajganj3.app.ui.main.MainActivity;

public class EmergencyNumberActivity extends AppCompatActivity {

    ActivityEmergencyNumberBinding emergencyNumberBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_number);
        emergencyNumberBinding = DataBindingUtil.setContentView(this, R.layout.activity_emergency_number);

        registerForContextMenu(emergencyNumberBinding.tThana1);
        registerForContextMenu(emergencyNumberBinding.tThana2);

        findViewById(R.id.home).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(),0, "Copy");
        //menu.setHeaderTitle("Copy text"); //setting header title for menu
        TextView textView = (TextView) v; //calling our textView
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", textView.getText());
        assert manager != null;
        manager.setPrimaryClip(clipData);
    }
}
