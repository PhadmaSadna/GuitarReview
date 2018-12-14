package com.someone.someone.myuasnew.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.someone.someone.myuasnew.R;
import com.someone.someone.myuasnew.Model.*;
import com.someone.someone.myuasnew.Rest.ApiClient;
import com.someone.someone.myuasnew.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuitarAdapter extends RecyclerView.Adapter<GuitarAdapter.GuitarViewHolder> {

    List<Guitar> listGuitar;
    Context mContext;
    ApiInterface mApiInterface;


    public GuitarAdapter(List<Guitar> listGuitar, Context mContext) {
        this.listGuitar= listGuitar;
        this.mContext = mContext;
    }

    @Override
    public GuitarAdapter.GuitarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_guitar, parent,
                false);
        GuitarViewHolder mHolder = new GuitarViewHolder(view, mContext);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(GuitarAdapter.GuitarViewHolder holder, final int position) {

        holder.tvReviewTittle.setText(listGuitar.get(position).getReviewTittle());
        holder.tvMerkType.setText(listGuitar.get(position).getMerkType());
        holder.tvJnsGitar.setText(listGuitar.get(position).getJnsGitar());
        holder.tvReviewDetail.setText(listGuitar.get(position).getReviewDetail());
        holder.tvHarga.setText(listGuitar.get(position).getHarga());

    }

    @Override
    public int getItemCount() {
        return listGuitar.size();
    }

    public class GuitarViewHolder extends RecyclerView.ViewHolder {
        TextView tvReviewTittle, tvMerkType, tvJnsGitar, tvReviewDetail, tvHarga;
        RecyclerView recyclerView;

        public GuitarViewHolder(View itemView, final Context mContext) {
            super(itemView);

            tvReviewTittle= (TextView) itemView.findViewById(R.id.tvReviewTittle);
            tvMerkType= (TextView) itemView.findViewById(R.id.tvMerkType);
            tvJnsGitar= (TextView) itemView.findViewById(R.id.tvJnsGitar);
            tvReviewDetail= (TextView) itemView.findViewById(R.id.tvReviewDetail);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.list_data_guitar);

            mApiInterface = ApiClient.getClient().create(ApiInterface.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Menggunakan Dialog untuk pembelian Tiket
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                    alertDialog.setTitle("Lihat Gitar?");
                    alertDialog.setMessage("Anda akan memasukkan Gitar"
                            + listGuitar.get(getAdapterPosition()).getMerkType()
                            + " Detail "
                            + listGuitar.get(getAdapterPosition()).getReviewDetail());

                    alertDialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    alertDialog.setPositiveButton("YA, BELI SEKARANG", new DialogInterface
                            .OnClickListener
                            () {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Dapatkan tanggal saat ini dengan format 1945-08-17
//                            Date tgl = Calendar.getInstance().getTime();
//                            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
//                            String hariIni = df.format(tgl);

                            // TODO: dapatkan idPembeli dari Login session
                            // TODO: dapatkan harga Tiket (ubah tabel Tiket dg menambah kolom harga)
                            // Untuk sementara, asumsinya adalah:
                            // idPembeli yang login = 11
                            // totalHarga = 450000

                            // Dapatkan id_pembeli yang login melalui shared pref
                            SharedPreferences pref = mContext.getSharedPreferences
                                    ("TokTikLoginData", Context.MODE_PRIVATE);
                            String idUser = pref.getString("user_id", "");

                            Call<PostPutDelReview> postReviewCall = mApiInterface.postReview(
                                    idUser,
                                    "45",
                                    listGuitar.get(getAdapterPosition()).getIdReview());

                            postReviewCall.enqueue(new Callback<PostPutDelReview>() {
                                @Override
                                public void onResponse(Call<PostPutDelReview> call,
                                                       Response<PostPutDelReview> response) {
                                    // Hapus item dari recyclerView dan update list
                                    listGuitar.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    notifyItemRangeChanged(getAdapterPosition(), getItemCount());

                                    // Beri info pembelian berhasil
                                    Toast.makeText(mContext, "Review "
                                            + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<PostPutDelReview> call, Throwable t) {
                                    Toast.makeText(mContext, "Review "
                                            + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                    // Tampilkan Dialog
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();

                }
            });
        }
    }
}
