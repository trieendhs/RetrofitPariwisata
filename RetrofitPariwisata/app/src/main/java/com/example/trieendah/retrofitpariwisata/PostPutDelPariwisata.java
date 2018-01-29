package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trie Endah on 11/22/2017.
 */

public class PostPutDelPariwisata {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pariwisata mPariwisata;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pariwisata getmPariwisata() {
        return mPariwisata;
    }

    public void setmPariwisata(Pariwisata mPariwisata) {
        this.mPariwisata = mPariwisata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
