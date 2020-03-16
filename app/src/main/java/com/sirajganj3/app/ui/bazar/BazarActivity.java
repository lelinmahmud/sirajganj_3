package com.sirajganj3.app.ui.bazar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sirajganj3.app.R;

import com.sirajganj3.app.databinding.ActivityBazarBinding;
import com.sirajganj3.app.ui.main.MainActivity;

public class BazarActivity extends AppCompatActivity {

    ActivityBazarBinding bazarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazar);
        bazarBinding = DataBindingUtil.setContentView(this, R.layout.activity_bazar);

        bazarBinding.bazarRv.setLayoutManager(new LinearLayoutManager(this));
        bazarBinding.bazarRv.setHasFixedSize(true);
        bazarBinding.bazarRv.setAdapter(new BazarAdapter(this));

        bazarBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });


        bazarBinding.addBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(BazarActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.product_advertisement_form, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitProduct());
    }

    private void submitProduct() {
        //TODO
        Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
    }
}