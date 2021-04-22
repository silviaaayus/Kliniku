package com.silvia.klinik.Poli;

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
import com.silvia.klinik.KotakSaran.KotakSaranActivity;
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PoliActivity extends AppCompatActivity {

    API api;
    ImageView back;
    TextView title;

    private List<ModelPoli> dataPoli;
    private RecyclerView recycler_poli;

    TinyDB tinyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poli);

        api = new API();
        AndroidNetworking.initialize(this);
        tinyDB = new TinyDB(this);

        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("Poliklinik");

        if (!tinyDB.getBoolean("keyLogin")){
            Intent intent = new Intent(PoliActivity.this, LoginActivity.class);
            Toast.makeText(this, "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        recycler_poli = findViewById(R.id.recycler_poli);
        recycler_poli.setHasFixedSize(true);
        recycler_poli.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataPoli = new ArrayList<>();
        getPoli();
    }

    public void getPoli(){
        AndroidNetworking.get(api.URL_POLI)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataPoli.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelPoli Isi = gson.fromJson(data + "", ModelPoli.class);
                                    dataPoli.add(Isi);
                                }
                                AdapterPoli adapter = new AdapterPoli(dataPoli);
                                recycler_poli.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {

                                Toast.makeText(PoliActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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