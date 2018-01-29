package com.example.trieendah.retrofitpariwisata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trieendah.retrofitpariwisata.Rest.ApiClient;
import com.example.trieendah.retrofitpariwisata.Rest.ApiInterface;
import com.example.trieendah.retrofitpariwisata.Rest.MyAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    Button btGet,btKategori,btUser,btKeluar;
    TextView tampil;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maina2);

        btGet = (Button) findViewById(R.id.btGet);
        btKategori = (Button) findViewById(R.id.btKategori);
        btUser=(Button) findViewById(R.id.btUser);
        btKeluar=(Button) findViewById(R.id.btLogout);
        tampil=(TextView) findViewById(R.id.user_log1);
//        btUpdate = (Button) findViewById(R.id.btUpdate);
//        btInsert = (Button) findViewById(R.id.btInsert);
//        btDelete = (Button) findViewById(R.id.btDelete);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<GetPariwisata> pariwisataCall = mApiInterface.getPariwisata();
                pariwisataCall.enqueue(new Callback<GetPariwisata>() {
                    @Override
                    public void onResponse(Call<GetPariwisata> call, Response<GetPariwisata>
                            response) {
                        List<Pariwisata> pariwisataList = response.body().getListDataPariwisata();
                        Log.d("Retrofit Get", "Jumlah data pariwisata: " +
                                String.valueOf(pariwisataList.size()));

                        mAdapter = new MyAdapter(pariwisataList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<GetPariwisata> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        tampil.setText(sharedPref.getString("username",""));

        btKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear().commit();
                Intent keMainMenu = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(keMainMenu);
            }
        });

        btKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent);
            }
        });

        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GetUserActivity.class);
                startActivity(intent);
            }
        });

//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Call<PostPutDelPariwisata> updatePembelianCall =
//                        mApiInterface.putPembelian("P3","L1","Kepulauan Raja Ampat","Papua Barat","Fantastic","120000");
//                updatePembelianCall.enqueue(new Callback<PostPutDelPariwisata>() {
//                    @Override
//                    public void onResponse(Call<PostPutDelPariwisata> call,
//                                           Response<PostPutDelPariwisata> response) {
//                        Log.d("Retrofit Update", "Status Update: " +
//                                String.valueOf(response.body().getStatus()));
//                    }
//                    @Override
//                    public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
//                        Log.d("Retrofit Update", t.getMessage());
//                    }
//                });
//            }
//
//        });
//
//        btInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<PostPutDelPariwisata> postPembelianCall =
//                        mApiInterface.postPembelian("P3","L1","Kepulauan Wakatobi","Sulawesi Tengah","Fantastic","120000");
//                postPembelianCall.enqueue(new Callback<PostPutDelPariwisata>() {
//                    @Override
//                    public void onResponse(Call<PostPutDelPariwisata> call,
//                                           Response<PostPutDelPariwisata> response) {
//                        Log.d("Retrofit Insert", "Status Insert: " +
//                                String.valueOf(response.body().getStatus()));
//                    }
//                    @Override
//                    public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
//                        Log.d("Retrofit Insert", t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<PostPutDelPariwisata> deletePembelian =
//                        mApiInterface.deletePariwisata("P3");
//                deletePembelian.enqueue(new Callback<PostPutDelPariwisata>() {
//                    @Override
//                    public void onResponse(Call<PostPutDelPariwisata> call,
//                                           Response<PostPutDelPariwisata> response) {
//                        Log.i("Retrofit Delete", "Status Delete: " +
//                                String.valueOf(response.body().getStatus()));
//                    }
//                    @Override
//                    public void onFailure(Call<PostPutDelPariwisata> call, Throwable t) {
//                        Log.i("Retrofit Delete", t.getMessage());
//                    }
//                });
//            }
//        });
    }
}
