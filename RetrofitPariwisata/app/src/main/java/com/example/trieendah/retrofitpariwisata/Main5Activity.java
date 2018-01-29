package com.example.trieendah.retrofitpariwisata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trieendah.retrofitpariwisata.Rest.ApiClient;
import com.example.trieendah.retrofitpariwisata.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main5Activity extends AppCompatActivity {

    EditText edtIdKategori, edtJenis;
    TextView tvMessage;
    Button btInsert, btUpdate, btDelete, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        edtIdKategori = (EditText) findViewById(R.id.edtidkategori2);
        edtJenis = (EditText) findViewById(R.id.edtjenis2);

        btInsert = (Button) findViewById(R.id.btInsert4);
        btUpdate = (Button) findViewById(R.id.btUpdate4);
        btDelete = (Button) findViewById(R.id.btDelete4);
        btBack = (Button) findViewById(R.id.btBack4);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        Intent mIntent = getIntent();
        edtIdKategori.setText(mIntent.getStringExtra("id"));
        edtJenis.setText(mIntent.getStringExtra("jenis"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btInsert.setOnClickListener(    new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelKategori> postKategoriCall =
                        mApiInterface.postKategori(edtIdKategori.getText().toString(),
                                edtJenis.getText().toString());
                    postKategoriCall.enqueue(new Callback<PostPutDelKategori>() {
                    @Override
                    public void onResponse(Call<PostPutDelKategori> call,
                                           Response<PostPutDelKategori> response) {
                        tvMessage.setText(" Retrofit Insert: " + "\n " + " Status Insert : " +response.body().getStatus() + "\n " + " Message Insert : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDelKategori> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelKategori> updateKategoriCall =
                        mApiInterface.putKategori(edtIdKategori.getText().toString(),
                                edtJenis.getText().toString());
                updateKategoriCall.enqueue(new Callback<PostPutDelKategori>() {
                    @Override
                        public void onResponse(Call<PostPutDelKategori> call,
                                           Response<PostPutDelKategori> response) {
                        tvMessage.setText(" Retrofit Update: " + "\n " + " Status Update : " +response.body().getStatus() + "\n " + " Message Update : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDelKategori> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtIdKategori.getText().toString().trim().isEmpty()==false) {
                    Call<PostPutDelKategori> deleteKategori = mApiInterface.deleteKategori(edtIdKategori.getText().toString());
                    deleteKategori.enqueue(new Callback<PostPutDelKategori>() {
                        @Override
                        public void onResponse(Call<PostPutDelKategori> call,
                                               Response<PostPutDelKategori> response) {
                            tvMessage.setText(" Retrofit Delete: " + "\n " + " Status Delete : " +response.body().getStatus() + "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelKategori> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else
                {
                    tvMessage.setText("id_pembelian harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(mIntent);
            }
        });
    }
}
