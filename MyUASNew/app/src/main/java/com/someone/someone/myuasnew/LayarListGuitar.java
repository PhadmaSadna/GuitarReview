package com.someone.someone.myuasnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.someone.someone.myuasnew.Adapter.GuitarAdapter;
import com.someone.someone.myuasnew.Model.GetGuitar;
import com.someone.someone.myuasnew.Model.Guitar;
import com.someone.someone.myuasnew.Rest.ApiClient;
import com.someone.someone.myuasnew.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListGuitar extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_guitar);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_data_guitar);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dapatkanData();

    }

    private void dapatkanData() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences pref = getSharedPreferences("ReviewLoginData", MODE_PRIVATE);
        String idUser = pref.getString("user_id", "");

        Call<GetGuitar> mGuitarCall= mApiInterface.getGuitarForUser(idUser);
        mGuitarCall.enqueue(new Callback<GetGuitar>() {
            @Override
            public void onResponse(Call<GetGuitar> call, Response<GetGuitar> response) {
                Log.d("GetGuitar", String.valueOf(response.body().getResult()));

                List<Guitar> listGuitar= response.body().getResult();
                // Dapatkan adapter listTiket dengan parameter tambahan Context
                // (karena ada AlertDialog)
                mAdapter = new GuitarAdapter(listGuitar, LayarListGuitar.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetGuitar> call, Throwable t) {
                Log.d("Get User",t.getMessage());
            }
        });
    }
}
