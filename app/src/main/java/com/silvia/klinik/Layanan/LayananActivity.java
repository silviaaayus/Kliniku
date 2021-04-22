package com.silvia.klinik.Layanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.silvia.klinik.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LayananActivity extends AppCompatActivity {

    API api;
    ImageView back;
    TextView title;

    private List<ModelLayanan> dataLayanan;
    private RecyclerView recycler_layanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);
        api = new API();
        AndroidNetworking.initialize(this);

        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_toolbar);
        title.setText("Layanan Kami");

        recycler_layanan = findViewById(R.id.recycler_layanan);
        recycler_layanan.setHasFixedSize(true);
        recycler_layanan.setLayoutManager(new GridLayoutManager(this,2));
        dataLayanan = new ArrayList<>();

        getLayanan();

    }
    public void getLayanan(){
        AndroidNetworking.get(api.URL_LAYANAN)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataLayanan.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelLayanan Isi = gson.fromJson(data + "", ModelLayanan.class);
                                    dataLayanan.add(Isi);
                                }
                                AdapterLayanan adapter = new AdapterLayanan(dataLayanan);
                                recycler_layanan.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {

                                Toast.makeText(LayananActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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