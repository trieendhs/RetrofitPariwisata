package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trie Endah on 12/4/2017.
 */

public class Kategori {
    @SerializedName("id")
    private String id_kategori;
    @SerializedName("jenis")
    private String jenis;

    public Kategori(String id_kategori, String jenis) {
        this.id_kategori = id_kategori;
        this.jenis = jenis;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
