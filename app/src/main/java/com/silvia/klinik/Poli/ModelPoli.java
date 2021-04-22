package com.silvia.klinik.Poli;

public class ModelPoli {

    private String id_poli;
    private String nama_poli;
    private String gambar;
    private String jam_awal;
    private String jam_akhir;

    public ModelPoli(String id_poli, String nama_poli, String gambar, String jam_awal, String jam_akhir) {
        this.id_poli = id_poli;
        this.nama_poli = nama_poli;
        this.gambar = gambar;
        this.jam_awal = jam_awal;
        this.jam_akhir = jam_akhir;
    }

    public String getId_poli() {
        return id_poli;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public String getGambar() {
        return gambar;
    }

    public String getJam_awal() {
        return jam_awal;
    }

    public String getJam_akhir() {
        return jam_akhir;
    }
}
