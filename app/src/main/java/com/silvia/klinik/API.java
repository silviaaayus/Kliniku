package com.silvia.klinik;

public class API {
    private String HOST = "http://kliniku.citragroup-hrd.com/";


    public String URL_LOGIN = HOST +  "api/login";
    public String URL_REGISTER = HOST +  "api/insert_register";
    public String URL_Kategori = HOST + "api/tampil_kategori";
    public String URL_GAMBAR = HOST + "assets/gambar_kategori/";


    public String URL_SARAN = HOST + "api/insert_saran";
    public String URL_USER =  HOST + "api/tampil_data_user?id_user=";
    public String URL_UPDATE_PASIEN = HOST+  "api/update_data_profil";
    public String URL_LAYANAN = HOST + "api/tampil_data_layanan";
    public String URL_GAMBAR_LAYANAN = HOST+ "assets/img_layanan/";

    public String URL_SLIDER = HOST + "assets/slider/";
    public String URL_GAMBAR_ARTIKEL = HOST +"assets/gambar_artikel/";
    public String URL_ARTIKEL = HOST + "api/tampil_data_artikel";
    public String URL_SEMUA_ARTIKEL = HOST + "api/tampil_semua_artikel";

    public String URL_RIWAYAT = HOST + "api/tampil_data_rm?id_pasien=";

    public String URL_BOOKING = HOST + "api/tanggal_berobat";
    public String URL_PASIEN_LAMA = HOST + "api/tampil_detail_pasien?nik=";
    public String URL_DETAIL_JADWAL = HOST + "api/tampil_detail_jadwal_booking?tanggal=";
    public String URL_GET_ANTRIAN = HOST + "api/insert_booking_pasien";
    public String URL_NOANTRIAN = HOST + "api/tampil_list_booking_pasien?id_pasien=";

    public String URL_GAMBAR_POLI = HOST+ "assets/gambar_poli/";
    public String URL_POLI = HOST + "api/tampil_poli";


}
