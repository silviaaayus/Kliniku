package com.silvia.klinik.Home;

public class ModelKategori {

        private String id_kategori;
        private String kategori;
        private String gambar;

    public ModelKategori(String id_kategori, String kategori, String gambar) {
        this.id_kategori = id_kategori;
        this.kategori = kategori;
        this.gambar = gambar;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public String getGambar() {
        return gambar;
    }
}
