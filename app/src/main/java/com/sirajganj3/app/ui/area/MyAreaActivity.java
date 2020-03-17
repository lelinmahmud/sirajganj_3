package com.sirajganj3.app.ui.area;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import com.sirajganj3.app.R;
import com.sirajganj3.app.databinding.ActivityMyAreaBinding;
import com.sirajganj3.app.ui.main.MainActivity;

public class MyAreaActivity extends AppCompatActivity {

    ActivityMyAreaBinding areaActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_area);
        areaActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_area);

        areaActivityBinding.myAreaRv.setLayoutManager(new LinearLayoutManager(this));
        areaActivityBinding.myAreaRv.setHasFixedSize(true);
        areaActivityBinding.myAreaRv.setAdapter(new MyAreaAdapter(this));

        areaActivityBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
