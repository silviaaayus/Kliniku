package com.silvia.klinik.Riwayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silvia.klinik.API;
import com.silvia.klinik.R;

public class DetailRiwayatActivity extends AppCompatActivity {

    ImageView back;
    TextView title;
    API api;

    TextView txt_namaLengkap, txt_nik,txt_ttl,txt_jekel,txt_nohp,txt_email,txt_alamat;
    TextView txt_tgl_periksa, txt_keluhan,txt_gigi,txt_dokter,txt_perawatan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat);

        api = new API();
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("Detail Riwayat");

        txt_namaLengkap = findViewById(R.id.namaLengkap);
        txt_nik = findViewById(R.id.nik);
        txt_ttl = findViewById(R.id.tanggalLahir);
        txt_jekel = findViewById(R.id.jekel);
        txt_nohp = findViewById(R.id.nohp);
        txt_email = findViewById(R.id.email);
        txt_alamat = findViewById(R.id.alamat);

        txt_tgl_periksa = findViewById(R.id.tanggal);
        txt_keluhan = findViewById(R.id.keluhan);
        txt_gigi = findViewById(R.id.gigi);
        txt_dokter = findViewById(R.id.namaDokter);
        txt_perawatan = findViewById(R.id.perawatan);

        getDetailRiwayat();

    }
    public void getDetailRiwayat(){
        Intent i = getIntent();


        String namaLengkap = i.getStringExtra("nama_pasien");
        String nik = i.getStringExtra("nik");
        String ttl = i.getStringExtra("ttl");
        String jekel = i.getStringExtra("jekel");
        String alamat = i.getStringExtra("alamat_pasien");
        String nohp = i.getStringExtra("nohp_pasien");
        String email = i.getStringExtra("email_pasien");

        String tgl = i.getStringExtra("tanggal_pemeriksaan");
        String keluhan = i.getStringExtra("keluhan");
        String gigi = i.getStringExtra("gigi");
        String dokter = i.getStringExtra("nama_dokter");
        String perawatan = i.getStringExtra("det_rm");


        txt_namaLengkap.setText(namaLengkap);
        txt_nik.setText(nik);
        txt_ttl.setText(ttl);
        txt_jekel.setText(jekel);
        txt_alamat.setText(alamat);
        txt_nohp.setText(nohp);
        txt_email.setText(email);

        txt_tgl_periksa.setText(tgl);
        txt_keluhan.setText(keluhan);
        txt_gigi.setText(gigi);
        txt_dokter.setText(dokter);
        txt_perawatan.setText(perawatan);

    }

}