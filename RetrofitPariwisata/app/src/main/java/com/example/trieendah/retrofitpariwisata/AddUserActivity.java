package com.example.trieendah.retrofitpariwisata;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trieendah.retrofitpariwisata.Rest.ApiClient;
import com.example.trieendah.retrofitpariwisata.Rest.ApiInterface;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private final String TAG=this.getClass().getName();
    Context mContext;
    ImageView mImageView;
    Button btAddPhotoId, btAddBack, btAddData, btTakePhoto;
    EditText edtAddIdUser, edtAddNamaUser, edtAddUsername,edtAddStatusUser,edtAddPasswordUser;
    //    EditText edtAddTelpnPembeli;
    TextView tvAddMessage;
    String imagePath = "";
    CameraPhoto camera;

    final int CAMERA_REQUEST=13323;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mContext = getApplicationContext();
        mImageView = (ImageView) findViewById(R.id.imgAddPhotoId);
        btAddPhotoId = (Button) findViewById(R.id.btAddPhotoId);
        btTakePhoto=(Button) findViewById(R.id.btTakePhoto);

        edtAddIdUser = (EditText) findViewById(R.id.edtAddIdUser);
        edtAddNamaUser = (EditText) findViewById(R.id.edtAddNamaUser);
        edtAddUsername = (EditText) findViewById(R.id.edtAddUsername);
        edtAddStatusUser = (EditText) findViewById(R.id.edtAddStatusUser);
        edtAddPasswordUser = (EditText) findViewById(R.id.edtAddPasswordUser);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);

        camera=new CameraPhoto(getApplicationContext());

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
//File creating from selected URL
                    File file = new File(imagePath);
// create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
// MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("photo_id", file.getName(),
                            requestFile);
                }
                RequestBody reqIdUser =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddIdUser.getText().toString().isEmpty()==true)?"":edtAddIdUser.getText().toString());
                RequestBody reqNama =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddNamaUser.getText().toString().isEmpty())?"":edtAddNamaUser.getText().toString());
                RequestBody reqUsername =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddNamaUser.getText().toString().isEmpty())?"":edtAddUsername.getText().toString());
                RequestBody reqStatusUser =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddNamaUser.getText().toString().isEmpty())?"":edtAddStatusUser.getText().toString());
                RequestBody reqPassword =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddNamaUser.getText().toString().isEmpty())?"":edtAddPasswordUser.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                "post");
                Call<ResultUser> mUserCall = mApiInterface.postUser(body,reqIdUser, reqNama,
                        reqUsername, reqPassword, reqStatusUser, reqAction );
                mUserCall.enqueue(new Callback<ResultUser>() {
                    @Override
                    public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                      Log.d("Insert Retrofit",response.body().getStatus());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+ "Message = "+response.body().getMessage()+"\n");
                        }else{
                            //String detail = "\n"+ "nama = "+response.body().getResult().get(0).getNama()+"\n"+ "photo_id = "+response.body().getResult().get(0).getPhotoId()+"\n";
                            tvAddMessage.setText(response.body().getStatus());
                        }
                   }
                    @Override
                    public void onFailure(Call<ResultUser> call, Throwable t) {
                     Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage.setText("Retrofit Update \n Status = "+ t.getMessage());
                    }
                });
            }
        });

        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LogActivity.class);
                startActivity(intent);
            }
        });

        btAddPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih Gambar Untuk Di upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(camera.takePhotoIntent(),CAMERA_REQUEST);
                    camera.addToGallery();
                } catch (IOException e) {
//                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Something wrong while taking photo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(mContext, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);
                //Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor.close();
            }else{
                Toast.makeText(mContext, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
            }
        }
        else
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAMERA_REQUEST)
            {
                String photopath=camera.getPhotoPath();
                try {
                    Bitmap bit = ImageLoader.init().from(photopath).requestSize(512,512).getBitmap();
                    mImageView.setImageBitmap(bit);
                    imagePath=photopath;
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"Something wrong while loading photo",Toast.LENGTH_SHORT).show();
                }
//                Log.d(TAG,photopath);
            }
        }
    }
}
