package com.silvia.klinik.Riwayat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.silvia.klinik.API;
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RiwayatFragment extends Fragment {
    API api;
    TinyDB tinyDB;
    String id;

    private ArrayList<ModelRiwayat> dataRiwayat;
    private RecyclerView recycler_riwayat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_riwayat, container, false);

        AndroidNetworking.initialize(getContext());
        api = new API();
        tinyDB = new TinyDB(getContext());
        id = tinyDB.getString("keyIdPasien");

        recycler_riwayat = view.findViewById(R.id.recycler_riwayat);
        recycler_riwayat.setHasFixedSize(true);
        recycler_riwayat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        dataRiwayat = new ArrayList<>();
        getRiwayat();


        if (!tinyDB.getBoolean("keyLogin")){

            Intent intent = new Intent(getContext(), LoginActivity.class);
            Toast.makeText(getContext(), "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return view;
    }
    
    public void getRiwayat(){
        Log.e("url",api.URL_RIWAYAT+id);
        AndroidNetworking.get(api.URL_RIWAYAT+id)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("sukses")) {
                                dataRiwayat.clear();
                                Gson gson = new Gson();
                                JSONArray res = response.getJSONArray("res");
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelRiwayat isi = gson.fromJson(data + "", ModelRiwayat.class);
                                    dataRiwayat.add(isi);
                                }
                                AdapterRiwayat adapter =  new AdapterRiwayat(dataRiwayat);
                                recycler_riwayat.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }else {

                                Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
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