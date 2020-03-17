package com.sirajganj3.app.ui.goodwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.sirajganj3.app.R;
import com.sirajganj3.app.databinding.ActivityGoodWorkBinding;
import com.sirajganj3.app.ui.area.MyAreaAdapter;
import com.sirajganj3.app.ui.main.MainActivity;

public class GoodWorkActivity extends AppCompatActivity {

    ActivityGoodWorkBinding goodWorkBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_work);
        goodWorkBinding = DataBindingUtil.setContentView(this, R.layout.activity_good_work);

        goodWorkBinding.myAreaRv.setLayoutManager(new LinearLayoutManager(this));
        goodWorkBinding.myAreaRv.setHasFixedSize(true);
        goodWorkBinding.myAreaRv.setAdapter(new GoodWorkAdapter(this));

        goodWorkBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }
}
