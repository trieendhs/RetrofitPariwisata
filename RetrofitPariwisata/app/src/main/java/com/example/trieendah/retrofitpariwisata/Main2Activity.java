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

public class Main2Activity extends AppCompatActivity {
    EditText edtid, edtidkategori, edtnama, edtlokasi, edtdeskripsi, edttiket;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtid = (EditText) findViewById(R.id.edtid);
        edtidkategori = (EditText) findViewById(R.id.edtidkategori);
        edtnama = (EditText) findViewById(R.id.edtnama);
        edtlokasi = (EditText) findViewById(R.id.edtlokasi);
        edtdeskripsi = (EditText) findViewById(R.id.edtdeskripsi);
        edttiket = (EditText) findViewById(R.id.edttiket);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        Intent mIntent = getIntent();
        edtid.setText(mIntent.getStringExtra("idpariwisata"));
        edtidkategori.setText(mIntent.getStringExtra("idkategori"));
        edtnama.setText(mIntent.getStringExtra("nama"));
        edtlokasi.setText(mIntent.getStringExtra("lokasi"));
        edtdeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        edttiket.setText(mIntent.getStringExtra("tiket"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<PostPutDelPariwisata> updatePariwisataCall =
                        mApiInterface.putPariwisata(edtid.getText().toString(),
                                edtidkategori.getText().toString(),
                                edtnama.getText().toString(),
                                edtlokasi.getText().toString(),
                                edtdeskripsi.getText().toString(),
                                edttiket.getText().toString());
                updatePariwisataCall.enqueue(new Callback<PostPutDelPariwisata>() {
                    @Override
                    public void onResponse(Call<PostPutDelPariwisata> call,
                                           Response<PostPutDelPariwisata> response) {
//                        tvMessage.setText(" Retrofit Update: " + "\n " + " Status Update : " +response.body().getStatus() + "\n " + " Message Update : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }

        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPariwisata> postPariwisataCall =
                        mApiInterface.postPariwisata(edtid.getText().toString(),
                                edtidkategori.getText().toString(),
                                edtnama.getText().toString(),
                                edtlokasi.getText().toString(),
                                edtdeskripsi.getText().toString(),
                                edttiket.getText().toString());
                postPariwisataCall.enqueue(new Callback<PostPutDelPariwisata>() {
                    @Override
                    public void onResponse(Call<PostPutDelPariwisata> call,
                                           Response<PostPutDelPariwisata> response) {
//                        tvMessage.setText(" Retrofit Insert: " + "\n " + " Status Insert : " +response.body().getStatus() + "\n " + " Message Insert : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
                        tvMessage.setText("Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtid.getText().toString().trim().isEmpty()==false) {
                    Call<PostPutDelPariwisata> deletePariwisata = mApiInterface.deletePariwisata(edtid.getText().toString());
                    deletePariwisata.enqueue(new Callback<PostPutDelPariwisata>() {
                        @Override
                        public void onResponse(Call<PostPutDelPariwisata> call,
                                               Response<PostPutDelPariwisata> response) {
//                            tvMessage.setText(" Retrofit Delete: " + "\n " + " Status Delete : " +response.body().getStatus() + "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
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
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(mIntent);
            }
        });
    }
}
