package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trie Endah on 1/27/2018.
 */

public class User {

    @SerializedName("id_user")
    private String idUser;
    @SerializedName("nama")
    private String nama;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("status_user")
    private String status_user;
    @SerializedName("photo_id")
    private String photoId;
    private String action;

    public User(String idUser, String nama, String username, String password, String status_user, String photoId, String action) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.status_user = status_user;
        this.photoId = photoId;
        this.action = action;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
