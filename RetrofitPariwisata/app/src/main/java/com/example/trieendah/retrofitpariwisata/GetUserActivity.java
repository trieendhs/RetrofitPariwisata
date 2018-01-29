package com.example.trieendah.retrofitpariwisata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.trieendah.retrofitpariwisata.Rest.ApiClient;
import com.example.trieendah.retrofitpariwisata.Rest.ApiInterface;
import com.example.trieendah.retrofitpariwisata.Rest.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView5);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = (Button) findViewById(R.id.btGet5);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ResultUser> mUserCall = mApiInterface.getUser();
                mUserCall.enqueue(new Callback<ResultUser>() {
                    @Override
                    public void onResponse(Call<ResultUser> call,
                                           Response<ResultUser> response) {
                        Log.d("Get User",response.body().getStatus());
                        List<User> listUser = response.body().getResult();
                        mAdapter = new UserAdapter(listUser);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<ResultUser> call, Throwable t) {
                        Log.d("Get Pembeli",t.getMessage());
                    }
                });
            }
        });
    }
}
