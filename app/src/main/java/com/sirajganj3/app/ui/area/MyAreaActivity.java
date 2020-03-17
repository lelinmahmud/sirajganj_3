package com.sirajganj3.app.ui.area;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityMyAreaBinding;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.areaDetails.AreaDetailsActivity;
import com.sirajganj3.app.ui.main.MainActivity;

import java.util.List;

public class MyAreaActivity extends AppCompatActivity implements AreaView,RecyclerViewItemClickListener{

    ActivityMyAreaBinding areaActivityBinding;
    private Repository repository=new Repository(this);
    private AreaPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_area);
        areaActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_area);
        mPresenter=new AreaPresenter(this,repository);
        mPresenter.loadArea();
        areaActivityBinding.myAreaRv.setLayoutManager(new LinearLayoutManager(this));
        areaActivityBinding.myAreaRv.setHasFixedSize(true);

        areaActivityBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void loadAresInfo(List<AreaInfo> areaInfos) {
        areaActivityBinding.myAreaRv.setAdapter(new MyAreaAdapter(this,areaInfos,this));
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        areaActivityBinding.newsPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        areaActivityBinding.newsPb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void didPressed(int position) {
        Intent detailsIntent = new Intent(MyAreaActivity.this, AreaDetailsActivity.class);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        detailsIntent.putExtra("news_id", position);
        startActivity(detailsIntent);
    }
}
