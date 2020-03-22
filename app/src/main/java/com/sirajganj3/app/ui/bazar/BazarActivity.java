package com.sirajganj3.app.ui.bazar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
    private static final int CAMERA_PERMISSION_CODE = 104;
    private static final int GALLERY_PERMISSION_CODE = 105;
    private static final int GALLERY_REQUEST_CODE = 103;
    private static final int PICK_FROM_CAMERA = 105;
    MultipartBody.Part body;
    String picturePath;

    ActivityBazarBinding bazarBinding;
    private Repository repository = new Repository(this);
    private BazarPresenter mPresenter;
    View dialogView;
    CircleImageView productImage;
    AlertDialog alertDialog;

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
            alertDialog = builder.create();
            alertDialog.show();
            initXml(dialogView, alertDialog);
        });
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitProduct(dialogView));

        productImage = dialogView.findViewById(R.id.product_img);
        productImage.setOnClickListener(v -> popUpOptionDialog());
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
            assert data != null;
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            productImage.setImageBitmap(imageBitmap);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            assert imageBitmap != null;
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            picturePath = getRealPathFromURI(getApplicationContext(), tempUri);


        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {

            Log.d(TAG, "onActivityResult: Pick form gallery");

            if (data != null) {
                Uri selectedImageUri = data.getData();
                picturePath = getRealPathFromURI(getApplicationContext(), selectedImageUri);
                productImage.setImageURI(selectedImageUri);
                Log.d(TAG, "Picture Path" + picturePath);
            }

        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String getRealPathFromURI(Context context, Uri uri) {
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


    private void submitProduct(View view) {

        EditText et_product_title = view.findViewById(R.id.product_title);
        EditText et_amount = view.findViewById(R.id.amount);
        EditText et_price = view.findViewById(R.id.price);
        EditText et_owner_name = view.findViewById(R.id.product_owner_name);
        EditText et_phone = view.findViewById(R.id.phone_num);

        String productTitle = et_product_title.getText().toString();
        String productQuantity = et_amount.getText().toString();
        String productPrice = et_price.getText().toString();
        String productOwner = et_owner_name.getText().toString();
        String productPhone = et_phone.getText().toString();

        if (isValid(productTitle, productQuantity, productPrice, productOwner, productPhone, picturePath)) {
            alertDialog.dismiss();
            showProgressBar();

            //Create a file object using file path
            File file = new File(picturePath);
            RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("product-img", file.getName(), requestFile);

            //Create request body with text description and text media type
            RequestBody pName = RequestBody.create(productTitle, MediaType.parse("text/plain"));
            RequestBody quantity = RequestBody.create(productQuantity, MediaType.parse("text/plain"));
            RequestBody price = RequestBody.create(productPrice, MediaType.parse("text/plain"));
            RequestBody owner = RequestBody.create(productOwner, MediaType.parse("text/plain"));
            RequestBody phone = RequestBody.create(productPhone, MediaType.parse("text/plain"));

            mPresenter.postBazar(pName, quantity, price, owner, phone, body);

        } else {
            showToast("Fill up the all Fields");
        }
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

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(String productTitle, String productQuantity, String productPrice, String productOwner, String productPhone, String picturePath) {
        if (productTitle.isEmpty() || productQuantity.isEmpty() || productPrice.isEmpty() || productOwner.isEmpty() || productPhone.isEmpty() || picturePath == null) {
            return false;
        }
        return true;
    }


}
