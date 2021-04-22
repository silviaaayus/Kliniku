package com.silvia.klinik.Antrian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.silvia.klinik.API;
import com.silvia.klinik.BookingJadwal.AdapterBookingJadwal;
import com.silvia.klinik.BookingJadwal.ModelBookingJadwal;
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.MainActivity;
import com.silvia.klinik.Poli.AdapterPoli;
import com.silvia.klinik.Poli.ModelPoli;
import com.silvia.klinik.Poli.PoliActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;
import com.silvia.klinik.databinding.ActivityAntrianBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AntrianActivity extends AppCompatActivity {


    API api;
    ImageView back;
    TextView title;
    TinyDB tinyDB;
    String id_user, id_pasien;

    private List<ModelAntrian> dataAntrian;
    private RecyclerView recycler_antrian;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);

        api = new API();
        AndroidNetworking.initialize(this);

        tinyDB = new TinyDB(this);

        tinyDB = new TinyDB(this);
        id_user = tinyDB.getString("keyIdUser");
        id_pasien = tinyDB.getString("keyIdPasien");


        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Intent i = new Intent(AntrianActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("No Antrian");

        if (!tinyDB.getBoolean("keyLogin")){
            Intent intent = new Intent(AntrianActivity.this, LoginActivity.class);
            Toast.makeText(this, "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }



        recycler_antrian = findViewById(R.id.recycler_antrian);
        recycler_antrian.setHasFixedSize(true);
        recycler_antrian.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataAntrian = new ArrayList<>();
        getAntrian();
    }


    public void getAntrian(){
        Log.e("antrian",api.URL_NOANTRIAN+id_pasien+"&"+"created_by="+id_user);
        AndroidNetworking.get(api.URL_NOANTRIAN+id_pasien+"&"+"created_by="+id_user)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("Sukses")) {
                                dataAntrian.clear();
                                Gson gson = new Gson();
                                JSONArray res = response.getJSONArray("res");
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelAntrian isi = gson.fromJson(data+ "",ModelAntrian.class);
                                    dataAntrian.add(isi);
                                }
                                AdapterAntrian adapter = new AdapterAntrian(dataAntrian);
                                recycler_antrian.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }else {

                                Toast.makeText(AntrianActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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
    public void onBackPressed(){
        Toast.makeText(this, "Tidak bisa kembali, silahkan Klik tombol Back diatas", Toast.LENGTH_SHORT).show();

    }
}