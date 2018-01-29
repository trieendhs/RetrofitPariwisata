package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trie Endah on 12/4/2017.
 */

public class PostPutDelKategori {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kategori mKategori;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kategori getmKategori() {
        return mKategori;
    }

    public void setmKategori(Kategori mKategori) {
        this.mKategori = mKategori;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
