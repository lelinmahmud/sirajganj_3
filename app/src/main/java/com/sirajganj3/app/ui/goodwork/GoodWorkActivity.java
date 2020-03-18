package com.sirajganj3.app.ui.goodwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityGoodWorkBinding;
import com.sirajganj3.app.ui.area.MyAreaActivity;
import com.sirajganj3.app.ui.area.MyAreaAdapter;
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.areaDetails.AreaDetailsActivity;
import com.sirajganj3.app.ui.goodWorkDetails.GoodworkDetailsActivity;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;
import com.sirajganj3.app.ui.job.JobsActivity;
import com.sirajganj3.app.ui.main.MainActivity;

import java.util.List;

public class GoodWorkActivity extends AppCompatActivity implements GoodWorkView,RecyclerViewItemClickListener{
    ActivityGoodWorkBinding goodWorkBinding;
    Repository repository=new Repository(this);
    GoodWorkPresenter mPresenter;
    private static final int GALLERY_REQUEST_CODE = 103;
    private static final int CAMERA_PERMISSION_CODE = 104;
    private static final int PICK_FROM_CAMERA = 105;
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
        Intent detailsIntent = new Intent(GoodWorkActivity.this, GoodworkDetailsActivity.class);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        detailsIntent.putExtra("news_id", position);
        startActivity(detailsIntent);
    }


    private void pickFromGallery() {

        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);

    }

    private void checkCameraPermission() {

        //openCamera();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, CAMERA_PERMISSION_CODE);

            } else {
                //Permission already granted
                openCamera();
            }
        } else {
            //System OS < Marshmallow
            openCamera();
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            Toast.makeText(this, "Camera is Calling", Toast.LENGTH_SHORT).show();
        }
    }
}
