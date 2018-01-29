package com.example.trieendah.retrofitpariwisata.Rest;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trieendah.retrofitpariwisata.Main2Activity;
import com.example.trieendah.retrofitpariwisata.Pariwisata;
import com.example.trieendah.retrofitpariwisata.R;

import java.util.List;

/**
 * Created by Trie Endah on 11/28/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Pariwisata> mPariwisataList;

    public MyAdapter(List<Pariwisata> pariwisataList) {
        mPariwisataList = pariwisataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewid.setText("ID Pariwisata : "+mPariwisataList.get(position).getId());
        holder.mTextViewidkategori.setText("ID Kategori : "+mPariwisataList.get(position).getId_kategori());
        holder.mTextViewnama.setText("Nama : "+mPariwisataList.get(position).getNama());
        holder.mTextViewlokasi.setText("Lokasi : "+mPariwisataList.get(position).getLokasi());
        holder.mTextViewtiket.setText("Tiket : "+mPariwisataList.get(position).getTiket());
        holder.mTextViewdeksripsi.setText("Deskripsi : "+mPariwisataList.get(position).getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), Main2Activity.class);
                mIntent.putExtra("id_pariwisata",mPariwisataList.get(position).getId());
                mIntent.putExtra("id_kategori",mPariwisataList.get(position).getId_kategori());
                mIntent.putExtra("nama",mPariwisataList.get(position).getNama());
                mIntent.putExtra("lokasi",mPariwisataList.get(position).getLokasi());
                mIntent.putExtra("tiket",mPariwisataList.get(position).getTiket());
                mIntent.putExtra("deskripsi",mPariwisataList.get(position).getDeskripsi());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPariwisataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewid, mTextViewnama,
                mTextViewlokasi,mTextViewtiket,mTextViewidkategori,mTextViewdeksripsi;

        public MyViewHolder(View itemView) {
            super(itemView);

                mTextViewid=(TextView) itemView.findViewById(R.id.tiket);
                mTextViewnama=(TextView) itemView.findViewById(R.id.nama);
                mTextViewlokasi=(TextView) itemView.findViewById(R.id.lokasi);
                mTextViewtiket=(TextView) itemView.findViewById(R.id.tiket);
                mTextViewidkategori=(TextView) itemView.findViewById(R.id.idkategori);
                mTextViewdeksripsi=(TextView) itemView.findViewById(R.id.deskripsi);
        }
    }
}
