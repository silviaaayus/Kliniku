package com.silvia.klinik.Antrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.silvia.klinik.API;
import com.silvia.klinik.KotakSaran.KotakSaranActivity;
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.MainActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;

public class NoAntrianActivity extends AppCompatActivity {

    API api;
    ImageView back;
    TextView title;

    TextView tgl,jadwal,antrian,nama,nohp,poli;
    TextView nik,ttl,jekel,alamat,email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_antrian);

        api = new API();
        AndroidNetworking.initialize(this);


        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
               Intent i = new Intent(NoAntrianActivity.this, AntrianActivity.class);
               startActivity(i);
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("Nomor Antrian");



        tgl = findViewById(R.id.tgl_noantrian);
        jadwal = findViewById(R.id.jamantrian);
        antrian = findViewById(R.id.noantrian);
        nama = findViewById(R.id.namaantrian);
        nohp = findViewById(R.id.nohpantrian);
        poli = findViewById(R.id.poli);
        nik = findViewById(R.id.nikantrian);
        ttl = findViewById(R.id.ttlantrian);
        jekel = findViewById(R.id.jekelantrian);
        alamat = findViewById(R.id.alamatantrian);
        email = findViewById(R.id.emailantrian);
        getNoAntrian();


    }

    public void getNoAntrian(){
        Intent i =getIntent();
        String tgl_ = i.getStringExtra("tanggal");
        String jam_awal = i.getStringExtra("jam_awal");
        String jam_akhir = i.getStringExtra("jam_akhir");
        String noantrian = i.getStringExtra("id_antrian");
        String nama_ = i.getStringExtra("nama_pasien");
        String nohp_ = i.getStringExtra("nohp_pasien");
        String poli_ = i.getStringExtra("nama_poli");
        String nik_ = i.getStringExtra("nik");
        String jekel_ = i.getStringExtra("jekel");
        String alamat_ = i.getStringExtra("alamat_pasien");
        String email_ = i.getStringExtra("email_pasien");
        String ttl_ = i.getStringExtra("ttl");


        tgl.setText(tgl_);
        jadwal.setText("Jam "+jam_awal+" - "+jam_akhir);
        antrian.setText(noantrian);
        nama.setText(nama_);
        nohp.setText(nohp_);
        poli.setText(poli_);
        nik.setText(nik_);
        jekel.setText(jekel_);
        alamat.setText(alamat_);
        email.setText(email_);
        ttl.setText(ttl_);

    }
    public void onBackPressed(){
        Toast.makeText(this, "Tidak bisa kembali, silahkan Klik tombol Back diatas", Toast.LENGTH_SHORT).show();

    }

}