package com.silvia.klinik.Artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silvia.klinik.API;
import com.silvia.klinik.R;
import com.squareup.picasso.Picasso;

public class DetailArtikelActivity extends AppCompatActivity {

    ImageView back;
    ImageView img_detail_artikel;
    TextView judul_artikel_detail,penulis_artikel_detail,tanggal_artikel_detail,isi_artikel_detail;

    API api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        api = new API();
        back = findViewById(R.id.back_artikel_detail);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        judul_artikel_detail = findViewById(R.id.judulArtikel_detail);
        penulis_artikel_detail = findViewById(R.id.penulis_detail);
        tanggal_artikel_detail = findViewById(R.id.tanggal_detail);
        isi_artikel_detail = findViewById(R.id.isi_artikel_detail);
        img_detail_artikel = findViewById(R.id.img_artikel_detail);
        getDetailArtikel();
    }

    public void getDetailArtikel(){
        Intent i = getIntent();

        String judul_artikel = i.getStringExtra("judul_artikel");
        String penulis_artikel = i.getStringExtra("penulis");
        String tanggal = i.getStringExtra("tanggal_artikel");
        String isi = i.getStringExtra("isi_artikel");

        judul_artikel_detail.setText(judul_artikel);
        penulis_artikel_detail.setText(penulis_artikel);
        tanggal_artikel_detail.setText(tanggal);
        isi_artikel_detail.setText(isi);

        Picasso.get().load(api.URL_GAMBAR_ARTIKEL + i.getStringExtra("img_artikel")).into(img_detail_artikel);

    }

}