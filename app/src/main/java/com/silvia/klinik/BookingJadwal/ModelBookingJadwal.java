package com.silvia.klinik.BookingJadwal;

import java.util.ArrayList;

public class ModelBookingJadwal {

    private String
            tanggal,tanggal_sistem;
    private ArrayList<ModelSubBookingJadwal> modelSubBookingJadwal;

    public ModelBookingJadwal(String tanggal, String tanggal_sistem, ArrayList<ModelSubBookingJadwal> modelSubBookingJadwal) {
        this.tanggal = tanggal;
        this.tanggal_sistem = tanggal_sistem;
        this.modelSubBookingJadwal = modelSubBookingJadwal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTanggal_sistem() {
        return tanggal_sistem;
    }

    public ArrayList<ModelSubBookingJadwal> getModelSubBookingJadwal() {
        return modelSubBookingJadwal;
    }
}
