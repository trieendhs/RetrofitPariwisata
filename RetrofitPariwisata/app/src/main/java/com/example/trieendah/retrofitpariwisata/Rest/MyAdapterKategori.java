package com.example.trieendah.retrofitpariwisata.Rest;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trieendah.retrofitpariwisata.Kategori;
import com.example.trieendah.retrofitpariwisata.Main2Activity;
import com.example.trieendah.retrofitpariwisata.Main5Activity;
import com.example.trieendah.retrofitpariwisata.Pariwisata;
import com.example.trieendah.retrofitpariwisata.R;

import java.util.List;

/**
 * Created by Trie Endah on 12/4/2017.
 */

public class  MyAdapterKategori extends RecyclerView.Adapter<MyAdapterKategori.MyViewHolder> {

    List<Kategori> mKategoriList;

    public MyAdapterKategori(List<Kategori> mKategoriList) {
        this.mKategoriList = mKategoriList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewidkategori.setText("ID Kategori : "+mKategoriList.get(position).getId_kategori());
        holder.mTextViewjenis.setText("Jenis : "+mKategoriList.get(position).getJenis());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), Main5Activity.class);
                mIntent.putExtra("id",mKategoriList.get(position).getId_kategori());
                mIntent.putExtra("jenis",mKategoriList.get(position).getJenis());
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKategoriList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewidkategori, mTextViewjenis;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewidkategori=(TextView) itemView.findViewById(R.id.idkategori3);
            mTextViewjenis=(TextView) itemView.findViewById(R.id.jenis);
        }
    }
}
