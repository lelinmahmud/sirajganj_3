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
import android.widget.EditText;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityJobsBinding;
import com.sirajganj3.app.ui.job.models.JobInfo;
import com.sirajganj3.app.ui.main.MainActivity;

import java.util.List;

public class JobsActivity extends AppCompatActivity implements JobView {
    ActivityJobsBinding jobsBinding;
    private Repository repository=new Repository(this);
    private JobPresenter jobPresenter;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        jobsBinding = DataBindingUtil.setContentView(this, R.layout.activity_jobs);
        jobPresenter=new JobPresenter(this,repository);
        jobPresenter.loadJobs();
        jobsBinding.jobsRv.setLayoutManager(new LinearLayoutManager(this));
        jobsBinding.jobsRv.setHasFixedSize(true);


        jobsBinding.addBtn.setOnClickListener(v -> {
            builder = new AlertDialog.Builder(JobsActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.job_form_view, viewGroup, false);
            builder.setView(dialogView);
            alertDialog = builder.create();
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
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v ->
                submitJob(dialogView)

        );
    }

    private void submitJob(View view) {

        EditText et_post=view.findViewById(R.id.job_title);
        EditText et_company=view.findViewById(R.id.company_name);
        EditText et_sallary=view.findViewById(R.id.price);
        EditText et_owner=view.findViewById(R.id.product_owner_name);
        EditText et_phone=view.findViewById(R.id.phone_num);

        String postName=et_post.getText().toString();
        String sallary=et_sallary.getText().toString();
        String company=et_company.getText().toString();
        String owner=et_owner.getText().toString();
        String phone=et_phone.getText().toString();
        if (isvalid(postName,company,sallary,owner,phone)){
            alertDialog.dismiss();
            showProgressBar();
            jobPresenter.postJob(postName,company,sallary,owner,phone);
        }
        else {
            showToast("Fill up the all Fields");
        }
    }

    @Override
    public void loadJobInfo(List<JobInfo> jobInfo) {
        jobsBinding.jobsRv.setAdapter(new JobAdapter(this,jobInfo));
        hideProgressBar();

    }

    @Override
    public void showProgressBar() {
        jobsBinding.newsPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        jobsBinding.newsPb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();

    }

    private boolean isvalid(String postname,String company,String salary,String owner,String phone){
        if (postname.isEmpty()||company.isEmpty()||salary.isEmpty()||owner.isEmpty()||phone.isEmpty()){
            return false;
        }
        return true;
    }
}
