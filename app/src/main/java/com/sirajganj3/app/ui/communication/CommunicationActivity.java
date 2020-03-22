package com.sirajganj3.app.ui.communication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.sirajganj3.app.R;
import com.sirajganj3.app.api.Repository;
import com.sirajganj3.app.databinding.ActivityCommunicationBinding;
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.main.MainActivity;
import com.sirajganj3.app.ui.opinion.OpinionActivity;
import com.sirajganj3.app.ui.opinion.OpinionAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener,CommunicationView {

    ActivityCommunicationBinding communicationBinding;
    CommunicationPresenter mPresenter;
    Repository repository=new Repository(this);
    private static final String TAG = "GoodWorkActivity";
    private static final int CAMERA_PERMISSION_CODE = 104;
    private static final int GALLERY_PERMISSION_CODE = 105;
    private static final int GALLERY_REQUEST_CODE = 103;
    private static final int PICK_FROM_CAMERA = 105;
    MultipartBody.Part body;
    String picturePath;
    CircleImageView productImage;
    AlertDialog alertDialog;
    int vehicleType;
    private String[] vehiclesName = {"-- যানবাহন বাছাই করুন --","মোটরসাইকেল", "ইজি বাইক", "প্রাইভেট কার/মাইক্রোবাস", "পিক আপ/ট্রাক", "বাস", "নৌযান"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        communicationBinding = DataBindingUtil.setContentView(this, R.layout.activity_communication);
        mPresenter=new CommunicationPresenter(this,repository);
        mPresenter.loadTransport();
        communicationBinding.addVehicles.setOnClickListener(this);
        communicationBinding.home.setOnClickListener(this);

        communicationBinding.boat.setOnClickListener(this);
        communicationBinding.bus.setOnClickListener(this);
        communicationBinding.pickUp.setOnClickListener(this);
        communicationBinding.privateCar.setOnClickListener(this);
        communicationBinding.easyBike.setOnClickListener(this);
        communicationBinding.motorBike.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.add_vehicles:
                AlertDialog.Builder builder = new AlertDialog.Builder(CommunicationActivity.this);
                builder.setCancelable(false);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.vehicles_form_view, viewGroup, false);
                builder.setView(dialogView);
                 alertDialog = builder.create();
                alertDialog.show();
                initXml(dialogView, alertDialog);
                break;

            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

            case R.id.boat:
                if (communicationBinding.boatInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.boatInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.boatInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.boatInfoRv);
                }
                break;

            case R.id.bus:
                if (communicationBinding.busInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.busInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.busInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.busInfoRv);
                }
                break;

            case R.id.pick_up:
                if (communicationBinding.pickUpInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.pickUpInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.pickUpInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.pickUpInfoRv);
                }
                break;

            case R.id.private_car:
                if (communicationBinding.privateCarInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.privateCarInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.privateCarInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.privateCarInfoRv);
                }
                break;

            case R.id.easy_bike:
                if (communicationBinding.easyBikeInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.easyBikeInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.easyBikeInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.easyBikeInfoRv);
                }
                break;

            case R.id.motor_bike:
                if (communicationBinding.motorBikeInfo.getVisibility() == View.VISIBLE) {
                    communicationBinding.motorBikeInfo.setVisibility(View.GONE);
                } else {
                    communicationBinding.motorBikeInfo.setVisibility(View.VISIBLE);
                    setDataOnRecyclerView(communicationBinding.motorBikeInfoRv);
                }
                break;
        }
    }

    private void initXml(View dialogView, AlertDialog alertDialog) {
        productImage=dialogView.findViewById(R.id.product_img);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CommunicationActivity.this, android.R.layout.simple_spinner_item, vehiclesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner vehiclesSpinner = dialogView.findViewById(R.id.vehicles_spinner);
        productImage.setOnClickListener(v -> popUpOptionDialog());
        vehiclesSpinner.setAdapter(adapter);
        vehiclesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("tag",vehiclesName[position]);
                vehicleType=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialogView.findViewById(R.id.close).setOnClickListener(v -> alertDialog.dismiss());
        dialogView.findViewById(R.id.btn_submit).setOnClickListener(v -> submitVehicles(dialogView));
    }

    private void submitVehicles(View view) {
        EditText et_name = view.findViewById(R.id.name);
        EditText et_phone = view.findViewById(R.id.phone_num);


        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();


        if (isValid(name, phone, picturePath,vehicleType)) {
            alertDialog.dismiss();

            //Create a file object using file path
            File file = new File(picturePath);
            RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("driver-img", file.getName(), requestFile);


            //Create request body with text description and text media type
            RequestBody pName = RequestBody.create(name, MediaType.parse("text/plain"));
            RequestBody pphone = RequestBody.create(phone, MediaType.parse("text/plain"));
            RequestBody t_tpype = RequestBody.create(vehiclesName[vehicleType], MediaType.parse("text/plain"));

            mPresenter.postVehicle(t_tpype, pName, pphone, body);

        } else {
            showToast("Fill up the all Fields");
        }
    }

    private void setDataOnRecyclerView(RecyclerView boatInfoRv) {
        boatInfoRv.setLayoutManager(new LinearLayoutManager(this));
        boatInfoRv.setHasFixedSize(true);
        boatInfoRv.setAdapter(new VehiclesAdapter(this));
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



    private boolean isValid(String name, String phone, String picturePathGallery,int type) {
        if (name.isEmpty() || phone.isEmpty() || picturePathGallery == null||type==0) {
            return false;
        }
        return true;
    }

    @Override
    public void loadTransport(List<Vehicle> vehicles) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
