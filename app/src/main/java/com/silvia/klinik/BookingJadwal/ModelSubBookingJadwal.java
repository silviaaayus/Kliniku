package com.silvia.klinik.BookingJadwal;

public class ModelSubBookingJadwal {

    private String tanggal,tanggal_sistem;

    private String
            id_jadwal, jam_awal, jam_akhir, maksimal,
            waktu_layanan;

    private int
            sudah_terdaftar;

    public ModelSubBookingJadwal(String tanggal, String id_jadwal, String jam_awal, String jam_akhir, String maksimal, String waktu_layanan, int sudah_terdaftar,String tanggal_sistem) {
        this.tanggal = tanggal;
        this.id_jadwal = id_jadwal;
        this.jam_awal = jam_awal;
        this.jam_akhir = jam_akhir;
        this.maksimal = maksimal;
        this.waktu_layanan = waktu_layanan;
        this.sudah_terdaftar = sudah_terdaftar;
        this.tanggal_sistem = tanggal_sistem;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public String getJam_awal() {
        return jam_awal;
    }

    public String getJam_akhir() {
        return jam_akhir;
    }

    public String getMaksimal() {
        return maksimal;
    }

    public String getWaktu_layanan() {
        return waktu_layanan;
    }

    public int getSudah_terdaftar() {
        return sudah_terdaftar;
    }

    public String getTanggal_sistem() {
        return tanggal_sistem;
    }
}
