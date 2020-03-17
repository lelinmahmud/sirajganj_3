package com.sirajganj3.app.ui.goodwork;

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
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityGoodWorkBinding;
import com.sirajganj3.app.ui.area.MyAreaAdapter;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.job.JobsActivity;
import com.sirajganj3.app.ui.main.MainActivity;

import java.util.List;

public class GoodWorkActivity extends AppCompatActivity implements GoodWorkView,RecyclerViewItemClickListener{

    ActivityGoodWorkBinding goodWorkBinding;
    Repository repository=new Repository(this);
    GoodWorkPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_work);
        goodWorkBinding = DataBindingUtil.setContentView(this, R.layout.activity_good_work);
        mPresenter=new GoodWorkPresenter(this,repository);
        mPresenter.loadGoodWork();
        goodWorkBinding.myAreaRv.setLayoutManager(new LinearLayoutManager(this));
        goodWorkBinding.myAreaRv.setHasFixedSize(true);


        goodWorkBinding.addGoodWork.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GoodWorkActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.good_work_form, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });

        goodWorkBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitGoodWork());
    }

    private void submitGoodWork() {
        //TODO
        Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadGoodWork(List<GoodWork> goodWorks) {
        goodWorkBinding.myAreaRv.setAdapter(new GoodWorkAdapter(this,goodWorks,this));
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        goodWorkBinding.newsPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        goodWorkBinding.newsPb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void didPressed(int position) {

    }
}
