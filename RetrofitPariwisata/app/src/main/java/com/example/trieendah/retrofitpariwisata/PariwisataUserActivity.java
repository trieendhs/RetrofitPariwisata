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

public class PariwisataUserActivity extends AppCompatActivity {

    Button btGet,btKeluar;
    TextView tampil;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pariwisata_user);

        btGet=(Button) findViewById(R.id.btGet6);
        btKeluar=(Button) findViewById(R.id.btKeluar2);
        tampil=(TextView) findViewById(R.id.user_log2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView6);
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

                        mAdapter = new PariwisataAdapter(pariwisataList);
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

        SharedPreferences sharedPref = getSharedPreferences("userInfo2", Context.MODE_PRIVATE);

        tampil.setText(sharedPref.getString("username",""));

        btKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo2", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear().commit();
                Intent keMainMenu = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(keMainMenu);
            }
        });
    }
}
