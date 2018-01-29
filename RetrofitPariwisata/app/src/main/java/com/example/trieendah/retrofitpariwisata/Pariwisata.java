package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trie Endah on 11/22/2017.
 */

public class Pariwisata {
    @SerializedName("id")
    private String id;
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("nama")
    private String nama;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("tiket")
    private String tiket;

    public Pariwisata(String id, String id_kategori, String nama, String lokasi, String deskripsi,String tiket)
    {
        this.id=id;
        this.id_kategori=id_kategori;
        this.nama=nama;
        this.lokasi=lokasi;
        this.deskripsi=deskripsi;
        this.tiket=tiket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }


}
