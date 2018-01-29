package com.example.trieendah.retrofitpariwisata.Rest;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trieendah.retrofitpariwisata.R;
import com.example.trieendah.retrofitpariwisata.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Trie Endah on 1/27/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> listUser;

    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent,
                false);
        UserViewHolder mHolder = new UserViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.idUser.setText(listUser.get(position).getIdUser());
        holder.userNama.setText(listUser.get(position).getNama());
        holder.statusUser.setText(listUser.get(position).getStatus_user());
        if (listUser.get(position).getPhotoId().length()>0){
//
            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listUser.get(position).getPhotoId());
// .into(holder.mPhotoid);
            Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listUser.get(position
            ).getPhotoId())
                    .into(holder.mPhotoid);
        }else{
//
            Picasso.with(holder.itemView.getContext()).load(R.drawable.wendy).into(holder.mPhotoid);
            Glide.with(holder.itemView.getContext()).load(R.drawable.wendy).into(holder.mPhotoid);
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), PutDelUserActivity.class);
//                intent.putExtra("id_user",listUser.get(position).getIdUser());
//                intent.putExtra("nama",listUser.get(position).getNama());
//                intent.putExtra("status_user",listUser.get(position).getStatus_user());
//                intent.putExtra("username",listUser.get(position).getUsername());
//                intent.putExtra("photo_id",listUser.get(position).getPhotoId());
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView mPhotoid;
        TextView idUser, userNama, statusUser, userName;

        public UserViewHolder(View itemView) {
            super(itemView);

            mPhotoid = (ImageView) itemView.findViewById(R.id.imgUser);
            idUser = (TextView) itemView.findViewById(R.id.idUser);
            userNama = (TextView) itemView.findViewById(R.id.userNama);
            userName = (TextView) itemView.findViewById(R.id.userName);
            statusUser = (TextView) itemView.findViewById(R.id.userStatusContent);

        }
    }
}
