package com.sirajganj3.app.ui.bazar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sirajganj3.app.R;

import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityBazarBinding;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.main.MainActivity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BazarActivity extends AppCompatActivity implements BazarView {

    private static final String TAG = "BazarActivity";
    private static final int GALLERY_REQUEST_CODE = 103;
    private static final int CAMERA_PERMISSION_CODE = 104;
    private static final int PICK_FROM_CAMERA = 105;
    MultipartBody.Part body;


    ActivityBazarBinding bazarBinding;
    private Repository repository = new Repository(this);
    private BazarPresenter mPresenter;
    View dialogView;
    CircleImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazar);
        bazarBinding = DataBindingUtil.setContentView(this, R.layout.activity_bazar);
        mPresenter = new BazarPresenter(this, repository);
        mPresenter.loadBazar();

        bazarBinding.bazarRv.setLayoutManager(new LinearLayoutManager(this));
        bazarBinding.bazarRv.setHasFixedSize(true);

        bazarBinding.home.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });


        bazarBinding.addBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(BazarActivity.this);
            builder.setCancelable(false);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.product_advertisement_form, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitProduct());

        productImage = dialogView.findViewById(R.id.product_img);
        productImage.setOnClickListener(v -> popUpOptionDialog());
    }

    private void popUpOptionDialog() {
        final String[] option = {"Take Picture From Gallery", "Take Picture by Camera "};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Option");
        builder.setSingleChoiceItems(option, -1, (dialog, which) -> {

            if (which == 0) {
                pickFromGallery();
                dialog.dismiss();
            }
            if (which == 1) {
                Log.d(TAG, "onClick: Camera");
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
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            Toast.makeText(this, "Camera is Calling", Toast.LENGTH_SHORT).show();
        }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            productImage.setImageBitmap(imageBitmap);
           // convertToJpg(imageBitmap);

        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {

            Log.d(TAG, "onActivityResult: Pick form gallery");

            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    productImage.setImageBitmap(bitmap);
                  //  convertToJpg(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission from pop is granted
                    openCamera();
                } else {
                    //Permission from pop is denied
                    Toast.makeText(this, "Permission from pop is denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void submitProduct() {
      //  mPresenter.postBazar(body);
        Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadBazarInfo(List<BazarInfo> bazarInfo) {
        bazarBinding.bazarRv.setAdapter(new BazarAdapter(this, bazarInfo));
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        bazarBinding.bazarPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        bazarBinding.bazarPb.setVisibility(View.INVISIBLE);

    }

//    private void convertToJpg(Bitmap bitmap){
//        File file =bitmapToFile(bitmap);
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//// MultipartBody.Part is used to send also the actual file name
//         body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//
//// add another part within the multipart request
//        RequestBody fullName =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file.getName());
//    }
//
//    private File bitmapToFile(Bitmap bitmap1){
//        File f = new File(context.getCacheDir(), filename);
//        f.createNewFile();
//
////Convert bitmap to byte array
//        Bitmap bitmap =bitmap;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//        byte[] bitmapdata = bos.toByteArray();
//
////write the bytes in file
//        FileOutputStream fos = new FileOutputStream(f);
//        fos.write(bitmapdata);
//        fos.flush();
//        fos.close();
//    }


}
