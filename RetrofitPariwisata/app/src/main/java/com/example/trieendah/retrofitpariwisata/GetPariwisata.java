package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Trie Endah on 11/22/2017.
 */

public class GetPariwisata {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Pariwisata> listDataPariwisata;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pariwisata> getListDataPariwisata() {
        return listDataPariwisata;
    }

    public void setListDataPariwisata(List<Pariwisata> listDataPariwisata) {
        this.listDataPariwisata = listDataPariwisata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
