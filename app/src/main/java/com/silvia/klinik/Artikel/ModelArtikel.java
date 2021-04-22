package com.silvia.klinik.Artikel;


public class ModelArtikel {
        private String id_artikel;
        private String judul_artikel;
        private String penulis;
        private String isi_artikel;
        private String tanggal_artikel;
        private String img_artikel;

    public ModelArtikel(String id_artikel, String judul_artikel, String penulis, String isi_artikel, String tanggal_artikel, String img_artikel) {
        this.id_artikel = id_artikel;
        this.judul_artikel = judul_artikel;
        this.penulis = penulis;
        this.isi_artikel = isi_artikel;
        this.tanggal_artikel = tanggal_artikel;
        this.img_artikel = img_artikel;
    }

    public String getId_artikel() {
        return id_artikel;
    }

    public void setId_artikel(String id_artikel) {
        this.id_artikel = id_artikel;
    }

    public String getJudul_artikel() {
        return judul_artikel;
    }

    public void setJudul_artikel(String judul_artikel) {
        this.judul_artikel = judul_artikel;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getIsi_artikel() {
        return isi_artikel;
    }

    public void setIsi_artikel(String isi_artikel) {
        this.isi_artikel = isi_artikel;
    }

    public String getTanggal_artikel() {
        return tanggal_artikel;
    }

    public void setTanggal_artikel(String tanggal_artikel) {
        this.tanggal_artikel = tanggal_artikel;
    }

    public String getImg_artikel() {
        return img_artikel;
    }

    public void setImg_artikel(String img_artikel) {
        this.img_artikel = img_artikel;
    }
}
