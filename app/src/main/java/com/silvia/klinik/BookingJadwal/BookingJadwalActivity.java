package com.silvia.klinik.BookingJadwal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.silvia.klinik.API;
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.TinyDB;
import com.silvia.klinik.databinding.ActivityBookingJadwalBinding;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingJadwalActivity extends AppCompatActivity {

    private ActivityBookingJadwalBinding binding;

    API api;
    TinyDB tinyDB;
    String id_poli;

    private ArrayList<ModelBookingJadwal> modelBooking = new ArrayList<>();
    private AdapterBookingJadwal mAdapterBooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingJadwalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        api = new API();
        tinyDB = new TinyDB(this);

        id_poli = tinyDB.getString("keyIdPoli");
        Log.e("idpoli", id_poli);

        AndroidNetworking.initialize(this);



        binding.toolbar.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.toolbar.tvToolbar.setText("Booking Jadwal ");


        if (!tinyDB.getBoolean("keyLogin")){
            Intent intent = new Intent(BookingJadwalActivity.this, LoginActivity.class);
            Toast.makeText(this, "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        mAdapterBooking = new AdapterBookingJadwal(modelBooking);
        binding.recyclerAntrian.setAdapter(mAdapterBooking);
        binding.recyclerAntrian.setHasFixedSize(true);

        getBooking();
    }
    public void getBooking(){
        Log.e("url",api.URL_BOOKING);
        AndroidNetworking.get(api.URL_BOOKING)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("Sukses")) {
                                JSONArray res = response.getJSONArray("res");
                                for (int i = 0; i < res.length(); i++){
                                    JSONObject obj = res.getJSONObject(i);
                                    String tanggal = obj.getString("tanggal");
                                    String tanggal_sistem = obj.getString("tanggal_sistem");

                                    ArrayList<ModelSubBookingJadwal> modelSub = new ArrayList<>();
                                    JSONArray jadwal = obj.getJSONArray("jadwal");
                                    for (int j = 0; j < jadwal.length(); j++){
                                        JSONObject has = jadwal.getJSONObject(j);
                                        String id_jadwal = has.getString("id_jadwal");
                                        String jam_awal = has.getString("jam_awal");
                                        String jam_akhir = has.getString("jam_akhir");
                                        String maksimal = has.getString("maksimal");
                                        String waktu_layanan = has.getString("waktu_layanan");
                                        int sudah_terdaftar = has.getInt("sudah_terdaftar");

                                        modelSub.add(new ModelSubBookingJadwal(
                                                tanggal, id_jadwal, jam_awal, jam_akhir,
                                                maksimal, waktu_layanan, sudah_terdaftar,tanggal_sistem
                                        ));

                                    }

                                    modelBooking.add(new ModelBookingJadwal(
                                            tanggal, tanggal_sistem, modelSub
                                    ));

                                }


                                mAdapterBooking.notifyDataSetChanged();
                            }else {

                                Toast.makeText(BookingJadwalActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tampil menu","response:"+anError);
                    }
                });

    }
}