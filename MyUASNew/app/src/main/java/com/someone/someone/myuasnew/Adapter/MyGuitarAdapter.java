package com.someone.someone.myuasnew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.someone.someone.myuasnew.Model.MyGuitar;
import com.someone.someone.myuasnew.R;
import com.someone.someone.myuasnew.Rest.ApiClient;
import com.someone.someone.myuasnew.Rest.ApiInterface;

import java.util.List;

public class MyGuitarAdapter extends RecyclerView.Adapter<MyGuitarAdapter.MyGuitarViewHolder> {

    List<MyGuitar> listGuitar;
    Context mContext;
    ApiInterface mApiInterface;

    public MyGuitarAdapter(List<MyGuitar> listGuitar, Context mContext) {
        this.listGuitar= listGuitar;
        this.mContext = mContext;
    }

    @Override
    public MyGuitarAdapter.MyGuitarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myguitar, parent, false);
        MyGuitarViewHolder mHolder = new MyGuitarViewHolder(view, mContext);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyGuitarAdapter.MyGuitarViewHolder holder, final int position) {

        holder.tvReviewTittle.setText(listGuitar.get(position).getReviewTittle());
        holder.tvMerkType.setText(listGuitar.get(position).getMerkType());
        holder.tvJnsGitar.setText(listGuitar.get(position).getJnsGitar());
        holder.tvReviewDetail.setText("Dilihat " + listGuitar.get(position).getReviewDetail());
        holder.tvHarga.setText(listGuitar.get(position).getHarga());

    }

    @Override
    public int getItemCount() {
        return listGuitar.size();
    }

    public class MyGuitarViewHolder extends RecyclerView.ViewHolder {
        TextView tvReviewTittle, tvMerkType, tvJnsGitar, tvReviewDetail, tvHarga;
        RecyclerView recyclerView;

        public MyGuitarViewHolder(View itemView, final Context mContext) {
            super(itemView);

            tvReviewTittle = (TextView) itemView.findViewById(R.id.tvReviewTittle);
            tvMerkType = (TextView) itemView.findViewById(R.id.tvMerkType);
            tvJnsGitar = (TextView) itemView.findViewById(R.id.tvJnsGitar);
            tvReviewDetail = (TextView) itemView.findViewById(R.id.tvReviewDetail);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.list_data_myguitar);

            mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        }
    }
}
