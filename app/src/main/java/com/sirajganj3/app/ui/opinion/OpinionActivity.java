package com.sirajganj3.app.ui.opinion;

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
import com.sirajganj3.app.databinding.ActivityOpinionBinding;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.main.MainActivity;

import java.util.List;

public class OpinionActivity extends AppCompatActivity implements OpinionView{

    ActivityOpinionBinding opinionBinding;
    OpinionPresenter mPresenter;
    AlertDialog alertDialog;
    Repository repository=new Repository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        opinionBinding = DataBindingUtil.setContentView(this, R.layout.activity_opinion);
        mPresenter=new OpinionPresenter(this,repository);
        mPresenter.opinionArea();
        opinionBinding.opinionRv.setLayoutManager(new LinearLayoutManager(this));
        opinionBinding.opinionRv.setHasFixedSize(true);

        opinionBinding.addOpinion.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(OpinionActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.opinion_form_view, viewGroup, false);
            builder.setView(dialogView);
             alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });

        opinionBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitOpinion(dialogView));
    }

    private void submitOpinion(View view) {
        EditText et_title = view.findViewById(R.id.opinion_title);
        EditText et_details = view.findViewById(R.id.opinion_descriptions);
        EditText et_name=view.findViewById(R.id.opinion_name);
        String title=et_title.getText().toString();
        String details=et_details.getText().toString();
        String name=et_name.getText().toString();

        if (isValid(title,details,name)){

            mPresenter.opinionPost(title,details,name);
            alertDialog.dismiss();
        }
        else {
            showToast("Fill up the all Fields");

        }
        //TODO
    }

    private boolean isValid(String title, String details,String name) {
        if (title.isEmpty() || details.isEmpty()||name.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void opinonsInfo(List<Opinion> opinions) {
        opinionBinding.opinionRv.setAdapter(new OpinionAdapter(this,opinions));
        hideProgressBar();

    }

    @Override
    public void showProgressBar() {
        opinionBinding.opinionPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        opinionBinding.opinionPb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
