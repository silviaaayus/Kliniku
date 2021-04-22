package com.silvia.klinik.Layanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silvia.klinik.API;
import com.silvia.klinik.R;
import com.squareup.picasso.Picasso;

public class DetailLayananActivity extends AppCompatActivity {

    ImageView back;
    TextView title;

    ImageView img_detail_layanan;
    TextView txt_detail_layanan,txt_detail_tarifLayanan,txt_detail_deskripsiLayanan;

    API api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);

        api = new API();
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("Detail Layanan");

        img_detail_layanan = findViewById(R.id.img_detail_layanan);
        txt_detail_layanan = findViewById(R.id.txt_detail_layanan);
        txt_detail_tarifLayanan = findViewById(R.id.txt_detail_tarifLayanan);
        txt_detail_deskripsiLayanan= findViewById(R.id.txt_detail_deskripsiLayanan);

        getDetailLayanan();
    }

    public void getDetailLayanan(){
        Intent i = getIntent();

//        Locale localeId = new Locale("in", "ID");
//        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeId);

        String layanan = i.getStringExtra("layanan");
        String tarif = i.getStringExtra("tarif_layanan");
        String deskripsi = i.getStringExtra("deskripsi");

        txt_detail_layanan.setText(layanan);
        txt_detail_tarifLayanan.setText("Rp."+tarif);
        txt_detail_deskripsiLayanan.setText(deskripsi);

        Picasso.get().load(api.URL_GAMBAR_LAYANAN + i.getStringExtra("img_layanan")).into(img_detail_layanan);

    }

}