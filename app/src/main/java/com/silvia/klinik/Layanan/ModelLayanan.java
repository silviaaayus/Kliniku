package com.silvia.klinik.Layanan;


public class ModelLayanan {


        private String id_layanan;
        private String layanan;
        private String deskripsi;
        private String tarif_layanan;
        private String img_layanan;

    public ModelLayanan(String id_layanan, String layanan, String deskripsi, String tarif_layanan, String img_layanan) {
        this.id_layanan = id_layanan;
        this.layanan = layanan;
        this.deskripsi = deskripsi;
        this.tarif_layanan = tarif_layanan;
        this.img_layanan = img_layanan;
    }

    public String getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(String id_layanan) {
        this.id_layanan = id_layanan;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTarif_layanan() {
        return tarif_layanan;
    }

    public void setTarif_layanan(String tarif_layanan) {
        this.tarif_layanan = tarif_layanan;
    }

    public String getImg_layanan() {
        return img_layanan;
    }

    public void setImg_layanan(String img_layanan) {
        this.img_layanan = img_layanan;
    }
}
