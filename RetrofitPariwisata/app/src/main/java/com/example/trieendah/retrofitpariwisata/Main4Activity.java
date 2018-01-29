package com.example.trieendah.retrofitpariwisata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.trieendah.retrofitpariwisata.Rest.ApiClient;
import com.example.trieendah.retrofitpariwisata.Rest.ApiInterface;
import com.example.trieendah.retrofitpariwisata.Rest.MyAdapter;
import com.example.trieendah.retrofitpariwisata.Rest.MyAdapterKategori;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main4Activity extends AppCompatActivity {

    Button btGet,btKategori;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btGet = (Button) findViewById(R.id.btGet3);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<GetKategori> KategoriCall = mApiInterface.getKategori();
                KategoriCall.enqueue(new Callback<GetKategori>() {
                    @Override
                    public void onResponse(Call<GetKategori> call, Response<GetKategori>
                            response) {
                        List<Kategori> kategoriList = response.body().getListDataKategori();
                        Log.d("Retrofit Get", "Jumlah data kategori: " +
                                String.valueOf(kategoriList.size()));

                        mAdapter = new MyAdapterKategori(kategoriList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<GetKategori> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }
}
