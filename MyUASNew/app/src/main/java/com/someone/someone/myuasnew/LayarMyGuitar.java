package com.someone.someone.myuasnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.someone.someone.myuasnew.Adapter.MyGuitarAdapter;
import com.someone.someone.myuasnew.Model.GetMyGuitar;
import com.someone.someone.myuasnew.Model.MyGuitar;
import com.someone.someone.myuasnew.Rest.ApiClient;
import com.someone.someone.myuasnew.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarMyGuitar extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_my_guitar);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_data_myguitar);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dapatkanData();
    }

    private void dapatkanData() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences pref = getSharedPreferences("ReviewLoginData", MODE_PRIVATE);
        String idUser= pref.getString("user_id", "");

        // Sehingga, kita bisa dapatkan tiket yang tersedia buat user yang sedang login
        // jangan lupa, buat juga fungsi available_post() pada REST Server yang menangkap id Pembeli
        Call<GetMyGuitar> mMyGuitarCall = mApiInterface.getMyGuitar(idUser);
        mMyGuitarCall.enqueue(new Callback<GetMyGuitar>() {
            @Override
            public void onResponse(Call<GetMyGuitar> call, Response<GetMyGuitar> response) {
                Log.d("GetMyGuitar", String.valueOf(response.body().getResult()));

                List<MyGuitar> listGuitar = response.body().getResult();
                // Dapatkan adapter listTiket dengan parameter tambahan Context
                // (karena ada AlertDialog)
                mAdapter = new MyGuitarAdapter(listGuitar, LayarMyGuitar.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetMyGuitar> call, Throwable t) {
                Log.d("GetMyGuitar",t.getMessage());
            }
        });
    }
}
