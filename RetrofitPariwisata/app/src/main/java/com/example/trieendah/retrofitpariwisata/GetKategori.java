package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Trie Endah on 12/4/2017.
 */

public class GetKategori {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kategori> listDataKategori;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kategori> getListDataKategori() {
        return listDataKategori;
    }

    public void setListDataKategori(List<Kategori> listDataKategori) {
        this.listDataKategori = listDataKategori;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
