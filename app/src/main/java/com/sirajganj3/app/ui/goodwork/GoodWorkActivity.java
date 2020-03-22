package com.sirajganj3.app.ui.goodwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GoodWorkActivity extends AppCompatActivity implements GoodWorkView,RecyclerViewItemClickListener{
    ActivityGoodWorkBinding goodWorkBinding;
    private static final String TAG = "GoodWorkActivity";
    private static final int CAMERA_PERMISSION_CODE = 104;
    private static final int GALLERY_PERMISSION_CODE = 105;
    private static final int GALLERY_REQUEST_CODE = 103;
    private static final int PICK_FROM_CAMERA = 105;
    MultipartBody.Part body;
    String picturePathGallery;
    CircleImageView productImage;
    AlertDialog alertDialog;


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
             alertDialog = builder.create();
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
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitGoodWork(dialogView));
        productImage = dialogView.findViewById(R.id.good_work_image);
        productImage.setOnClickListener(v -> popUpOptionDialog());
    }

    private void submitGoodWork(View view) {

        EditText et_name = view.findViewById(R.id.name);
        EditText et_village = view.findViewById(R.id.village);
        EditText et_thana = view.findViewById(R.id.thana);
        EditText et_details = view.findViewById(R.id.good_work_descriptions);

        String name = et_name.getText().toString();
        String village = et_village.getText().toString();
        String thana = et_thana.getText().toString();
        String details = et_details.getText().toString();

        if (isValid(name, village, thana, details, picturePathGallery)) {
            alertDialog.dismiss();
            showProgressBar();

            //Create a file object using file path
            File file = new File(picturePathGallery);
            RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("product-img", file.getName(), requestFile);


            //Create request body with text description and text media type
            RequestBody pName = RequestBody.create(name, MediaType.parse("text/plain"));
            RequestBody quantity = RequestBody.create(village, MediaType.parse("text/plain"));
            RequestBody price = RequestBody.create(thana, MediaType.parse("text/plain"));
            RequestBody owner = RequestBody.create(details, MediaType.parse("text/plain"));

            mPresenter.postGoodWork(pName, quantity, price, owner, body);

        } else {
            showToast("Fill up the all Fields");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
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



    private void popUpOptionDialog() {
        final String[] option = {"গ্যালারী থেকে ছবি বাছাই করুন", "ক্যামেরা দ্বারা ছবি তুলুন"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(option, -1, (dialog, which) -> {

            if (which == 0) {
                checkPermission("Storage");
                dialog.dismiss();
            }

            if (which == 1) {
                Log.d(TAG, "onClick: Camera");
                checkPermission("Camera");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void checkPermission(String storage) {

        if (storage.equals("Storage")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permission, GALLERY_PERMISSION_CODE);

                } else {
                    //Permission already granted
                    openGallery();
                }
            } else {
                //System OS < Marshmallow
                openGallery();
            }
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                        || checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                    String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
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

    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            Toast.makeText(this, "Camera is Calling", Toast.LENGTH_SHORT).show();
        }

    }

    private void openGallery() {
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
                Uri selectedImageUri = data.getData();
                picturePathGallery = getPath(getApplicationContext(), selectedImageUri);
                productImage.setImageURI(selectedImageUri);
                Log.d(TAG, "Picture Path" + picturePathGallery);
            }

        }

    }

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
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
                break;
            }
            case GALLERY_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission from pop is granted
                    openGallery();
                } else {
                    //Permission from pop is denied
                    Toast.makeText(this, "Permission from pop is denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }



    private boolean isValid(String productTitle, String productQuantity, String productPrice, String productOwner, String picturePathGallery) {
        if (productTitle.isEmpty() || productQuantity.isEmpty() || productPrice.isEmpty() || productOwner.isEmpty()  || picturePathGallery == null) {
            return false;
        }
        return true;
    }
}
