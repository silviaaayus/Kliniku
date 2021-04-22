package com.silvia.klinik.Antrian;



public class ModelAntrian  {

        private String no;
        private String id_pasien;
        private String tanggal;
        private String id_jadwal;
        private String create_date;
        private String create_by;
        private String nama_pasien;
        private String ttl;
        private String jekel;
        private String alamat_pasien;
        private String email_pasien;
        private String nik;
        private String id_status;
        private String status;
        private String jam_awal;
        private String jam_akhir;
        private String nohp_pasien;
        private String id_poli;
        private String nama_poli;
        private String id_antrian;

    public ModelAntrian(String no, String id_pasien, String tanggal, String id_jadwal, String create_date, String create_by, String nama_pasien, String ttl, String jekel, String alamat_pasien, String email_pasien, String nik, String id_status, String status, String jam_awal, String jam_akhir, String nohp_pasien, String id_poli, String nama_poli, String id_antrian) {
        this.no = no;
        this.id_pasien = id_pasien;
        this.tanggal = tanggal;
        this.id_jadwal = id_jadwal;
        this.create_date = create_date;
        this.create_by = create_by;
        this.nama_pasien = nama_pasien;
        this.ttl = ttl;
        this.jekel = jekel;
        this.alamat_pasien = alamat_pasien;
        this.email_pasien = email_pasien;
        this.nik = nik;
        this.id_status = id_status;
        this.status = status;
        this.jam_awal = jam_awal;
        this.jam_akhir = jam_akhir;
        this.nohp_pasien = nohp_pasien;
        this.id_poli = id_poli;
        this.nama_poli = nama_poli;
        this.id_antrian = id_antrian;
    }

    public String getNo() {
        return no;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public String getNama_pasien() {
        return nama_pasien;
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

    public String getEmail_pasien() {
        return email_pasien;
    }

    public String getNik() {
        return nik;
    }

    public String getId_status() {
        return id_status;
    }

    public String getStatus() {
        return status;
    }

    public String getJam_awal() {
        return jam_awal;
    }

    public String getJam_akhir() {
        return jam_akhir;
    }

    public String getNohp_pasien() {
        return nohp_pasien;
    }

    public String getId_poli() {
        return id_poli;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public String getId_antrian() {
        return id_antrian;
    }
}