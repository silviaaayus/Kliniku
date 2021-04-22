package com.silvia.klinik.Riwayat;



public class ModelRiwayat {

        private String id_rekam_medis;
        private String id_pasien;
        private String nama_pasien;
        private String nik;
        private String ttl;
        private String jekel;
        private String alamat_pasien;
        private String nohp_pasien;
        private String email_pasien;
        private String nama_dokter;
        private String tanggal_pemeriksaan;
        private String keluhan;
        private String gigi;
        private String det_rm;
        private String total;

    public ModelRiwayat(String id_rekam_medis, String id_pasien, String nama_pasien, String nik, String ttl, String jekel, String alamat_pasien, String nohp_pasien, String email_pasien, String nama_dokter, String tanggal_pemeriksaan, String keluhan, String gigi, String det_rm, String total) {
        this.id_rekam_medis = id_rekam_medis;
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.nik = nik;
        this.ttl = ttl;
        this.jekel = jekel;
        this.alamat_pasien = alamat_pasien;
        this.nohp_pasien = nohp_pasien;
        this.email_pasien = email_pasien;
        this.nama_dokter = nama_dokter;
        this.tanggal_pemeriksaan = tanggal_pemeriksaan;
        this.keluhan = keluhan;
        this.gigi = gigi;
        this.det_rm = det_rm;
        this.total = total;
    }

    public String getId_rekam_medis() {
        return id_rekam_medis;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public String getNik() {
        return nik;
    }

    public String getTtl() {
        return ttl;
    }

    public String getJekel() {
        return jekel;
    }

    public String getAlamat_pasien() {
        return alamat_pasien;
    }

    public String getNohp_pasien() {
        return nohp_pasien;
    }

    public String getEmail_pasien() {
        return email_pasien;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public String getTanggal_pemeriksaan() {
        return tanggal_pemeriksaan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public String getGigi() {
        return gigi;
    }

    public String getDet_rm() {
        return det_rm;
    }

    public String getTotal() {
        return total;
    }
}
