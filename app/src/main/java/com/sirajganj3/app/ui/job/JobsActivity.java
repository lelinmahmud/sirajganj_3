package com.sirajganj3.app.ui.job;

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
import com.sirajganj3.app.databinding.ActivityJobsBinding;
import com.sirajganj3.app.ui.main.MainActivity;

public class JobsActivity extends AppCompatActivity {
    ActivityJobsBinding jobsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        jobsBinding = DataBindingUtil.setContentView(this, R.layout.activity_jobs);

        jobsBinding.jobsRv.setLayoutManager(new LinearLayoutManager(this));
        jobsBinding.jobsRv.setHasFixedSize(true);
        jobsBinding.jobsRv.setAdapter(new JobAdapter(this));


        jobsBinding.addBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(JobsActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.job_form_view, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });

        jobsBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitJob());
    }

    private void submitJob() {
        //TODO
        Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
    }
}
